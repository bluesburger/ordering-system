package br.com.bluesburger.orderingsystem.core.domain.mapper;

import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;

@Component
public class DrinkMapper {

	public DrinkDto toDto(Drink dish) {
		return new DrinkDto(dish.getId(), dish.getCreatedTime(), dish.getUpdatedTime(), dish.getName(), dish.getPrice());
	}
}
