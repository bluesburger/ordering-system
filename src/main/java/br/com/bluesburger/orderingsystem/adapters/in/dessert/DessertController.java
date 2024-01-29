package br.com.bluesburger.orderingsystem.adapters.in.dessert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.DessertRequest;
import br.com.bluesburger.orderingsystem.adapters.in.order.dto.mapper.DessertMapper;
import br.com.bluesburger.orderingsystem.core.services.DessertService;

@RestController
@RequestMapping(value = "/api/v1/dessert")
public class DessertController {
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private DessertMapper dessertMapper;
	
	@GetMapping
	@ResponseBody
	public List<DessertRequest> listAll() {
		return dessertService.listAll().stream()
				.map(dessertMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{dessertId}")
	@ResponseBody
	public DessertRequest getOrderById(@PathVariable Long dessertId) {
		var dessert = dessertService.getById(dessertId).orElseThrow(DrinkNotFoundException::new);
		return dessertMapper.toDto(dessert);
	}
}
