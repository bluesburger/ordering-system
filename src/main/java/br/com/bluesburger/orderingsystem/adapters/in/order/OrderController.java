package br.com.bluesburger.orderingsystem.adapters.in.order;

import java.util.List;

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

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.OrderRequest;
import br.com.bluesburger.orderingsystem.adapters.out.OrderAdapter;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DessertNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DishNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.OrderDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.OrderMapper;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	private OrderAdapter orderAdapter;

	@Autowired
	private OrderMapper orderMapper;

	@GetMapping("/{orderId}")
	@ResponseBody
	public OrderDto getOrderById(@PathVariable Long orderId) {
		var order = orderAdapter.getById(orderId).orElseThrow(OrderNotFoundException::new);
		return orderMapper.toDto(order);
	}
	
	@PutMapping("/{orderId}/{status}")
	@ResponseBody
	public OrderDto updateStatus(@PathVariable Long orderId, @PathVariable OrderStatus status) {
		var existantOrder = orderAdapter.getById(orderId).orElseThrow(OrderNotFoundException::new);
		existantOrder.setStatus(status);
		return orderMapper.toDto(orderAdapter.save(existantOrder));
	}

	@GetMapping("/all/{status}")
	@ResponseBody
	public List<OrderDto> getOrderById(@PathVariable OrderStatus status) {
		return orderAdapter.findAllOrders(status).stream()
				.map(orderMapper::toDto)
				.toList();
	}
	
	@PostMapping
	public void addOrder(@RequestBody OrderRequest orderRequest) {
		orderRequest.setStatus(OrderStatus.PEDIDO_REALIZADO);
		var order = orderMapper.from(orderRequest);
		orderAdapter.save(order);
	}
	
	@PutMapping(value = "/{orderId}/drink")
	public void addDrink(@PathVariable Long orderId, @RequestBody DrinkDto drinkDto) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var drink = orderAdapter.findDrinkById(drinkDto.getId()).orElseThrow(DrinkNotFoundException::new);
				order.add(drink);
				orderAdapter.save(order);
			});
	}
	
	@DeleteMapping(value = "/{orderId}/drink/{drinkId}")
	public void removeDrink(@PathVariable Long orderId, @PathVariable Long drinkId) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var drink = orderAdapter.findDrinkById(drinkId).orElseThrow(DrinkNotFoundException::new);
				order.remove(drink);
				orderAdapter.save(order);
			});
	}
	
	@PutMapping(value = "/{orderId}/dish")
	public void addDish(@PathVariable Long orderId, @RequestBody DishDto dishDto) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var dish = orderAdapter.findDishById(dishDto.getId()).orElseThrow(DishNotFoundException::new);
				order.add(dish);
				orderAdapter.save(order);
			});
	}
	
	@DeleteMapping(value = "/{orderId}/dish/{dishId}")
	public void removeDish(@PathVariable Long orderId, @PathVariable Long dishId) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var dish = orderAdapter.findDishById(dishId).orElseThrow(DishNotFoundException::new);
				order.remove(dish);
				orderAdapter.save(order);
			});
	}
	
	@PutMapping(value = "/{orderId}/dessert")
	public void addDessert(@PathVariable Long orderId, @RequestBody DessertDto dessertDto) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var dessert = orderAdapter.findDessertById(dessertDto.getId()).orElseThrow(DessertNotFoundException::new);
				order.add(dessert);
				orderAdapter.save(order);
			});
	}
	
	@DeleteMapping(value = "/{orderId}/dessert/{dessertId}")
	public void removeDessert(@PathVariable Long orderId, @PathVariable Long dessertId) {
		orderAdapter.getById(orderId)
			.ifPresent(order -> {
				var dessert = orderAdapter.findDessertById(dessertId).orElseThrow(DessertNotFoundException::new);
				order.remove(dessert);
				orderAdapter.save(order);
			});
	}
}
