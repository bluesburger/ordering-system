package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.infrastructure.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Optional<Order> getById(Long orderId) {
		return orderRepository.findById(orderId);
	}
	
	public Order update(Order order) {
		return orderRepository.save(order);
	}
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findAll(OrderStatus status) {
		return orderRepository.findAllByStatus(status);
	}
}
