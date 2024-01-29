package br.com.bluesburger.orderingsystem.core.domain;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum OrderStatus {
	 PEDIDO_RECEBIDO("Pedido Recebido"),
	 PEDIDO_EM_PREPARACAO("Pedido em Preparação"),
	 PEDIDO_PRONTO("Pedido Pronto"),
	 PEDIDO_FINALIZADO("Pedido Finalizado");
	
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
