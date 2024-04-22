package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Explicação de cada anotação na classe sacola
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private double valorUnitario;

    @Builder.Default //Quando uma propriedade possui um valor pré-definido, usamos essa anotação;
    private Boolean disponivel  = true;

    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;
}
