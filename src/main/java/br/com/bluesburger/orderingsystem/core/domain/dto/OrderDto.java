package br.com.bluesburger.orderingsystem.core.domain.dto;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class OrderDto implements Serializable {

    private static final long serialVersionUID = -9204013197937217047L;

    @NonNull
    private Long id;

    @NonNull
    private OrderStatus status;

    @NonNull
    private LocalDateTime createdTime;

    @NonNull
    private LocalDateTime updatedTime;

    @NonNull
    private List<DishDto> dishes;

    @NonNull
    private List<DessertDto> desserts;

    @NonNull
    private List<DrinkDto> drinks;

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
