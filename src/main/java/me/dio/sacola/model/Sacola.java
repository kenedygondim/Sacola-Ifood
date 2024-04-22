package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;

import java.util.List;

@AllArgsConstructor //Cria um construtor com todos os atributos
@Builder //Cria um objeto de forma simples
@Data //Cria todos os getters e setters dos atributos. Possui os metodos Equal e Hashcode. PS: Não recomendado em programas maiores por haver getters e setters inutilizados
@Entity //Garante que essa classe será uma entidade, uma tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora algumas propriedades
@NoArgsConstructor //Cria um construtor vazio (Essencial durante o uso do Lombok)
public class Sacola {
    @Id //Anotação para Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Estratégia de AUTO-incremento
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //o carregamento vai ser do tipo lazy, pois não queremos que todas as informações do cliente sejam carregadas junto com a sacola
    @JsonIgnore //a propriedade cliente será ignorada no retorno do json
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens; //Uma sacola pode estar associada a uma série de itens, mas um item só pode estar associado a uma sacola.

    private Double valorTotal;

    @Enumerated //Enumerated: definimos nosso Enum de forma de pagamaneto como DINHEIRO ou MAQUINETA
    private FormaPagamento formaPagamento; //Vai receber exclusivamente o valor DINHEIRO ou MAQUINETA

    private boolean fechada;
}
