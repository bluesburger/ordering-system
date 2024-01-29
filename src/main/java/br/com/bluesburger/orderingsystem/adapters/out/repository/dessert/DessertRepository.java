package br.com.bluesburger.orderingsystem.adapters.out.repository.dessert;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.entities.DessertEntity;

public interface DessertRepository extends JpaRepository<DessertEntity, Long> {

}
