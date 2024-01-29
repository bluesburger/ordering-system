package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.*;

import java.util.List;

public interface OrderPort {

    List<Order> getOrdersByUser(User user);

    Order findById(Long orderId);

    List<Order> findAll();

    List<Order> findAllCustom();

    List<Order> findAllByStatus(OrderStatus status);

    Order save(Order order);
}
