package br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "tb_dish_order")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDishEntity implements Serializable {

    private static final long serialVersionUID = 4264027616126450798L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DishEntity dish;

    @NonNull
    private Integer quantity;

    @NonNull
    private BigDecimal totalAmount;
}
