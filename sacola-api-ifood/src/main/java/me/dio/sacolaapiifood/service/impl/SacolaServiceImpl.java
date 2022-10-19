package me.dio.sacolaapiifood.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapiifood.enumeration.FormaPagamento;
import me.dio.sacolaapiifood.model.Item;
import me.dio.sacolaapiifood.model.Restaurante;
import me.dio.sacolaapiifood.model.Sacola;
import me.dio.sacolaapiifood.repository.ProdutoRepository;
import me.dio.sacolaapiifood.repository.SacolaRepository;
import me.dio.sacolaapiifood.resource.dto.ItemDto;
import me.dio.sacolaapiifood.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola esta fechada");
        }
        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse produto nao existe");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Nao é possivel adicionar produtos de restaurantes diferentes");
            }
        }
        List<Double> valorDosItens = new ArrayList<>();

        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem =
                    itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return itemParaSerInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
         return sacolaRepository.findById(id).orElseThrow(
                () -> {
                   throw new RuntimeException("Essa sacola nao existe");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola");
        }
        FormaPagamento formaPagamento =
                numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);

    }

    @Override
    public Sacola excluirSacola(Long id) {
        Sacola sacola = verSacola(id);
        sacolaRepository.delete(sacola);
        return sacola;
    }

    }

