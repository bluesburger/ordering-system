package br.com.bluesburger.orderingsystem.core.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class OrderDto implements Serializable {

	private static final long serialVersionUID = -9204013197937217047L;

	@NonNull
	private Long id;
	
	@NonNull
	private OrderStatus status;
	
	@Setter
	private Cpf cpf;
	
	@NonNull
	private LocalDateTime createdTime;
	
	@NonNull
	private List<DishDto> dishes;
	
	@NonNull
	private List<DessertDto> desserts;
	
	@NonNull
	private List<DrinkDto> drinks;
	
	public String getStatus() {
		return status.getName();
	}
}
