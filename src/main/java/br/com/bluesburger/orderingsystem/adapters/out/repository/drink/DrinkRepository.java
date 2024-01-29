package br.com.bluesburger.orderingsystem.adapters.out.repository.drink;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.entities.DrinkEntity;

public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {

}
