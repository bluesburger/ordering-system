package br.com.bluesburger.orderingsystem.adapters.in.dessert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.core.domain.dto.DessertDto;
import br.com.bluesburger.orderingsystem.core.domain.mapper.DessertMapper;
import br.com.bluesburger.orderingsystem.core.infrastructure.service.DessertService;

@RestController
@RequestMapping(value = "/api/dessert")
public class DessertController {
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private DessertMapper dessertMapper;
	
	@GetMapping("/")
	@ResponseBody
	public List<DessertDto> listAll() {
		return dessertService.listAll().stream()
				.map(dessertMapper::toDto)
				.toList();
	}
	
	@GetMapping("/{dessertId}")
	@ResponseBody
	public DessertDto getOrderById(@PathVariable Long dessertId) {
		var dessert = dessertService.getById(dessertId).orElseThrow(DrinkNotFoundException::new);
		return dessertMapper.toDto(dessert);
	}
}
