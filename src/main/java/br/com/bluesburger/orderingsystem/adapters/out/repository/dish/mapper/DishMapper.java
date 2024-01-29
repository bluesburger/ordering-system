package br.com.bluesburger.orderingsystem.adapters.out.repository.dish.mapper;

import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;

public class DishMapper {

    public static Dish dishToDishEntity(DishEntity source) {
        return Dish.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }
}
