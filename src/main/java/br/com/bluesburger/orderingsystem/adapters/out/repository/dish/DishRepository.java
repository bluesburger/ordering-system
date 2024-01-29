package br.com.bluesburger.orderingsystem.adapters.out.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.entities.DishEntity;

public interface DishRepository extends JpaRepository<DishEntity, Long> {

}
