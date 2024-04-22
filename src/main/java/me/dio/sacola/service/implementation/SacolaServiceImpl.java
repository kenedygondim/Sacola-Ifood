package me.dio.sacola.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurante;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProdutoRepository;
import me.dio.sacola.repository.SacolaRepository;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService
{
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if(sacola.isFechada())
           throw new RuntimeException("Esta sacola está fechada");

       Item itemConstruido = Item.builder()
           .quantidade(itemDto.getQuantidade())
           .sacola(sacola)
           .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow( () -> {
               throw new RuntimeException("Esse produto não existe");
           }
           )).build();

       List<Item> itensDaSacola = sacola.getItens();

       if(itensDaSacola.isEmpty())
           itensDaSacola.add(itemConstruido);
        else {
           Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
           Restaurante restauranteDoNovoItem = itemConstruido.getProduto().getRestaurante();

           if(restauranteAtual.equals(restauranteDoNovoItem))
                itensDaSacola.add(itemConstruido);
           else
               throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes! Feche a sacola ou remova os itens.");
       }

        double valorTotalDaSacola = 0.0;

        for (Item itemDaSacola : itensDaSacola){
            valorTotalDaSacola += itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
        }

        sacola.setValorTotal(valorTotalDaSacola);
        sacolaRepository.save(sacola);

        return itemConstruido;
    }

    @Override
    public Sacola verSacola(Long id)
    {
        return sacolaRepository.findById(id)
                .orElseThrow(
                () -> new RuntimeException("Essa sacola não existe!")
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numFormaPagamento)
    {
        Sacola sacola = verSacola(id);

        //método getItens é um getter da classe sacola
        if(sacola.getItens().isEmpty())
           throw new RuntimeException("Inclua itens na sacola!");

        FormaPagamento formaPagamento = numFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }
}
