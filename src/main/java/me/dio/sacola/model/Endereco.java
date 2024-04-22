package me.dio.sacola.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Embeddable //Não será diretamente uma tabela, servirá como propriedade para as tabelas de Restautante e Cliente. ótimo para reutilização de código
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String complemento;
}
