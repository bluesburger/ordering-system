package br.com.bluesburger.orderingsystem.adapters.in.dish;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DishNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DishRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.DishMapper;
import br.com.bluesburger.orderingsystem.core.services.DishService;

@RestController
@RequestMapping(value = "/api/v1/dish")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DishMapper dishMapper;
	
	@GetMapping
	@ResponseBody
	public List<DishRequest> listAll() {
		return dishService.listAll().stream()
				.map(dishMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{dishId}")
	@ResponseBody
	public DishRequest getDishById(@PathVariable Long dishId) {
		var dish = dishService.getById(dishId).orElseThrow(DishNotFoundException::new);
		return dishMapper.toDto(dish);
	}
}
