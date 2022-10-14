package me.dio.sacolaapiifood.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapiifood.model.Item;
import me.dio.sacolaapiifood.model.Sacola;
import me.dio.sacolaapiifood.resource.dto.ItemDto;
import me.dio.sacolaapiifood.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RestController // Indicar que a classe possui os endpoints
@RequestMapping("/ifood-devweek/sacolas") // Definir como sera escrito os endpoints
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping //POST
    public Item incluirItemNaSacola(@RequestBody /*Indicar que a info vem do corpo da requisicao*/ ItemDto itemDto) {
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")//GET
    public Sacola verSacola(@PathVariable/*Indicar que o id vem da url*/("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")//PATCH
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,@RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
