package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Menu {

    private List<Dish> dishes;

    private List<Drink> drinks;

    private List<Dessert> desserts;
}
