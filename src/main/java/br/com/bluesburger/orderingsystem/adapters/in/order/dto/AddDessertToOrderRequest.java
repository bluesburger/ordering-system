package br.com.bluesburger.orderingsystem.adapters.in.order.dto;

import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
public class AddDessertToOrderRequest {

	@NonNull
	private Long orderId;
	
	@NonNull
	private DessertDto dessert;
}
