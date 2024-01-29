package br.com.bluesburger.orderingsystem.adapters.out.repository.order.mapper;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.DrinkRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import br.com.bluesburger.orderingsystem.core.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DessertRepository dessertRepository;

    @Autowired
    private DrinkRepository drinkRepository;


    public static Order mapperOrderToOrderEntity(OrderEntity source) {
        var validOrder = Optional.ofNullable(source).orElseThrow(OrderNotFoundException::new);

        var dishes = validOrder.getDishEntities().stream()
                .map(OrderMapper::mapperOrderItemDishToOrderItemDishEntity)
                .collect(Collectors.toList());

        var desserts = validOrder.getDessertEntities().stream()
                .map(OrderMapper::mapperOrderItemDessertToOrderItemDessertEntity)
                .collect(Collectors.toList());

        var drinks = validOrder.getDrinkEntities().stream()
                .map(OrderMapper::mapperOrderItemDrinkToOrderItemDrinkEntity)
                .collect(Collectors.toList());

        return Order.builder()
                .id(source.getId())
                .status(source.getStatus())
                .createdTime(source.getCreatedTime())
                .totalValue(source.getTotalValue())
                .dishes(dishes)
                .drinks(drinks)
                .desserts(desserts)
                .user(userToUserEntity(source.getUserEntity()))
                .build();

    }

    private static User userToUserEntity(UserEntity source) {
        return User.builder()
                .id(source.getId())
                .cpf(source.getCpf())
                .identified_user(source.getIdentified_user())
                .build();
    }

    private static Dish dishToDishEntity(DishEntity source) {
        return Dish.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }

    private static Drink drinkToDrinkEntity(DrinkEntity source) {
        return Drink.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .price(source.getPrice())
                .build();
    }

    private static Dessert dessertToDessertEntity(DessertEntity source) {
        return Dessert.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }

    public OrderEntity mapperOrderEntityToOrder(Order source) {
        var validOrder = Optional.ofNullable(source).orElseThrow(OrderNotFoundException::new);

        var dishes = source.getDishes().stream()
                .map(this::mapperOrderItemDishEntityToOrderItemDish)
                .collect(Collectors.toList());

        var desserts = validOrder.getDesserts().stream()
                .map(this::mapperOrderItemDessertEntityToOrderItemDessert)
                .collect(Collectors.toList());

        var drinks = validOrder.getDrinks().stream()
                .map(this::mapperOrderItemDrinkEntityToOrderItemDrink)
                .collect(Collectors.toList());

        return OrderEntity.builder()
                .id(source.getId())
                .status(source.getStatus())
                .createdTime(source.getCreatedTime())
                .totalValue(source.getTotalValue())
                .dishEntities(dishes)
                .drinkEntities(drinks)
                .dessertEntities(desserts)
                .userEntity(userEntityToUser(source.getUser()))
                .build();
    }

    public OrderItemDishEntity mapperOrderItemDishEntityToOrderItemDish(OrderItemDish source) {
        return OrderItemDishEntity.builder()
                .dish(dishEntityToDish(source.getDish()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    public OrderItemDessertEntity mapperOrderItemDessertEntityToOrderItemDessert(OrderItemDessert source) {
        return OrderItemDessertEntity.builder()
                .dessert(dessertEntityToDessert(source.getDessert()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    public OrderItemDrinkEntity mapperOrderItemDrinkEntityToOrderItemDrink(OrderItemDrink source) {
        return OrderItemDrinkEntity.builder()
                .drink(drinkEntityToDrink(source.getDrink()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    private DishEntity dishEntityToDish(Dish source) {
        return dishRepository.getReferenceById(source.getId());
    }

    private DessertEntity dessertEntityToDessert(Dessert source) {
        return dessertRepository.getReferenceById(source.getId());
    }

    private DrinkEntity drinkEntityToDrink(Drink source) {
        return drinkRepository.getReferenceById(source.getId());
    }

    public static OrderItemDish mapperOrderItemDishToOrderItemDishEntity(OrderItemDishEntity source) {
        return OrderItemDish.builder()
                .dish(dishToDishEntity(source.getDish()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    public static OrderItemDessert mapperOrderItemDessertToOrderItemDessertEntity(OrderItemDessertEntity source) {
        return OrderItemDessert.builder()
                .dessert(dessertToDessertEntity(source.getDessert()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    public static OrderItemDrink mapperOrderItemDrinkToOrderItemDrinkEntity(OrderItemDrinkEntity source) {
        return OrderItemDrink.builder()
                .drink(drinkToDrinkEntity(source.getDrink()))
                .quantity(source.getQuantity())
                .totalAmount(source.getTotalAmount())
                .build();
    }

    private static UserEntity userEntityToUser(User source) {
        return UserEntity.builder()
                .id(source.getId())
                .cpf(source.getCpf())
                .identified_user(source.getIdentified_user())
                .build();
    }
}
