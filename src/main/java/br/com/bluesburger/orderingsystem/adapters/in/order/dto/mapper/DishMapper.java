package br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DishRequest;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishRequest toDto(DishEntity dishEntity) {
        return new DishRequest(
                dishEntity.getId(),
                null
        );
    }
}
