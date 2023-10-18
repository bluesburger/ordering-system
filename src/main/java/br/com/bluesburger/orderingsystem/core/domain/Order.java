package br.com.bluesburger.orderingsystem.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Data
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PEDIDO_REALIZADO;
	
	@Convert(converter = CpfConverter.class)
	private Cpf cpf;
	

	@ManyToMany
	private List<Dish> dishes = new ArrayList<>();

	@ManyToMany
	private List<Dessert> desserts = new ArrayList<>();
	
	@ManyToMany
	private List<Drink> drinks = new ArrayList<>();

	public void add(Dish... newDishes) {
		Stream.of(newDishes).forEach(dish -> dishes.add(dish));
	}
	
	public void remove(Dish dish) {
		dishes.remove(dish);
	}
	
	public void add(Dessert... newDesserts) {
		Stream.of(newDesserts).forEach(dish -> desserts.add(dish));
	}
	
	public void remove(Dessert dessert) {
		desserts.remove(dessert);
	}
	
	public void add(Drink... newDrinks) {
		Stream.of(newDrinks).forEach(dish -> drinks.add(dish));
	}
	
	public void remove(Drink drink) {
		drinks.remove(drink);
	}
	
	public void updateSituation(OrderStatus status) {
		this.status = status;
	}

}
