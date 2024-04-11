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
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
      //Arrange
      var pedido = gerarPedido();

      when(pedidoRepository.save(any(OrderEntity.class))).thenReturn(pedido);

      //ACT
      var pedidoArmazenado = pedidoRepository.save(pedido);

      //Assert
        assertThat(pedidoArmazenado)
                .isNotNull()
                .isEqualTo(pedido);

        verify(pedidoRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void deveListarPedidos(){
        //Arrange
        var pedido1 = gerarPedido();
        var pedido2 = gerarPedido();
        var listaPedidos = Arrays.asList(
                pedido1,
                pedido2);
        when(pedidoRepository.findAll()).thenReturn(listaPedidos);
        //ACT
        var pedidoArmazenado = pedidoRepository.findAll();
        //Assert
        assertThat(pedidoArmazenado)
                .hasSize(2)
                .containsExactlyInAnyOrder(pedido1,pedido2);
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarPedido() {
        //Arrange
        var pedido = gerarPedido();

        when(pedidoRepository.findById(pedido.getId()))
                .thenReturn(Optional.of(pedido));

        //ACT
        var pedidoArmazenadoOpcional = pedidoRepository.findById(pedido.getId());

        //Assert
        assertThat(pedidoArmazenadoOpcional)
                .isPresent()
                .containsSame(pedido);
        pedidoArmazenadoOpcional.ifPresent(pedidoRecebido -> {
            assertThat(pedidoRecebido.getId()).isEqualTo(pedido.getId());
        });

        verify(pedidoRepository, times(1)).findById(pedido.getId());
    }

    @Test
    void deveRemoverPedido(){
        //Arrange
        var pedido = gerarPedido();
        doNothing().when(pedidoRepository).deleteById(pedido.getId());

        //ACT
        pedidoRepository.deleteById(pedido.getId());

        //Assert
        verify(pedidoRepository, times(1)).deleteById(pedido.getId());
    }



    private OrderEntity gerarPedido(){

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

