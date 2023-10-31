package br.com.bluesburger.orderingsystem.core.domain;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderSituationIllegal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 4781858089323528412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PEDIDO_REALIZADO;

	@ManyToMany
	private List<Dish> dishes = new ArrayList<>();

	@ManyToMany
	private List<Dessert> desserts = new ArrayList<>();
	
	@ManyToMany
	private List<Drink> drinks = new ArrayList<>();

	@Convert(converter = CpfConverter.class)
	@ManyToOne
	@JsonIgnoreProperties("orders")
	@JoinColumn(name = "user_id")
	private User user;

	public void add(Dish... newDishes) {
		verifyIfCanModifyItems();
		Stream.of(newDishes).forEach(dish -> dishes.add(dish));
	}
	
	public void remove(Dish dish) {
		verifyIfCanModifyItems();
		dishes.remove(dish);
	}
	
	public void add(Dessert... newDesserts) {
		verifyIfCanModifyItems();
		Stream.of(newDesserts).forEach(dish -> desserts.add(dish));
	}
	
	public void remove(Dessert dessert) {
		verifyIfCanModifyItems();
		desserts.remove(dessert);
	}
	
	public void add(Drink... newDrinks) {
		verifyIfCanModifyItems();
		Stream.of(newDrinks).forEach(dish -> drinks.add(dish));
	}
	
	public void remove(Drink drink) {
		verifyIfCanModifyItems();
		drinks.remove(drink);
	}
	
	private void verifyIfCanModifyItems() {
		if (!OrderStatus.PEDIDO_REALIZADO.equals(this.status)) {
			throw new OrderSituationIllegal();
		}
	}
	
	public void start() {
		setStatusIfAllowed(OrderStatus.PREPARO_INICIADO);
	}
	
	public void ready() {
		setStatusIfAllowed(OrderStatus.PREPARO_PRONTO);
	}
	
	public void complete() {
		setStatusIfAllowed(OrderStatus.PEDIDO_ENTREGUE);
	}
	
	private void setStatusIfAllowed(OrderStatus newStatus) {
		switch (newStatus) {
		case PEDIDO_REALIZADO:
			currentStatusNeedsToBe(OrderStatus.PEDIDO_REALIZADO);
			currentStatusISAlready(newStatus);
			break;
		case PREPARO_INICIADO:
			currentStatusNeedsToBe(OrderStatus.PEDIDO_REALIZADO);
			currentStatusISAlready(newStatus);
			break;
		case PREPARO_PRONTO:
			currentStatusNeedsToBe(OrderStatus.PREPARO_INICIADO);
			currentStatusISAlready(newStatus);
			break;
		case PEDIDO_ENTREGUE:
			currentStatusNeedsToBe(OrderStatus.PREPARO_PRONTO);
			currentStatusISAlready(newStatus);
			break;
		}
		this.status = newStatus;
	}
	
	/**
	 * Verifica se status atual já se encontra na situação
	 * @param target
	 */
	private void currentStatusISAlready(OrderStatus target) {
		if (target.equals(this.status)) {
			throw new OrderSituationIllegal();
		}
	}
	
	/**
	 * Verifica se a reaga status atual é equivalente ao esperado
	 * @param target
	 */
	private void currentStatusNeedsToBe(OrderStatus target) {
		if (!target.equals(this.status)) {
			throw new OrderSituationIllegal();
		}
	}
}
