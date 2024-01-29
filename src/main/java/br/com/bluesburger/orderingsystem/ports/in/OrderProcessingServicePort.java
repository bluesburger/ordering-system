package br.com.bluesburger.orderingsystem.ports.in;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DessertRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DishRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DrinkRequest;
import br.com.bluesburger.orderingsystem.core.domain.Command;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

import java.util.List;

public interface OrderProcessingServicePort {

    Order processOrder(Command commandRequest);

    Order getOrderById(Long orderId);

    Order startOrder(Long orderId);

    Order setReadyOrder(Long orderId);

    Order completeOrder(Long orderId);

    List<Order> findAllByStatus(OrderStatus orderStatus);

    List<Order> findAll();

    List<Order> findAllCustom();

    void addDish(Long orderId, DishRequest dish);

    void removeDish(Long orderId, Long dishId);

    void addDrink(Long orderId, DrinkRequest drink);

    void removeDrink(Long orderId, Long drinkId);

    void addDessert(Long orderId, DessertRequest dessert);

    void removeDessert(Long orderId, Long dessertId);
}
