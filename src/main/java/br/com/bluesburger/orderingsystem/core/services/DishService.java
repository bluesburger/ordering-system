package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;
	
	public List<Dish> listAll() {
		return dishRepository.findAll();
	}
	
	public Optional<Dish> getById(Long id) {
		return dishRepository.findById(id);
	}
	
	public Optional<Dish> save(Dish dish) {
		return Optional.of(dishRepository.save(dish));
	}
}
