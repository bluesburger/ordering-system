package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.adapters.out.repository.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;
import br.com.bluesburger.orderingsystem.core.domain.*;
import br.com.bluesburger.orderingsystem.core.ports.out.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.bluesburger.orderingsystem.core.domain.factory.MenuFactory.buildMenu;

@RequiredArgsConstructor
@Service
public class MenuPromotionalStrategy implements MenuStrategy {

    private final DishRepository dishRepository;
    private final DrinkRepository drinkRepository;
    private final DessertRepository dessertRepository;
    private final OrderPort orderPort;

    @Override
    public Menu showMenu(User user) {
        final var dishList = dishRepository.findAll();
        final var drinkList = drinkRepository.findAll();
        final var dessertList = dessertRepository.findAll();

        var menu = buildMenu(dishList, drinkList, dessertList);
        final var ordersUser = orderPort.getOrdersByUser(user);

        applyDiscounts(menu, ordersUser);

        return menu;
    }

    private void applyDiscounts(Menu menuDefault, List<Order> ordersUser) {
        Map<Object, Long> countOrders = ordersUser.stream()
                .collect(Collectors.groupingBy(order -> order, Collectors.counting()));

        applyDiscountDishes(menuDefault.getDishes(), countOrders);
        applyDiscountDrinks(menuDefault.getDrinks(), countOrders);
        applyDiscountDesserts(menuDefault.getDesserts(), countOrders);
    }

    private void applyDiscountDishes(List<Dish> dishes, Map<Object, Long> countOrders) {
        dishes.stream()
                .filter(dishe -> countOrders.containsKey(dishe) && countOrders.get(dishe) > 10)
                .forEach(Dish::applyTenPercentDiscount);
    }

    private void applyDiscountDrinks(List<Drink> drinks, Map<Object, Long> countOrders) {
        drinks.stream()
                .filter(drink -> countOrders.containsKey(drink) && countOrders.get(drink) > 10)
                .forEach(Drink::applyFivePercentDiscount);
    }

    private void applyDiscountDesserts(List<Dessert> desserts, Map<Object, Long> countOrders) {
        desserts.stream()
                .filter(dessert -> countOrders.containsKey(dessert) && countOrders.get(dessert) > 10)
                .forEach(Dessert::applyFifteenPercentDiscount);
    }
}
