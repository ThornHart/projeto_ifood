package me.dio.sacolaapiifood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@Embeddable // Classe abstrata que terá os atributos embedded por outra table
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String complemento;
}
