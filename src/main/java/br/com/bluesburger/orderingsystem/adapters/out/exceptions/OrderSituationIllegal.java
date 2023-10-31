package br.com.bluesburger.orderingsystem.adapters.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderSituationIllegal extends ResponseStatusException {

	private static final long serialVersionUID = -1425508428127277268L;

	public OrderSituationIllegal() {
		super(HttpStatus.FORBIDDEN, "Illegal Situation");
	}
}
