package br.com.bluesburger.orderingsystem.core.ports;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.User;

import java.util.List;

public interface OrderPort {

    List<Order> getOrdersByUser(User user);
}
