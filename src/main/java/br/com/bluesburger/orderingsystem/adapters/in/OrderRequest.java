package br.com.bluesburger.orderingsystem.adapters.in;

import java.util.List;

import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class OrderRequest {

	@NonNull
	private Long id;
	
	private Cpf cpf;
	
	@NonNull
	private List<DishDto> dishes;
	
	@NonNull
	private List<DessertDto> desserts;
	
	@NonNull
	private List<DrinkDto> drinks;
}
