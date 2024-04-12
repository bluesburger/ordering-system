package br.com.bluesburger.orderingsystem.repository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderItemDessert;
import br.com.bluesburger.orderingsystem.core.domain.OrderItemDish;
import br.com.bluesburger.orderingsystem.core.domain.OrderItemDrink;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PedidoRepositoriIT {


    @Autowired
    private OrderRepository pedidoRepository;

    @BeforeEach

    @Test
    void deveCriarTabela(){
        var totalDeRegistros = pedidoRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    //@Sql("/data.sql")
    void deveRegistrarPedido(){
        //arrange
        var pedido = gerarPedido();

        //Act
        var pedidoArmazenado = registrarPedido(pedido);

        //Assert
        assertThat(pedidoArmazenado)
                .isInstanceOf(OrderEntity.class)
                .isNotNull();
        assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId());

    }

    @Test
    void deveBuscarPedido(){
        //Arrange
        var pedido = gerarPedido();
        registrarPedido(pedido);

        //Act
        var pedidoArmazenadoOptional = pedidoRepository.findById(pedido.getId());

        // Assert
        assertThat(pedidoArmazenadoOptional)
                .isPresent();

        pedidoArmazenadoOptional.ifPresent(pedidoArmazenado -> {
            assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId());
        });

    }

    @Test
    void deveRemoverPedido(){
        //Arrange
        var pedido = gerarPedido();
        registrarPedido(pedido);

        //Act
        pedidoRepository.deleteById(pedido.getId());
        var pedidoRecebidoOptional = pedidoRepository.findById(pedido.getId());

        //Assert
        assertThat(pedidoRecebidoOptional).isEmpty();
    }


    private OrderEntity gerarPedido(){
        List<OrderEntity> pedidoEntities = pedidoRepository.findAll();
        return pedidoEntities.isEmpty() ? null : pedidoEntities.get(0);
    }

    private OrderEntity registrarPedido(OrderEntity pedido){
        return pedidoRepository.save(pedido);
    }
}
