package br.com.bluesburger.orderingsystem.core.services.strategies.Order;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderPortImpl;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.services.objectsmother.OrderMother;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.bluesburger.orderingsystem.core.services.objectsmother.OrderMother.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;



public class PedidoRepositoryTest {


    OrderMother orderMother = new OrderMother();

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
      var pedido = orderMother.gerarPedido();

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
        var pedido1 = orderMother.gerarPedido();
        var pedido2 = orderMother.gerarPedido();
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
        var pedido = orderMother.gerarPedido();

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
        var pedido = orderMother.gerarPedido();
        doNothing().when(pedidoRepository).deleteById(pedido.getId());

        //ACT
        pedidoRepository.deleteById(pedido.getId());

        //Assert
        verify(pedidoRepository, times(1)).deleteById(pedido.getId());
    }


}

