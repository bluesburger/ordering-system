package br.com.bluesburger.orderingsystem.objectsmother;

import br.com.bluesburger.orderingsystem.core.domain.*;

import java.math.BigDecimal;
import java.util.List;

public class MenuMother {

    public static Menu buildMenuMock(User user) {
        return Menu.builder()
                .dishes(buildDishesMock(user))
                .drinks(buildDrinksMock())
                .desserts(buildDessertMock())
                .build();
    }

    public static List<Dish> buildDishesMock(User user) {
        final var dishOne = Dish.builder()
                .id(1L)
                .name("Peixe")
                .price(user.getIdentified_user() ? BigDecimal.valueOf(9) :BigDecimal.TEN)
                .build();

        final var dishTwo = Dish.builder()
                .id(2L)
                .name("Falafel")
                .price(BigDecimal.TEN)
                .build();

        final var dishThree = Dish.builder()
                .id(3L)
                .name("Gaspacho")
                .price(BigDecimal.TEN)
                .build();

        return List.of(dishOne, dishTwo, dishThree);
    }

    public static List<Drink> buildDrinksMock() {
        final var drinkOne = Drink.builder()
                .id(1L)
                .name("coca cola")
                .price(BigDecimal.TEN)
                .build();

        final var drinkTwo = Drink.builder()
                .id(2L)
                .name("pepsi")
                .price(BigDecimal.TEN)
                .build();

        final var drinkThree = Drink.builder()
                .id(3L)
                .name("sprite")
                .price(BigDecimal.TEN)
                .build();

        return List.of(drinkOne, drinkTwo, drinkThree);
    }

    public static List<Dessert> buildDessertMock() {
        final var dessertOne = Dessert.builder()
                .id(1L)
                .name("bolo")
                .price(BigDecimal.TEN)
                .build();

        final var dessertTwo = Dessert.builder()
                .id(2L)
                .name("sorvete")
                .price(BigDecimal.TEN)
                .build();

        final var dessertThree = Dessert.builder()
                .id(3L)
                .name("pudim")
                .price(BigDecimal.TEN)
                .build();

        return List.of(dessertOne, dessertTwo, dessertThree);
    }
}
