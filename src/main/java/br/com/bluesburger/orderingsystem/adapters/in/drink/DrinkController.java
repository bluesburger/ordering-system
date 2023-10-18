package br.com.bluesburger.orderingsystem.adapters.in.drink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.dto.DrinkDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DrinkMapper;
import br.com.bluesburger.orderingsystem.core.infrastructure.service.DrinkService;

@RestController
@RequestMapping(value = "/api/drink")
public class DrinkController {
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DrinkMapper drinkMapper;
	
	@GetMapping("/")
	@ResponseBody
	public List<DrinkDto> listAll() {
		return drinkService.listAll().stream()
				.map(drinkMapper::toDto)
				.toList();
	}
	
	@GetMapping("/{drinkId}")
	@ResponseBody
	public DrinkDto getDishById(@PathVariable Long drinkId) {
		var drink = drinkService.getById(drinkId).orElseThrow(DrinkNotFoundException::new);
		return drinkMapper.toDto(drink);
	}
}
