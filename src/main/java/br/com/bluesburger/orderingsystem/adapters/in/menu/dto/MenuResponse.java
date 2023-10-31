package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
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
    private List<Dish> dishes;

    @JsonProperty("drinks")
    private List<Drink> drinks;

    @JsonProperty("desserts")
    private List<Dessert> desserts;
}
