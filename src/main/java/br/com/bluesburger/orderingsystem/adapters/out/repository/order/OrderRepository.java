package br.com.bluesburger.orderingsystem.adapters.out.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByStatus(OrderStatus status);

    List<OrderEntity> findAllByUserEntity(UserEntity user);

    @Query("SELECT o FROM tb_order o WHERE o.status <> 'PEDIDO_FINALIZADO' ORDER BY CASE o.status " +
            "WHEN 'PEDIDO_PRONTO' THEN 1 " +
            "WHEN 'PEDIDO_EM_PREPARACAO' THEN 2 " +
            "WHEN 'PEDIDO_RECEBIDO' THEN 3 " +
            "ELSE 4 END, " +
            "o.createdTime DESC"
    )
    List<OrderEntity> findAllOrdered();
}
