package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DessertRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DishRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DrinkRequest;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.OrderDishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.core.domain.*;
import br.com.bluesburger.orderingsystem.ports.in.OrderProcessingServicePort;
import br.com.bluesburger.orderingsystem.ports.out.DessertPort;
import br.com.bluesburger.orderingsystem.ports.out.DishPort;
import br.com.bluesburger.orderingsystem.ports.out.DrinkPort;
import br.com.bluesburger.orderingsystem.ports.out.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements OrderProcessingServicePort {

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private DishPort dishPort;

    @Autowired
    private DessertPort dessertPort;

    @Autowired
    private DrinkPort drinkPort;

    @Autowired
    private UserService userService;

    public Order processOrder(Command command) {

        var userValidate = userService.validateUser(command.getUser());

        var order = new Order();
        order.setUser(userValidate);
        order.setStatus(OrderStatus.PEDIDO_RECEBIDO);

        var dishes = command.getDishes().stream()
                .map(this::completeDish)
                .map(this::createOrderItemDish)
                .collect(Collectors.toList());
        order.setDishes(dishes);

        var drinks = command.getDrinks().stream()
                .map(this::completeDrink)
                .map(this::createOrderItemDrink)
                .collect(Collectors.toList());
        order.setDrinks(drinks);

        var desserts = command.getDesserts().stream()
                .map(this::completeDessert)
                .map(this::createOrderItemDessert)
                .collect(Collectors.toList());
        order.setDesserts(desserts);

        final var totalValue = calculateTotalValueOrder(dishes, drinks, desserts);

        order.setTotalValue(totalValue);

        return save(order);
    }

    private OrderItemDish createOrderItemDish(Dish dish) {
        return OrderItemDish.builder()
                .dish(dish)
                .quantity(dish.getQuantity())
                .totalAmount(dish.getPrice().multiply(new BigDecimal(dish.getQuantity())))
                .build();
    }

    private OrderItemDessert createOrderItemDessert(Dessert dessert) {
        return OrderItemDessert.builder()
                .dessert(dessert)
                .quantity(dessert.getQuantity())
                .totalAmount(dessert.getPrice().multiply(new BigDecimal(dessert.getQuantity())))
                .build();
    }

    private OrderItemDrink createOrderItemDrink(Drink drink) {
        return OrderItemDrink.builder()
                .drink(drink)
                .quantity(drink.getQuantity())
                .totalAmount(drink.getPrice().multiply(new BigDecimal(drink.getQuantity())))
                .build();
    }

    public Order getOrderById(Long orderId) {
        return orderPort.findById(orderId);
    }

    public Order startOrder(Long orderId) {
        var existantOrder = getOrderById(orderId);
        existantOrder.start();
        return save(existantOrder);
    }

    public Order setReadyOrder(Long orderId) {
        var existantOrder = getOrderById(orderId);
        existantOrder.ready();
        return save(existantOrder);
    }

    public Order completeOrder(Long orderId) {
        var existantOrder = getOrderById(orderId);
        existantOrder.complete();
        return save(existantOrder);
    }

    public List<Order> findAll() {
        return orderPort.findAll();
    }

    public List<Order> findAllCustom() {
        return orderPort.findAllCustom();
    }

    public List<Order> findAllByStatus(OrderStatus status) {
        return orderPort.findAllByStatus(status);
    }

    public void addDish(Long orderId, DishRequest drinkDto) {
        var existantOrder = getOrderById(orderId);
        var dish = dishPort.findById(drinkDto.getId());
        existantOrder.add(dish);
        save(existantOrder);
    }

    public void removeDish(Long orderId, Long dishId) {
        var existantOrder = getOrderById(orderId);
        var dish = dishPort.findById(dishId);
        existantOrder.remove(dish);
        save(existantOrder);
    }

    public void addDrink(Long orderId, DrinkRequest drinkRequest) {
        var existantOrder = getOrderById(orderId);
        var drink = drinkPort.findById(drinkRequest.getId());
        existantOrder.add(drink);
        save(existantOrder);
    }

    public void removeDrink(Long orderId, Long drinkId) {
        var existantOrder = getOrderById(orderId);
        var drink = drinkPort.findById(drinkId);
        existantOrder.remove(drink);
        save(existantOrder);
    }

    public void addDessert(Long orderId, DessertRequest dessertRequest) {
        var existantOrder = getOrderById(orderId);
        var dessert = dessertPort.findById(dessertRequest.getId());
        existantOrder.add(dessert);
        save(existantOrder);
    }

    public void removeDessert(Long orderId, Long dessertId) {
        var existantOrder = getOrderById(orderId);
        var dessert = dessertPort.findById(dessertId);
        existantOrder.remove(dessert);
        save(existantOrder);
    }

    private Order save(Order order) {
        return orderPort.save(order);
    }

    private Dish completeDish(Dish dish) {
        Dish dishRecovered = dishPort.findById(dish.getId());
        dishRecovered.setQuantity(dish.getQuantity());

        return dishRecovered;
    }

    private Drink completeDrink(Drink drink) {
        Drink drinkRecovered = drinkPort.findById(drink.getId());
        drinkRecovered.setQuantity(drink.getQuantity());

        return drinkRecovered;
    }

    private Dessert completeDessert(Dessert dessert) {
        Dessert dessertRecovered = dessertPort.findById(dessert.getId());
        dessertRecovered.setQuantity(dessert.getQuantity());

        return dessertRecovered;
    }

    private BigDecimal calculateTotalValueOrder(List<OrderItemDish> dishes, List<OrderItemDrink> drinks, List<OrderItemDessert> desserts) {
        final var totalValueDishes = getTotalValueDishes(dishes);
        final var totalValueDrinks = getTotalValueDrinks(drinks);
        final var totalValueDesserts = getTotalValueDesserts(desserts);

        return List.of(totalValueDishes, totalValueDrinks, totalValueDesserts).stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalValueDishes(List<OrderItemDish> dishesOrder) {
        return dishesOrder.stream()
                .map(dishItem -> dishItem.getDish().getPrice().multiply(new BigDecimal(dishItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalValueDrinks(List<OrderItemDrink> drinks) {
        return drinks.stream()
                .map(drink -> drink.getDrink().getPrice().multiply(new BigDecimal(drink.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalValueDesserts(List<OrderItemDessert> desserts) {
        return desserts.stream()
                .map(dessert -> dessert.getDessert().getPrice().multiply(new BigDecimal(dessert.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void update(Order order) {
        orderPort.save(order);
    }
}
