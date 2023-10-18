package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {

    @JsonProperty("dishes")
    private List<Dish> dishes;

    @JsonProperty("dishes")
    private List<Drink> drinks;

    @JsonProperty("dishes")
    private List<Dessert> desserts;
}
