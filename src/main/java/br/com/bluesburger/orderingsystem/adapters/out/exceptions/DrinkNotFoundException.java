package br.com.bluesburger.orderingsystem.adapters.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DrinkNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -1364796507701220119L;

	public DrinkNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Drink not found");
	}
}
