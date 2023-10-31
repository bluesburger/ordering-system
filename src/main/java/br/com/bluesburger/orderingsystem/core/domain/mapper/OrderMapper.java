package br.com.bluesburger.orderingsystem.core.domain.mapper;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.CreateOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.out.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.dto.OrderDto;
import br.com.bluesburger.orderingsystem.core.services.DessertService;
import br.com.bluesburger.orderingsystem.core.services.DishService;
import br.com.bluesburger.orderingsystem.core.services.DrinkService;

@Component
public class OrderMapper {
	
	@Autowired
	private DishMapper dishMapper;
	
	@Autowired
	private DessertMapper dessertMapper;
	
	@Autowired
	private DrinkMapper drinkMapper;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DessertService dessertService;
	
	public OrderDto toDto(Order order) {
		var validOrder = Optional.ofNullable(order).orElseThrow(OrderNotFoundException::new);
		var dishes = validOrder.getDishes().stream()
				.map(dishMapper::toDto)
				.collect(Collectors.toList());
		
		var desserts = validOrder.getDesserts().stream()
				.map(dessertMapper::toDto)
				.collect(Collectors.toList());
		
		var drinks = validOrder.getDrinks().stream()
				.map(drinkMapper::toDto)
				.collect(Collectors.toList());
		
		var orderDto = OrderDto.builder()
				.id(order.getId())
				.status(order.getStatus())
				.createdTime(order.getCreatedTime())
				.updatedTime(order.getUpdatedTime())
				.dishes(dishes)
				.drinks(drinks)
				.desserts(desserts)
				.user(order.getUser())
				.build();


		return orderDto;
	}

	public Order from(OrderDto orderDto) {
		var order = new Order();
		order.setId(orderDto.getId());
		orderDto.setUser(order.getUser());;
		order.setCreatedTime(orderDto.getCreatedTime());
		OrderStatus.from(orderDto.getStatus()).ifPresent(order::setStatus);
		return order;
	}
	
	public Order from(CreateOrderRequest orderRequest) {
		var order = new Order();
		order.setUser(order.getUser());;
		order.setStatus(orderRequest.getStatus());

		orderRequest.getDishes().stream()
			.map(c -> c.getId())
			.map(dishService::getById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		orderRequest.getDesserts().stream()
			.map(c -> c.getId())
			.map(dessertService::getById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		orderRequest.getDrinks().stream()
			.map(c -> c.getId())
			.map(drinkService::getById)
			.filter(d -> d.isPresent())
			.map(d -> d.get())
			.forEach(order::add);
		
		return order;
	}
}
