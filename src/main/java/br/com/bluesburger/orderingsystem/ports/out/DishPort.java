package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.Dish;

import java.util.List;

public interface DishPort {

    Dish findById(Long id);

    List<Dish> findAll();
}
