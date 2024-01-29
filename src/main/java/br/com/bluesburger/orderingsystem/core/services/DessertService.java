package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.DessertRepository;

@Service
public class DessertService {

	@Autowired
	private DessertRepository dessertRepository;
	
	public List<DessertEntity> listAll() {
		return dessertRepository.findAll();
	}
	
	public Optional<DessertEntity> getById(Long id) {
		return dessertRepository.findById(id);
	}
	
	public Optional<DessertEntity> save(DessertEntity dish) {
		return Optional.ofNullable(dessertRepository.save(dish));
	}
}
