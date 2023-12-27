package br.com.bluesburger.orderingsystem.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "drink")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drink implements Serializable {

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

    @Setter
    private BigDecimal price;

    public void applyFivePercentDiscount() {
        BigDecimal discount = price.multiply(BigDecimal.valueOf(0.05));
        price = price.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

}
