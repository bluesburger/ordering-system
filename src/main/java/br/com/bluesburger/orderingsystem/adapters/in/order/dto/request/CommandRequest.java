package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

import java.util.List;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class CommandRequest {

	@NonNull
	private Long id;
	
	@Setter
	private OrderStatus status;
	
	@NonNull
	private List<DishRequest> dishes;
	
	@NonNull
	private List<DessertRequest> desserts;
	
	@NonNull
	private List<DrinkRequest> drinks;

	@NonNull
	private UserRequest user;
}
