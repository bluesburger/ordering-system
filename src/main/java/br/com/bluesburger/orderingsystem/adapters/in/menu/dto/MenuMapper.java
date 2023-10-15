package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import br.com.bluesburger.orderingsystem.core.domain.Menu;

public class MenuMapper {

    public static MenuResponse mapperMenuToMenuResponse(Menu menu) {
        MenuResponse menuResponse = new MenuResponse();

        menuResponse.setDishes(menu.getDishes());
        menuResponse.setDrinks(menu.getDrinks());
        menuResponse.setDesserts(menu.getDesserts());

        return menuResponse;
    }
}
