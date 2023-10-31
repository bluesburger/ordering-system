package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;

@Service
public class DrinkService {

	@Autowired
	private DrinkRepository drinkRepository;
	
	public List<Drink> listAll() {
		return drinkRepository.findAll();
	}
	
	public Optional<Drink> getById(Long id) {
		return drinkRepository.findById(id);
	}
	
	public Optional<Drink> save(Drink dish) {
		return Optional.ofNullable(drinkRepository.save(dish));
	}
}
