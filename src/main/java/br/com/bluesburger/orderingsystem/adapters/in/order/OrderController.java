package br.com.bluesburger.orderingsystem.adapters.in.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
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
import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.OrderDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.OrderMapper;
import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import lombok.SneakyThrows;

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

	@GetMapping("/all/{status}")
	@ResponseBody
	public List<OrderDto> getOrderById(@PathVariable OrderStatus status) {
		return orderAdapter.findAllOrders(status).stream()
				.map(orderMapper::toDto)
				.toList();
	}
	
	@PostMapping
	public void addOrder(@RequestBody OrderRequest orderRequest) {
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

	// FIXME: remover
	@Component
	@Profile({ "test" })
	public class DataLoader {

		@PostConstruct
		@SneakyThrows
		public void onConstruct() {
			
			// Dishes
			List.of("Falafel", "Fish", "Gazpacho", "Ramen", "Rigatoni", "Hamburguer")
				.stream()
				.map(Dish::new)
				.peek(d -> d.setPrice(new BigDecimal(new Random().nextDouble())))
				.forEach(orderAdapter::save);
			
			// Desserts		
			List.of("Cake", "Cookie", "Biscuit", "Gelatin", "Ice Cream", "Pie", "Pudding", "Candy")
				.stream()
				.map(Dessert::new)
				.peek(d -> d.setPrice(new BigDecimal(new Random().nextDouble())))
				.forEach(orderAdapter::save);
			
			// Drinks
			List.of("Margarita", "Cosmopolitan", "Daiquiri", "Gimlet", "Manhattan", 
					"Negroni", "Old Fashioned", "Expresso Martini", "Passionfruit Martini", "Mimosa")
				.stream()
				.map(Drink::new)
				.peek(d -> d.setPrice(new BigDecimal(new Random().nextDouble())))
				.forEach(orderAdapter::save);
			
			// Orders
			createOrder("787.641.759-01", List.of(1L), List.of(5L), List.of(1L), OrderStatus.PREPARO_INICIADO);			
			createOrder(null, List.of(2L), List.of(2L), List.of(4L, 5L, 15L), null);
			createOrder("160.964.076-41", List.of(5L, 3L), List.of(1L), List.of(), OrderStatus.PREPARO_PRONTO);
			createOrder("659.296.788-98", List.of(3L), List.of(2L), List.of(4L), OrderStatus.PEDIDO_ENTREGUE);
		}

		private Order createOrder(String cpf, List<Long> dishesIds, List<Long> drinksIds, List<Long> dessertsIds, OrderStatus orderStatus) {
			var order = new Order();
			if (orderStatus != null) {
				order.setStatus(orderStatus);
			}
			order.setCpf(Optional
					.ofNullable(cpf)
					.map(Cpf::new)
					.orElse(null));
			
			dishesIds.stream().map(orderAdapter::findDishById).filter(opt -> opt.isPresent()).map(opt -> opt.get()).forEach(order::add);
			drinksIds.stream().map(orderAdapter::findDrinkById).filter(opt -> opt.isPresent()).map(opt -> opt.get()).forEach(order::add);
			dessertsIds.stream().map(orderAdapter::findDessertById).filter(opt -> opt.isPresent()).map(opt -> opt.get()).forEach(order::add);
			
			return orderAdapter.save(order);
		}
	}
}
