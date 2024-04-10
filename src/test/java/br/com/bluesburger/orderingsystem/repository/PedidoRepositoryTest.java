package br.com.bluesburger.orderingsystem.repository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderPortImpl;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.ports.out.OrderPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


public class PedidoRepositoryTest {

    @Mock
    private OrderRepository pedidoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void deveCadastrarPedido(){
        // Arrange
        OrderItemDishEntity dishEntity1 = mock(OrderItemDishEntity.class);
        OrderItemDishEntity dishEntity2 = mock(OrderItemDishEntity.class);

        OrderItemDessertEntity dessertEntity1 = mock(OrderItemDessertEntity.class);
        OrderItemDessertEntity dessertEntity2 = mock(OrderItemDessertEntity.class);

        OrderItemDrinkEntity drinkEntity1 = mock(OrderItemDrinkEntity.class);
        OrderItemDrinkEntity drinkEntity2 = mock(OrderItemDrinkEntity.class);

        var pedido = OrderEntity.builder()
                .id(1L)
                .status(OrderStatus.PEDIDO_RECEBIDO)
                .totalValue(BigDecimal.ZERO)
                .dishEntities(List.of(dishEntity1, dishEntity2))
                .dessertEntities(List.of(dessertEntity1, dessertEntity2))
                .drinkEntities(List.of(drinkEntity1, drinkEntity2))
                .build();

      when(pedidoRepository.save(pedido)).thenReturn(pedido);

      //ACT
      var pedidoArmazenado = pedidoRepository.save(pedido);

      //Assert
        assertThat(pedidoArmazenado)
                .isNotNull()
                .isEqualTo(pedido);

        verify(pedidoRepository, times(1)).save(pedido);
    }
}
