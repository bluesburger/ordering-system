package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.core.domain.*;
import br.com.bluesburger.orderingsystem.ports.out.DessertPort;
import br.com.bluesburger.orderingsystem.ports.out.DishPort;
import br.com.bluesburger.orderingsystem.ports.out.DrinkPort;
import br.com.bluesburger.orderingsystem.ports.out.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.bluesburger.orderingsystem.core.domain.factory.MenuFactory.buildMenu;

@RequiredArgsConstructor
@Service
public class MenuPromotionalStrategy implements MenuStrategy {

    private final DishPort dishPort;
    private final DrinkPort drinkPort;
    private final DessertPort dessertPort;
    private final OrderPort orderPort;

    @Override
    public Menu showMenu(User user) {
        final var dishList = dishPort.findAll();
        final var drinkList = drinkPort.findAll();
        final var dessertList = dessertPort.findAll();

        var menu = buildMenu(dishList, drinkList, dessertList);
        final var ordersUser = orderPort.getOrdersByUser(user);

        applyDiscounts(menu, ordersUser);

        return menu;
    }

    private void applyDiscounts(Menu menuDefault, List<Order> ordersUser) {
        applyDiscountDishes(menuDefault.getDishes(), ordersUser);
        applyDiscountDrinks(menuDefault.getDrinks(), ordersUser);
        applyDiscountDesserts(menuDefault.getDesserts(), ordersUser);
    }

    private void applyDiscountDishes(List<Dish> dishes, List<Order> ordersUser) {
        Map<Object, Long> countOrders = ordersUser.stream().flatMap(order -> order.getDishes().stream())
                .collect(Collectors.groupingBy(dish -> dish, Collectors.counting()));

        dishes.stream()
                .filter(dish -> countOrders.containsKey(dish) && countOrders.get(dish) > 10)
                .forEach(Dish::applyTenPercentDiscount);
    }

    private void applyDiscountDrinks(List<Drink> drinks, List<Order> ordersUser) {
        Map<Object, Long> countOrders = ordersUser.stream().flatMap(order -> order.getDrinks().stream())
                .collect(Collectors.groupingBy(drink -> drink, Collectors.counting()));

        drinks.stream()
                .filter(drink -> countOrders.containsKey(drink) && countOrders.get(drink) > 10)
                .forEach(Drink::applyFivePercentDiscount);
    }

    private void applyDiscountDesserts(List<Dessert> desserts, List<Order> ordersUser) {
        Map<Object, Long> countOrders = ordersUser.stream().flatMap(order -> order.getDesserts().stream())
                .collect(Collectors.groupingBy(dessert -> dessert, Collectors.counting()));

        desserts.stream()
                .filter(dessert -> countOrders.containsKey(dessert) && countOrders.get(dessert) > 10)
                .forEach(Dessert::applyFifteenPercentDiscount);
    }
}
