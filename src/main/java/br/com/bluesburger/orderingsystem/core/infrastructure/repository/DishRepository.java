package br.com.bluesburger.orderingsystem.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
