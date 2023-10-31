package br.com.bluesburger.orderingsystem.adapters.out.repository;

import java.util.List;

import br.com.bluesburger.orderingsystem.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByStatus(OrderStatus status);

	List<Order> findAllByUser(User user);


}
