package br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DessertRequest;
import org.springframework.stereotype.Component;

@Component
public class DessertMapper {

    public DessertRequest toDto(DessertEntity dessertEntity) {
        return new DessertRequest(
                dessertEntity.getId(),
                null
        );
    }
}
