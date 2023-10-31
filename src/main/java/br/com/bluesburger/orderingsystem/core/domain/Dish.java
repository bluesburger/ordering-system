package br.com.bluesburger.orderingsystem.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "dish")
@Data
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {

	private static final long serialVersionUID = 184546618012661973L;

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

    public void applyTenPercentDiscount() {
        BigDecimal discount = price.multiply(BigDecimal.valueOf(0.1));
        price = price.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

}
