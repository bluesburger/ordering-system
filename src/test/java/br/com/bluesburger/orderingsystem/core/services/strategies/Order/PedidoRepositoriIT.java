package br.com.bluesburger.orderingsystem.core.services.strategies.Order;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PedidoRepositoriIT {


    @Autowired
    private OrderRepository pedidoRepository;

    @BeforeEach

    @Test
    void deveCriarTabela(){
        var totalDeRegistros = pedidoRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void deveRegistrarPedido(){
        //arrange
        var pedido = gerarPedido();

        //Act
        var pedidoArmazenado = registrarPedido(pedido);

        //Assert
        assertThat(pedidoArmazenado)
                .isInstanceOf(OrderEntity.class)
                .isNotNull();
        assert pedido != null;
        assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId());

    }

    @Test
    void deveBuscarPedido(){
        //Arrange
        var pedido = gerarPedido();
        registrarPedido(pedido);

        //Act
        assert pedido != null;
        var pedidoArmazenadoOptional = pedidoRepository.findById(pedido.getId());

        // Assert
        assertThat(pedidoArmazenadoOptional)
                .isPresent();

        pedidoArmazenadoOptional.ifPresent(pedidoArmazenado -> assertThat(pedidoArmazenado.getId()).isEqualTo(pedido.getId()));

    }

    @Test
    void deveRemoverPedido(){
        //Arrange
        var pedido = gerarPedido();
        registrarPedido(pedido);

        //Act
        assert pedido != null;
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
