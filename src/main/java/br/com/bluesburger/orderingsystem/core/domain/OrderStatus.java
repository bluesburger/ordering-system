package br.com.bluesburger.orderingsystem.core.domain;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum OrderStatus {
	 PEDIDO_REALIZADO("Pedido Realizado"),
	 PREPARO_INICIADO("Preparo Iniciado"),
	 PEDIDO_PRONTO("Pedido Pronto"),
	 PEDIDO_RECEBIDO("Pedido Recebido"),
	 PEDIDO_EM_PREPARACAO("Pedido em Preparação");

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
