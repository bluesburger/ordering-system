package br.com.bluesburger.orderingsystem.adapters.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DishNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.dto.DishDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DishMapper;
import br.com.bluesburger.orderingsystem.core.infrastructure.service.DishService;

@RestController
@RequestMapping(value = "/api/dish")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DishMapper dishMapper;
	
	@GetMapping("/")
	@ResponseBody
	public List<DishDto> listAll() {
		return dishService.listAll().stream()
				.map(dishMapper::toDto)
				.toList();
	}
	
	@GetMapping("/{dishId}")
	@ResponseBody
	public DishDto getDishById(@PathVariable Long dishId) {
		var dish = dishService.getById(dishId).orElseThrow(DishNotFoundException::new);
		return dishMapper.toDto(dish);
	}
}
