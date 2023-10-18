package br.com.bluesburger.orderingsystem.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.services.DessertService;
import br.com.bluesburger.orderingsystem.core.services.DishService;
import br.com.bluesburger.orderingsystem.core.services.DrinkService;
import br.com.bluesburger.orderingsystem.core.services.OrderService;

@Component
public class OrderAdapter {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private DrinkService drinkService;

	public Optional<Order> getById(Long orderId) {
		return orderService.getById(orderId);
	}
	
	public List<Order> findAllOrders(OrderStatus status) {
		return orderService.findAll(status);
	}

	public Order save(Order order) {
		return orderService.save(order);
	}
	
	public Optional<Dish> findDishById(Long id) {
		return dishService.getById(id);
	}
	
	public Optional<Dessert> findDessertById(Long id) {
		return dessertService.getById(id);
	}
	
	public Optional<Drink> findDrinkById(Long id) {
		return drinkService.getById(id);
	}
	
	public void save(Dish dish) {
		dishService.save(dish);
	}
	
	public void save(Dessert dessert) {
		dessertService.save(dessert);
	}
	
	public void save(Drink drink) {
		drinkService.save(drink);
	}
}