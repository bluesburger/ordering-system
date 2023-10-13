package br.com.bluesburger.orderingsystem.adapters.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DessertNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -1364796507701220119L;

	public DessertNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Dessert not found");
	}
}
