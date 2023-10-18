package br.com.bluesburger.orderingsystem.core.domain.factory;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.core.domain.Menu;

import java.util.List;

public class MenuFactory {

    public static Menu buildMenu(List<Dish> dishes, List<Drink> drinks, List<Dessert> desserts) {
        return Menu.builder()
                .dishes(dishes)
                .drinks(drinks)
                .desserts(desserts)
                .build();
    }
}
