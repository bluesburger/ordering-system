package br.com.bluesburger.orderingsystem.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;
	
	public List<DishEntity> listAll() {
		return dishRepository.findAll();
	}
	
	public Optional<DishEntity> getById(Long id) {
		return dishRepository.findById(id);
	}
	
	public Optional<DishEntity> save(DishEntity dishEntity) {
		return Optional.of(dishRepository.save(dishEntity));
	}
}
