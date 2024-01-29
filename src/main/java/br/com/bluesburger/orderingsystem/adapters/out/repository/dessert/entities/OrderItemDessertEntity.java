package br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "tb_dessert_order")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDessertEntity implements Serializable {

    private static final long serialVersionUID = -2366794715249358172L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DessertEntity dessert;

    @NonNull
    private Integer quantity;

    @NonNull
    private BigDecimal totalAmount;
}
