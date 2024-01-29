package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.Drink;

import java.util.List;

public interface DrinkPort {

    Drink findById(Long id);

    List<Drink> findAll();
}
