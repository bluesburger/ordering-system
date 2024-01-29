package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

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
	private DessertRequest dessert;
}
