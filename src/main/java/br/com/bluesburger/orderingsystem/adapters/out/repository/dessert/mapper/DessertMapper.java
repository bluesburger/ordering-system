package br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.mapper;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;

public class DessertMapper {

    public static Dessert dessertToDessertEntity(DessertEntity source) {
        return Dessert.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .category(source.getCategory())
                .price(source.getPrice())
                .build();
    }
}
