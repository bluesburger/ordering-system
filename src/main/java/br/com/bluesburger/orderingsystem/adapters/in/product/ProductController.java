package br.com.bluesburger.orderingsystem.adapters.in.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.in.product.dto.ProductCategory;
import br.com.bluesburger.orderingsystem.core.domain.dto.ProductDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DessertMapper;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DishMapper;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DrinkMapper;
import br.com.bluesburger.orderingsystem.core.services.DessertService;
import br.com.bluesburger.orderingsystem.core.services.DishService;
import br.com.bluesburger.orderingsystem.core.services.DrinkService;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DessertMapper dessertMapper;
	
	@Autowired
	private DishMapper dishMapper;
	
	@Autowired
	private DrinkMapper drinkMapper;

	@GetMapping
	@ResponseBody
	public List<? extends ProductDto> listByCategory(@RequestParam ProductCategory category) {
		switch(category) {
			case DESSERT: {
				return dessertService.listAll().stream()
						.map(dessert -> dessertMapper.toDto(dessert))
						.toList();
			}
			case DISH: {
				return dishService.listAll().stream()
						.map(dishMapper::toDto)
						.toList();
			}
			case DRINK: {
				return drinkService.listAll().stream()
						.map(drinkMapper::toDto)
						.collect(Collectors.toList());
			}
		}
		return List.of();
	}
}
