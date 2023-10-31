package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DessertNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DishNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.OrderRepository;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import br.com.bluesburger.orderingsystem.core.ports.out.UserPort;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private DessertRepository dessertRepository;
	
	@Autowired
	private DrinkRepository drinkRepository;

	@Autowired
	private UserPort userPort;
	
	public Optional<Order> getById(Long orderId) {
		return orderRepository.findById(orderId);
	}
	
	public Order update(Order order) {
		return orderRepository.save(order);
	}
	
	public Order save(Order order) {		
		var user = order.getUser();


		final var enrichedOrder = orderRepository.save(order);
		if(user.getIdentified_user()){
			user.setOrders(List.of(order));
			userPort.updateUserByCpf(user);
		}

		return enrichedOrder;
	}

	public List<Order> findAll(OrderStatus status) {
		return orderRepository.findAllByStatus(status);
	}

	public Order createOrder(Cpf cpf, List<DishDto> dishesDto,
			List<DrinkDto> drinksDto, List<DessertDto> dessertsDto, User user) {
		var order = new Order();
		order.setUser(user);

		order.setStatus(OrderStatus.PEDIDO_REALIZADO);
		
		var dishes = dishesDto.stream()
			.map(DishDto::getId)
			.map(dishRepository::findById)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());
		order.setDishes(dishes);
		
		var drinks = drinksDto.stream()
			.map(DrinkDto::getId)
			.map(drinkRepository::findById)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());
		order.setDrinks(drinks);
		
		var desserts = dessertsDto.stream()
			.map(DessertDto::getId)
			.map(dessertRepository::findById)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());
		order.setDesserts(desserts);
		
		return save(order);
	}
	
	public Order startOrder(Long orderId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		existantOrder.start();
		return save(existantOrder);
	}
	
	public Order setReadyOrder(Long orderId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		existantOrder.ready();
		return save(existantOrder);
	}

	public Order completeOrder(Long orderId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		existantOrder.complete();
		return save(existantOrder);
	}

	public void addDrink(Long orderId, DrinkDto drinkDto) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var drink = drinkRepository.findById(drinkDto.getId()).orElseThrow(DrinkNotFoundException::new);
		existantOrder.add(drink);
		save(existantOrder);
	}
	
	public void removeDrink(Long orderId, Long drinkId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var drink = drinkRepository.findById(drinkId).orElseThrow(DrinkNotFoundException::new);
		existantOrder.remove(drink);
		save(existantOrder);
	}
	
	public void addDish(Long orderId, DishDto drinkDto) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var dish = dishRepository.findById(drinkDto.getId()).orElseThrow(DishNotFoundException::new);
		existantOrder.add(dish);
		save(existantOrder);
	}
	
	public void removeDish(Long orderId, Long dishId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var dish = dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new);
		existantOrder.remove(dish);
		save(existantOrder);
	}
	
	public void addDessert(Long orderId, DessertDto dessertDto) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var dessert = dessertRepository.findById(dessertDto.getId()).orElseThrow(DessertNotFoundException::new);
		existantOrder.add(dessert);
		save(existantOrder);
	}
	
	public void removeDessert(Long orderId, Long dessertId) {
		var existantOrder = getById(orderId).orElseThrow(OrderNotFoundException::new);
		var dessert = dessertRepository.findById(dessertId).orElseThrow(DessertNotFoundException::new);
		existantOrder.remove(dessert);
		save(existantOrder);
	}
}
