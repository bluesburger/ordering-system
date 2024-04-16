package br.com.bluesburger.orderingsystem.core.services.objectsmother;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;

public class OrderMother {

    public OrderEntity gerarPedido(){

        OrderItemDishEntity dishEntity1 = mock(OrderItemDishEntity.class);
        OrderItemDishEntity dishEntity2 = mock(OrderItemDishEntity.class);

        OrderItemDessertEntity dessertEntity1 = mock(OrderItemDessertEntity.class);
        OrderItemDessertEntity dessertEntity2 = mock(OrderItemDessertEntity.class);

        OrderItemDrinkEntity drinkEntity1 = mock(OrderItemDrinkEntity.class);
        OrderItemDrinkEntity drinkEntity2 = mock(OrderItemDrinkEntity.class);

        return OrderEntity.builder()
                .id(1L)
                .status(OrderStatus.PEDIDO_RECEBIDO)
                .totalValue(BigDecimal.ZERO)
                .dishEntities(List.of(dishEntity1, dishEntity2))
                .dessertEntities(List.of(dessertEntity1, dessertEntity2))
                .drinkEntities(List.of(drinkEntity1, drinkEntity2))
                .createdTime(LocalDateTime.now())
                .build();
    }

}
