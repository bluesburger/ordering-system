package br.com.bluesburger.orderingsystem.adapters.out.repository.dish;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishRepository extends JpaRepository<OrderItemDishEntity, Long> {

}
