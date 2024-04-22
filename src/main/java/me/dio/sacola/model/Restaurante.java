package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Explicação de cada anotação na classe sacola
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL) //Se excluir o restaurante, será excluído todos os produtos associados a ele.
    private List<Produto> cardapio;

    @Embedded //Irá incorporar as propriedades da classe Endereço.
    private Endereco endereco;
}
