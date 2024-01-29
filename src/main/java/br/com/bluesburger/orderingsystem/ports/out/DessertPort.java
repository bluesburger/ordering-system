package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.Dessert;

import java.util.List;

public interface DessertPort {

    Dessert findById(Long id);

    List<Dessert> findAll();
}
