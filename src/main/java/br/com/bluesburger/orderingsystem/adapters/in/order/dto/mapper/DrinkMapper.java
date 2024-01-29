package br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DrinkRequest;

@Component
public class DrinkMapper {

	public DrinkRequest toDto(DrinkEntity drinkEntity) {
		return new DrinkRequest(
				drinkEntity.getId(),
				drinkEntity.getQuantity()
		);
	}
}
