package br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.*;

import br.com.bluesburger.orderingsystem.adapters.in.product.dto.ProductCategory;
import br.com.bluesburger.orderingsystem.adapters.out.payment.request.UnitMeasureEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "dessert")
@Data
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DessertEntity implements Serializable {

    private static final long serialVersionUID = -4705493288584213744L;

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

    private final String category = ProductCategory.DESSERT.name();

    @Setter
    private BigDecimal price;
}
