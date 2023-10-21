package br.com.bluesburger.orderingsystem.adapters.in.order.dto;

import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
public class AddDrinkToOrderRequest {

	@NonNull
	private Long orderId;
	
	@NonNull
	private DrinkDto drink;
}
