package br.com.bluesburger.orderingsystem.adapters.in.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.AddDessertToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.AddDishToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.AddDrinkToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.CreateOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.dto.OrderDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.OrderMapper;
import br.com.bluesburger.orderingsystem.core.services.OrderService;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;
	
	@PostMapping
	public OrderDto crateOrder(@RequestBody CreateOrderRequest command) {
		var createdOrder = orderService.createOrder(command.getCpf(), 
				command.getDishes(), command.getDrinks(), command.getDesserts(), command.getUser());
		
		return orderMapper.toDto(createdOrder);
	}
	
	@GetMapping("/{orderId}")
	@ResponseBody
	public OrderDto getOrderById(@PathVariable Long orderId) {
		var order = orderService.getById(orderId).orElseThrow(OrderNotFoundException::new);
		return orderMapper.toDto(order);
	}
	
	@PutMapping("/{orderId}/start")
	@ResponseBody
	public OrderDto startOrder(@PathVariable Long orderId) {
		var order = orderService.startOrder(orderId);
		return orderMapper.toDto(order);
	}
	
	@PutMapping("/{orderId}/ready")
	@ResponseBody
	public OrderDto setReadyOrder(@PathVariable Long orderId) {
		var order = orderService.setReadyOrder(orderId);
		return orderMapper.toDto(order);
	}
	
	@PutMapping("/{orderId}/complete")
	@ResponseBody
	public OrderDto completeOrder(@PathVariable Long orderId) {
		var order = orderService.completeOrder(orderId);
		return orderMapper.toDto(order);
	}
	
	@GetMapping("/all/{status}")
	@ResponseBody
	public List<OrderDto> getOrderById(@PathVariable OrderStatus status) {
		return orderService.findAll(status).stream()
				.map(orderMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@PutMapping(value = "/{orderId}/drink")
	public void addDrink(@PathVariable Long orderId, @RequestBody AddDrinkToOrderRequest command) {
		orderService.addDrink(orderId, command.getDrink());
	}
	
	@DeleteMapping(value = "/{orderId}/drink/{drinkId}")
	public void removeDrink(@PathVariable Long orderId, @PathVariable Long drinkId) {
		orderService.removeDrink(orderId, drinkId);
	}
	
	@PutMapping(value = "/{orderId}/dish")
	public void addDish(@PathVariable Long orderId, @RequestBody AddDishToOrderRequest command) {
		orderService.addDish(orderId, command.getDish());
	}
	
	@DeleteMapping(value = "/{orderId}/dish/{dishId}")
	public void removeDish(@PathVariable Long orderId, @PathVariable Long dishId) {
		orderService.removeDish(orderId, dishId);
	}
	
	@PutMapping(value = "/{orderId}/dessert")
	public void addDessert(@PathVariable Long orderId, @RequestBody AddDessertToOrderRequest command) {
		orderService.addDessert(orderId, command.getDessert());
	}
	
	@DeleteMapping(value = "/{orderId}/dessert/{dessertId}")
	public void removeDessert(@PathVariable Long orderId, @PathVariable Long dessertId) {
		orderService.removeDessert(orderId, dessertId);
	}
}
