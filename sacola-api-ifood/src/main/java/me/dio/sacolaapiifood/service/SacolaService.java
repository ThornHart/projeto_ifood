package me.dio.sacolaapiifood.service;

import me.dio.sacolaapiifood.model.Item;
import me.dio.sacolaapiifood.model.Sacola;
import me.dio.sacolaapiifood.resource.dto.ItemDto;

//Implementar os metodos na service
public interface SacolaService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long Id);
    Sacola fecharSacola(Long Id, int formaPagamento);

}
