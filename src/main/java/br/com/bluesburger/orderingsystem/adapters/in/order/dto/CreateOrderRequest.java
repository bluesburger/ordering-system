package br.com.bluesburger.orderingsystem.adapters.in.order.dto;

import java.util.List;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
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
public class CreateOrderRequest {
	
	@Setter
	private OrderStatus status;
	
	@NonNull
	private List<DishDto> dishes;
	
	@NonNull
	private List<DessertDto> desserts;
	
	@NonNull
	private List<DrinkDto> drinks;

	@NonNull
	@Setter
	private User user;
}
