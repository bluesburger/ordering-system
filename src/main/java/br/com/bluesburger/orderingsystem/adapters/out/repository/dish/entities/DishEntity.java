package br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.bluesburger.orderingsystem.adapters.in.product.dto.ProductCategory;
import br.com.bluesburger.orderingsystem.adapters.out.payment.request.UnitMeasureEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "tb_dish")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DishEntity implements Serializable {

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

	@NonNull
	private String description;

	private final String category = ProductCategory.DISH.name();

	@Setter
	private BigDecimal price;
}
