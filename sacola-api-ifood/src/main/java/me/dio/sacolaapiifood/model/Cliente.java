package me.dio.sacolaapiifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor // Construtor com todos os atributos
@Builder // Design pattern que ajuda a criar o objeto
@Data // Setters e getters
@Entity //Indica que a classe será uma tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor //Construtor sem atributo
public class Cliente {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment no id
    private Long id;
    private String nome;
    @Embedded // Pegando os atributos cep e complemento da classe Endereco
    private Endereco endereco;
 }

