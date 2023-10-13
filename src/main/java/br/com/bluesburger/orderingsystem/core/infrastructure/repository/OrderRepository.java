package br.com.bluesburger.orderingsystem.core.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByStatus(OrderStatus status);

}
