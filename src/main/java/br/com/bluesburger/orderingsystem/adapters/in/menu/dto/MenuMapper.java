package br.com.bluesburger.orderingsystem.adapters.in.menu.dto;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.core.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuMapper {

    public static MenuResponse mapperMenuToMenuResponse(Menu menu) {
        MenuResponse menuResponse = new MenuResponse();

        menuResponse.setDishes(buildDishes(menu.getDishes()));
        menuResponse.setDrinks(buildDrinks(menu.getDrinks()));
        menuResponse.setDesserts(buildDesserts(menu.getDesserts()));

        return menuResponse;
    }

    private static List<DishResponse> buildDishes(List<Dish> source) {
        var target = new ArrayList<DishResponse>();

        for (Dish dish : source) {
            var dishObject = DishResponse.builder()
                    .id(dish.getId())
                    .name(dish.getName())
                    .description(dish.getDescription())
                    .category(dish.getCategory())
                    .price(dish.getPrice())
                    .build();

            target.add(dishObject);
        }
        return target;
    }

    private static List<DrinkResponse> buildDrinks(List<Drink> source) {
        var target = new ArrayList<DrinkResponse>();

        for (Drink drink : source) {
            var drinkObject = DrinkResponse.builder()
                    .id(drink.getId())
                    .name(drink.getName())
                    .description(drink.getDescription())
                    .category(drink.getCategory())
                    .price(drink.getPrice())
                    .build();

            target.add(drinkObject);
        }
        return target;
    }

    private static List<DessertResponse> buildDesserts(List<Dessert> source) {
        var target = new ArrayList<DessertResponse>();

        for (Dessert dessert : source) {
            var dessertObject = DessertResponse.builder()
                    .id(dessert.getId())
                    .name(dessert.getName())
                    .description(dessert.getDescription())
                    .category(dessert.getCategory())
                    .price(dessert.getPrice())
                    .build();

            target.add(dessertObject);
        }
        return target;
    }
}
