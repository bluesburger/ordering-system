package br.com.bluesburger.orderingsystem.adapters.in.order.dto.response;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = -9204013197937217047L;

    @NonNull
    private Long id;

    @NonNull
    private OrderStatus status;

    @NonNull
    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @NonNull
    private List<OrderItemDishResponse> dishes;

    @NonNull
    private List<OrderItemDessertResponse> desserts;

    @NonNull
    private List<OrderItemDrinkResponse> drinks;

    @NonNull
    private BigDecimal totalValue;

    @NonNull
    @Setter
    @JsonIgnoreProperties("orders")
    private User user;

    public String getStatus() {
        return status.getName();
    }
}
