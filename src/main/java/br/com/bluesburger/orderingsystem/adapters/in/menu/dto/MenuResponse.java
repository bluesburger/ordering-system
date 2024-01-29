package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse implements Serializable {

    private static final long serialVersionUID = -814389075442231294L;

    @JsonProperty("dishes")
    private List<DishResponse> dishes;

    @JsonProperty("drinks")
    private List<DrinkResponse> drinks;

    @JsonProperty("desserts")
    private List<DessertResponse> desserts;
}
