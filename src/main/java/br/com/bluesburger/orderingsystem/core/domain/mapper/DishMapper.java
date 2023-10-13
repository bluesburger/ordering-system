package br.com.bluesburger.orderingsystem.core.domain.mapper;

import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;

@Component
public class DishMapper {

	public DishDto toDto(Dish dish) {
		return new DishDto(dish.getId(), dish.getCreatedTime(), dish.getUpdatedTime(), dish.getName());
	}
}
