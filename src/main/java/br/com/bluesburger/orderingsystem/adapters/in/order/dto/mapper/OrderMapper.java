package br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.*;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.response.*;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.*;

import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Command mapperCommandToCommandRequest(CommandRequest source) {
        var validOrder = Optional.ofNullable(source).orElseThrow(OrderNotFoundException::new);

        var dishes = validOrder.getDishes().stream()
                .map(OrderMapper::dishToDishRequest)
                .collect(Collectors.toList());

        var desserts = validOrder.getDesserts().stream()
                .map(OrderMapper::dessertToDessertRequest)
                .collect(Collectors.toList());

        var drinks = validOrder.getDrinks().stream()
                .map(OrderMapper::drinkToDrinkRequest)
                .collect(Collectors.toList());

        return Command.builder()
                .status(source.getStatus())
                .dishes(dishes)
                .drinks(drinks)
                .desserts(desserts)
                .user(userToUserRequest(source.getUser()))
                .build();
    }

    public static OrderResponse mapperOrderResponseToOrder(Order source) {
        var validOrder = Optional.ofNullable(source).orElseThrow(OrderNotFoundException::new);

        var dishes = validOrder.getDishes().stream()
                .map(OrderMapper::mapperOrderItemDishResponseToOrderItemDish)
                .collect(Collectors.toList());

        var desserts = validOrder.getDesserts().stream()
                .map(OrderMapper::mapperOrderItemDessertToOrderItemDessertResponse)
                .collect(Collectors.toList());

        var drinks = validOrder.getDrinks().stream()
                .map(OrderMapper::mapperOrderItemDessertToOrderItemDessertResponse)
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(source.getId())
                .status(source.getStatus())
                .createdTime(source.getCreatedTime())
                .totalValue(source.getTotalValue())
                .dishes(dishes)
                .drinks(drinks)
                .desserts(desserts)
                .user(source.getUser())
                .build();
    }

    private static User userToUserRequest(UserRequest source) {
        return User.builder()
                .cpf(source.getCpf())
                .identified_user(source.getIdentified_user())
                .build();
    }

    private static Dish dishToDishRequest(DishRequest source) {
        return Dish.builder()
                .id(source.getId())
                .quantity(source.getQuantity())
                .build();
    }

    private static Drink drinkToDrinkRequest(DrinkRequest source) {
        return Drink.builder()
                .id(source.getId())
                .quantity(source.getQuantity())
                .build();
    }

    private static Dessert dessertToDessertRequest(DessertRequest source) {
        return Dessert.builder()
                .id(source.getId())
                .quantity(source.getQuantity())
                .build();
    }

    private static OrderItemDishResponse mapperOrderItemDishResponseToOrderItemDish(OrderItemDish source) {
        return OrderItemDishResponse.builder()
                .dish(dishResponseToDish(source.getDish()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();

    }

    private static OrderItemDessertResponse mapperOrderItemDessertToOrderItemDessertResponse(OrderItemDessert source) {
        return OrderItemDessertResponse.builder()
                .dessert(dessertResponseToDessert(source.getDessert()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();

    }

    private static OrderItemDrinkResponse mapperOrderItemDessertToOrderItemDessertResponse(OrderItemDrink source) {
        return OrderItemDrinkResponse.builder()
                .drink(drinkResponseToDrink(source.getDrink()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();

    }

    private static DishResponse dishResponseToDish(Dish source) {
        return DishResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }

    private static DessertResponse dessertResponseToDessert(Dessert source) {
        return DessertResponse.builder()
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .price(source.getPrice())
                .build();
    }

    private static DrinkResponse drinkResponseToDrink(Drink source) {
        return DrinkResponse.builder()
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .price(source.getPrice())
                .build();
    }


}
