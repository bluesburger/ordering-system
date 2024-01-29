package br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "tb_drink_order")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDrinkEntity implements Serializable {

    private static final long serialVersionUID = 3994897609931291623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DrinkEntity drink;

    @NonNull
    private Integer quantity;

    @NonNull
    private BigDecimal totalAmount;
}
