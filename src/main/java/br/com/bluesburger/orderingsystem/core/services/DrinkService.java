package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.DrinkRepository;

@Service
public class DrinkService {

	@Autowired
	private DrinkRepository drinkRepository;
	
	public List<DrinkEntity> listAll() {
		return drinkRepository.findAll();
	}
	
	public Optional<DrinkEntity> getById(Long id) {
		return drinkRepository.findById(id);
	}
	
	public Optional<DrinkEntity> save(DrinkEntity dish) {
		return Optional.ofNullable(drinkRepository.save(dish));
	}
}
