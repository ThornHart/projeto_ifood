package me.dio.sacolaapiifood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacolaapiifood.enumeration.FormaPagamento;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Evitar dar erro no hibernate por causa do Lazy
@NoArgsConstructor
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Cliente pode ter varias sacola -- lazy fetch retorna o client apenas quando necessario
    @JsonIgnore // Evitar erros de serialização
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL) // Uma sacola para varios itens -- Cascade -PERSIST, MERGE, REMOVE e REFRESH de Sacola para Itens
    private List<Item> itens;
    private Double valorTotal;
    @Enumerated
    private FormaPagamento formaPagamento;
    private boolean fechada;
}
