package br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities;

import br.com.bluesburger.orderingsystem.adapters.in.product.dto.ProductCategory;
import br.com.bluesburger.orderingsystem.adapters.out.payment.request.UnitMeasureEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity(name = "drink")
@Getter
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkEntity implements Serializable {

    private static final long serialVersionUID = 3817656862317795723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @NonNull
    private String name;

    @NonNull
    private String description;

    private Integer quantity;

    private final String category = ProductCategory.DRINK.name();

    @Setter
    private BigDecimal price;
}
