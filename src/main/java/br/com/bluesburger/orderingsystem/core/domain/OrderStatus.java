package br.com.bluesburger.orderingsystem.core.domain;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum OrderStatus {
	 PEDIDO_REALIZADO("Pedido Realizado"),
	 PREPARO_INICIADO("Preparo Iniciado"),
	 PREPARO_PRONTO("Pedido Pronto"),
	 PEDIDO_ENTREGUE("Pedido Entregue");
	
	private String name;
	
	OrderStatus(String name) {
		this.name = name;
	}

	public static Optional<OrderStatus> from(String status) {
		return Stream.of(OrderStatus.values())
				.filter(s -> s.getName().equals(status))
				.findFirst();
	}
}
