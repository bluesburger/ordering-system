package br.com.bluesburger.orderingsystem.core.domain.mapper;

import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;

@Component
public class DessertMapper {

	public DessertDto toDto(Dessert dessert) {		
		return new DessertDto(dessert.getId(), dessert.getCreatedTime(), dessert.getUpdatedTime(), dessert.getName(), dessert.getPrice());
	}
}
