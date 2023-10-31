package br.com.bluesburger.orderingsystem.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

}
