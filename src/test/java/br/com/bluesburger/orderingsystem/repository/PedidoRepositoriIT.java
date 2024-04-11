package br.com.bluesburger.orderingsystem.repository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PedidoRepositoriIT {


//    @Autowired
//    private OrderRepository pedidoRepository;
//
//    @Test
//    void deveCriarTabela(){
//        var totalDeRegistros = pedidoRepository.count();
//        assertThat(totalDeRegistros).isNotNegative();
//    }
//
//    @Test
//    void deveRegistrarPedido(){
//        //arrange
//        var pedido = gerarPedido();
//
//        //Act
//        var pedidoArmazenado = registrarPedido(pedido);
//
//        //Assert
//        assertThat(pedidoArmazenado)
//                .isInstanceOf(OrderEntity.class)
//                .isNotNull();
//        assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId());
//
//    }
//
//    @Test
//    void deveBuscarPedido(){
//        //Arrange
//        var pedido = gerarPedido();
//        registrarPedido(pedido);
//
//        //Act
//        var pedidoArmazenadoOptional = pedidoRepository.findById(pedido.getId());
//
//        // Assert
//        assertThat(pedidoArmazenadoOptional)
//                .isPresent();
//
//        pedidoArmazenadoOptional.ifPresent(pedidoArmazenado -> {
//            assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId());
//        });
//
//    }
//
//    @Test
//    void deveRemoverPedido(){
//        //Arrange
//        var pedido = gerarPedido();
//        registrarPedido(pedido);
//
//        //Act
//        pedidoRepository.deleteById(pedido.getId());
//        var pedidoRecebidoOptional = pedidoRepository.findById(pedido.getId());
//
//        //Assert
//        assertThat(pedidoRecebidoOptional).isEmpty();
//    }
//
//
//    private OrderEntity gerarPedido(){
//
//        OrderItemDishEntity dishEntity1 = mock(OrderItemDishEntity.class);
//        OrderItemDishEntity dishEntity2 = mock(OrderItemDishEntity.class);
//
//        OrderItemDessertEntity dessertEntity1 = mock(OrderItemDessertEntity.class);
//        OrderItemDessertEntity dessertEntity2 = mock(OrderItemDessertEntity.class);
//
//        OrderItemDrinkEntity drinkEntity1 = mock(OrderItemDrinkEntity.class);
//        OrderItemDrinkEntity drinkEntity2 = mock(OrderItemDrinkEntity.class);
//
//        return OrderEntity.builder()
//                .id(1L)
//                .status(OrderStatus.PEDIDO_RECEBIDO)
//                .totalValue(BigDecimal.ZERO)
//                .dishEntities(List.of(dishEntity1, dishEntity2))
//                .dessertEntities(List.of(dessertEntity1, dessertEntity2))
//                .drinkEntities(List.of(drinkEntity1, drinkEntity2))
//                .createdTime(LocalDateTime.now())
//                .build();
//    }
//
//    private OrderEntity registrarPedido(OrderEntity pedido){
//        return pedidoRepository.save(pedido);
//    }
}
