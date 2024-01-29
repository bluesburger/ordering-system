package br.com.bluesburger.orderingsystem.adapters.in.drink;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DrinkRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.DrinkMapper;
import br.com.bluesburger.orderingsystem.core.services.DrinkService;

@RestController
@RequestMapping(value = "/api/v1/drink")
public class DrinkController {
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DrinkMapper drinkMapper;
	
	@GetMapping
	@ResponseBody
	public List<DrinkRequest> listAll() {
		return drinkService.listAll().stream()
				.map(drinkMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{drinkId}")
	@ResponseBody
	public DrinkRequest getDishById(@PathVariable Long drinkId) {
		var drink = drinkService.getById(drinkId).orElseThrow(DrinkNotFoundException::new);
		return drinkMapper.toDto(drink);
	}
}
