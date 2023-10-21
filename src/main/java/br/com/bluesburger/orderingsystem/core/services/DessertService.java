package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.core.infrastructure.repository.DessertRepository;

@Service
public class DessertService {

	@Autowired
	private DessertRepository dessertRepository;
	
	public List<Dessert> listAll() {
		return dessertRepository.findAll();
	}
	
	public Optional<Dessert> getById(Long id) {
		return dessertRepository.findById(id);
	}
	
	public Optional<Dessert> save(Dessert dish) {
		return Optional.ofNullable(dessertRepository.save(dish));
	}
}
