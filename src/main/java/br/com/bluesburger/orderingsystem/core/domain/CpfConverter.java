package br.com.bluesburger.orderingsystem.core.domain;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;

@Converter
public class CpfConverter implements AttributeConverter<Cpf, String> {

	@Override
	public String convertToDatabaseColumn(Cpf attribute) {
		if (attribute == null || attribute.getValue() == null) {
			return null;
		}
		return attribute.getValue();
	}

	@Override
	public Cpf convertToEntityAttribute(String dbData) {
		return Optional.ofNullable(dbData)
				.map(Cpf::new)
				.orElse(null);
	}
}
