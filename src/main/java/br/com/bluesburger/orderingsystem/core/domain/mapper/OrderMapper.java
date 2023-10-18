package br.com.bluesburger.orderingsystem.core.domain.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.OrderRequest;
import br.com.bluesburger.orderingsystem.adapters.out.OrderAdapter;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.dto.OrderDto;

@Component
public class OrderMapper {
	
	@Autowired
	private DishMapper dishMapper;
	
	@Autowired
	private DessertMapper dessertMapper;
	
	@Autowired
	private DrinkMapper drinkMapper;
	
	@Autowired
	private OrderAdapter orderAdapter;
	
	public OrderDto toDto(Order order) {
		var validOrder = Optional.ofNullable(order).orElseThrow(OrderNotFoundException::new);
		var dishes = validOrder.getDishes().stream()
				.map(dishMapper::toDto)
				.toList();
		
		var desserts = validOrder.getDesserts().stream()
				.map(dessertMapper::toDto)
				.toList();
		
		var drinks = validOrder.getDrinks().stream()
				.map(drinkMapper::toDto)
				.toList();
		
		var orderDto = new OrderDto(order.getId(), order.getStatus(), order.getCreatedTime(), dishes, desserts, drinks);
		orderDto.setCpf(order.getCpf());
		return orderDto;
	}

	public Order from(OrderDto orderDto) {
		var order = new Order();
		order.setId(orderDto.getId());
		order.setCpf(orderDto.getCpf());
		order.setCreatedTime(orderDto.getCreatedTime());
		OrderStatus.from(orderDto.getStatus()).ifPresent(order::setStatus);
		return order;
	}

	public Order from(OrderRequest orderRequest) {
		var order = new Order();
		order.setId(orderRequest.getId());
		order.setCpf(orderRequest.getCpf());

		orderRequest.getDishes().stream()
			.map(c -> c.getId())
			.map(orderAdapter::findDishById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		orderRequest.getDesserts().stream()
			.map(c -> c.getId())
			.map(orderAdapter::findDessertById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		orderRequest.getDrinks().stream()
			.map(c -> c.getId())
			.map(orderAdapter::findDrinkById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		return order;
	}
}
