package me.dio.sacola.resource.controller;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaController
{
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto)
    {
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id)
    {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{id}/")
    public Sacola fecharSacola(@PathVariable("id") Long id,
                               @RequestParam("formaPagamento") int formaPagamento)
    {
        return  sacolaService.fecharSacola(id, formaPagamento);
    }


}
