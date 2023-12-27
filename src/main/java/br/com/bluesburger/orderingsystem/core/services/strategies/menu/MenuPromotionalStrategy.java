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

@Service
@RequiredArgsConstructor
public class MenuPromotionalStrategy implements MenuStrategy {

    private final DishRepository dishRepository;
    private final DrinkRepository drinkRepository;
    private final DessertRepository dessertRepository;
    private final OrderPort orderPort;

    @Override
    public Menu showMenu(User user) {
        try {
            final var dishList = dishRepository.findAll();
            final var drinkList = drinkRepository.findAll();
            final var dessertList = dessertRepository.findAll();

            var menu = buildMenu(dishList, drinkList, dessertList);
            final var ordersUser = orderPort.getOrdersByUser(user);

            applyDiscounts(menu, ordersUser);

            return menu;
        } catch (Exception exception) {
            throw new RuntimeException("Ocorreu algum erro na busca dos produtos no banco de dados");
        }
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
                .filter(dishe -> countOrders.containsKey(dishe) && countOrders.get(dishe) > 10)
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
