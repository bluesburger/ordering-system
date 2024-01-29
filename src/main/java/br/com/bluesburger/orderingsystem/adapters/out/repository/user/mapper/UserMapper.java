package br.com.bluesburger.orderingsystem.adapters.out.repository.user.mapper;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import br.com.bluesburger.orderingsystem.core.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class UserMapper {

    public static User userToUserEntity(UserEntity userEntity) {
        return isNull(userEntity) ? User.builder().identified_user(false).build() :
                User.builder()
                        .id(userEntity.getId())
                        .cpf(nonNull(userEntity.getCpf()) ? userEntity.getCpf() : "")
                        .identified_user(userEntity.getIdentified_user())
                        .orders(nonNull(userEntity.getOrderEntities()) ?
                                orderToOrderEntity(userEntity.getOrderEntities()) : Collections.emptyList())
                        .build();
    }

    public static UserEntity userEntityToUser(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .cpf(nonNull(user.getCpf()) ? user.getCpf() : "")
                .identified_user(user.getIdentified_user())
                .orderEntities(nonNull(user.getOrders()) ?
                        orderEntityToOrder(user.getOrders()) : Collections.emptyList())
                .build();
    }

    private static List<Order> orderToOrderEntity(List<OrderEntity> orderEntities) {
        var orders = new ArrayList<Order>();

        for (OrderEntity source : orderEntities) {
            var target = Order.builder()
                    .id(source.getId())
                    .status(source.getStatus())
                    .createdTime(source.getCreatedTime())
                    .updatedTime(source.getUpdatedTime())
//                    .dishes(dishResponseToDish(source.getDishEntities()))
//                    .drinks(drinkResponseToDrink(source.getDrinkEntities()))
//                    .desserts(dessertResponseToDessert(source.getDessertEntities()))
                    .totalValue(source.getTotalValue())
                    .build();

            orders.add(target);
        }
        return orders;
    }

    private static List<OrderEntity> orderEntityToOrder(List<Order> orderList) {
        var orders = new ArrayList<OrderEntity>();

        for (Order source : orderList) {
            var target = OrderEntity.builder()
                    .id(source.getId())
                    .status(source.getStatus())
                    .createdTime(source.getCreatedTime())
                    .updatedTime(source.getUpdatedTime())
//                    .dishEntities(dishToDishEntity(source.getDishes()))
//                    .drinkEntities(drinkEntityToDrink(source.getDrinks()))
//                    .dessertEntities(dessertEntityToDessert(source.getDesserts()))
                    .totalValue(source.getTotalValue())
                    .build();

            orders.add(target);
        }
        return orders;
    }

    private static Dish dishToDishEntity(DishEntity source) {
        return Dish.builder()
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }


    private static List<Dish> dishResponseToDish(List<DishEntity> dishEntityList) {

        var dishes = new ArrayList<Dish>();

        for (DishEntity source : dishEntityList) {
            var target = Dish.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .category(source.getCategory())
                    .price(source.getPrice())
                    .build();

            dishes.add(target);
        }
        return dishes;
    }

    private static List<DishEntity> dishToDishEntity(List<Dish> dishList) {

        var dishes = new ArrayList<DishEntity>();

        for (Dish source : dishList) {
            var target = DishEntity.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(source.getPrice())
                    .build();

            dishes.add(target);
        }
        return dishes;
    }

    private static List<Drink> drinkResponseToDrink(List<DrinkEntity> drinkList) {

        var drinks = new ArrayList<Drink>();

        for (DrinkEntity source : drinkList) {
            var target = Drink.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .quantity(source.getQuantity())
                    .price(source.getPrice())
                    .build();

            drinks.add(target);
        }
        return drinks;
    }

    private static List<DrinkEntity> drinkEntityToDrink(List<Drink> drinkList) {

        var drinks = new ArrayList<DrinkEntity>();

        for (Drink source : drinkList) {
            var target = DrinkEntity.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .quantity(source.getQuantity())
                    .price(source.getPrice())
                    .build();

            drinks.add(target);
        }
        return drinks;
    }

    private static List<Dessert> dessertResponseToDessert(List<DessertEntity> dessertEntityList) {

        var desserts = new ArrayList<Dessert>();

        for (DessertEntity source : dessertEntityList) {
            var target = Dessert.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(source.getPrice())
                    .build();

            desserts.add(target);
        }
        return desserts;
    }

    private static List<DessertEntity> dessertEntityToDessert(List<Dessert> dessertList) {

        var desserts = new ArrayList<DessertEntity>();

        for (Dessert source : dessertList) {
            var target = DessertEntity.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .price(source.getPrice())
                    .build();

            desserts.add(target);
        }
        return desserts;
    }

}
