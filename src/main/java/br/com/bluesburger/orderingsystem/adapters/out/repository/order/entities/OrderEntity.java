package br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.OrderItemDessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.OrderItemDishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.OrderItemDrinkEntity;
import br.com.bluesburger.orderingsystem.core.domain.CpfConverter;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 4781858089323528412L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @NonNull
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PEDIDO_RECEBIDO;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemDishEntity> dishEntities = new ArrayList<>();

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemDessertEntity> dessertEntities = new ArrayList<>();

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemDrinkEntity> drinkEntities = new ArrayList<>();

    @Convert(converter = CpfConverter.class)
    @ManyToOne
    @JsonIgnoreProperties("orders")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
