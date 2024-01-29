package br.com.bluesburger.orderingsystem.adapters.out.repository.drink.mapper;

import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;

public class DrinkMapper {

    public static Drink drinkToDrinkEntity(DrinkEntity source) {
        return Drink.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .price(source.getPrice())
                .build();
    }
}
