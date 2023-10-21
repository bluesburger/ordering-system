package br.com.bluesburger.orderingsystem.adapters.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -1364796507701220119L;

	public OrderNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Order not found");
	}
}
