package br.com.bluesburger.orderingsystem.core.domain.factory;

import br.com.bluesburger.orderingsystem.core.domain.*;

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
