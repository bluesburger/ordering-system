package br.com.bluesburger.orderingsystem.core.ports;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;

import java.util.List;

public interface InventoryPort {

    List<Dish> getAllDishes();
    List<Drink> getAllDrinks();
    List<Dessert> getAllDesserts();
}
