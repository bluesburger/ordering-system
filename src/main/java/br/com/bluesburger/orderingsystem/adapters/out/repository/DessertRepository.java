package br.com.bluesburger.orderingsystem.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long> {

}
