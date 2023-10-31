package br.com.bluesburger.orderingsystem.core.domain.valueobject;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@RequiredArgsConstructor
@ToString(includeFieldNames = false)
public class Cpf implements Serializable {

	private static final long serialVersionUID = -19776677621521603L;
	
	@NonNull
	@JsonValue
	private String value;
}
