package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DessertResponse implements Serializable {

    private static final long serialVersionUID = 1190172181165417950L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("categoria")
    private String category;

    @JsonProperty("preco")
    private BigDecimal price;
}
