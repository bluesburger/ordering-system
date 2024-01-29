package br.com.bluesburger.orderingsystem.adapters.in.order;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.OrderMapper;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.AddDessertToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.AddDishToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.AddDrinkToOrderRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.CommandRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.response.OrderResponse;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.ports.in.OrderProcessingServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.OrderMapper.mapperCommandToCommandRequest;
import static br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.OrderMapper.mapperOrderResponseToOrder;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {
	
	@Autowired
	private OrderProcessingServicePort orderProcessingServicePort;
	
	@PostMapping
	public ResponseEntity<OrderResponse> crateOrder(@RequestBody CommandRequest commandRequest) {
		var command = mapperCommandToCommandRequest(commandRequest);

		var order = orderProcessingServicePort.processOrder(command);
		final var orderResponse = mapperOrderResponseToOrder(order);

		return ResponseEntity.ok().body(orderResponse);
	}
	
	@GetMapping("/{orderId}")
	@ResponseBody
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
		var order = orderProcessingServicePort.getOrderById(orderId);

		final var orderResponse = mapperOrderResponseToOrder(order);
		return ResponseEntity.ok().body(orderResponse);
	}
	
	@PutMapping("/{orderId}/start")
	@ResponseBody
	public ResponseEntity<OrderResponse> startOrder(@PathVariable Long orderId) {
		var order = orderProcessingServicePort.startOrder(orderId);
		final var orderResponse = mapperOrderResponseToOrder(order);

		return ResponseEntity.ok().body(orderResponse);
	}
	
	@PutMapping("/{orderId}/ready")
	@ResponseBody
	public ResponseEntity<OrderResponse> setReadyOrder(@PathVariable Long orderId) {
		var order = orderProcessingServicePort.setReadyOrder(orderId);
		final var orderResponse = mapperOrderResponseToOrder(order);

		return ResponseEntity.ok().body(orderResponse);
	}
	
	@PutMapping("/{orderId}/complete")
	@ResponseBody
	public ResponseEntity<OrderResponse> completeOrder(@PathVariable Long orderId) {
		var order = orderProcessingServicePort.completeOrder(orderId);
		final var orderResponse = mapperOrderResponseToOrder(order);

		return ResponseEntity.ok().body(orderResponse);
	}
	
	@GetMapping("/all/{status}")
	@ResponseBody
	public List<OrderResponse> getOrderById(@PathVariable OrderStatus status) {
		return orderProcessingServicePort.findAllByStatus(status).stream()
				.map(OrderMapper::mapperOrderResponseToOrder)
				.collect(Collectors.toList());
	}

	@GetMapping("/all")
	@ResponseBody
	public List<OrderResponse> getAllOrders() {
		return orderProcessingServicePort.findAll().stream()
				.map(OrderMapper::mapperOrderResponseToOrder)
				.collect(Collectors.toList());
	}

	@GetMapping("/all-ordered")
	@ResponseBody
	public List<OrderResponse> getAllOrdersOrdered() {
		return orderProcessingServicePort.findAllCustom().stream()
				.map(OrderMapper::mapperOrderResponseToOrder)
				.collect(Collectors.toList());
	}

	@PutMapping(value = "/{orderId}/dish")
	public void addDish(@PathVariable Long orderId, @RequestBody AddDishToOrderRequest command) {
		orderProcessingServicePort.addDish(orderId, command.getDish());
	}

	@DeleteMapping(value = "/{orderId}/dish/{dishId}")
	public void removeDish(@PathVariable Long orderId, @PathVariable Long dishId) {
		orderProcessingServicePort.removeDish(orderId, dishId);
	}
	
	@PutMapping(value = "/{orderId}/drink")
	public void addDrink(@PathVariable Long orderId, @RequestBody AddDrinkToOrderRequest command) {
		orderProcessingServicePort.addDrink(orderId, command.getDrink());
	}
	
	@DeleteMapping(value = "/{orderId}/drink/{drinkId}")
	public void removeDrink(@PathVariable Long orderId, @PathVariable Long drinkId) {
		orderProcessingServicePort.removeDrink(orderId, drinkId);
	}
	
	@PutMapping(value = "/{orderId}/dessert")
	public void addDessert(@PathVariable Long orderId, @RequestBody AddDessertToOrderRequest command) {
		orderProcessingServicePort.addDessert(orderId, command.getDessert());
	}
	
	@DeleteMapping(value = "/{orderId}/dessert/{dessertId}")
	public void removeDessert(@PathVariable Long orderId, @PathVariable Long dessertId) {
		orderProcessingServicePort.removeDessert(orderId, dessertId);
	}
}
