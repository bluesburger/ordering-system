package br.com.bluesburger.orderingsystem.adapters.out.repository.drink;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DrinkNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.drink.mapper.DrinkMapper;
import br.com.bluesburger.orderingsystem.core.domain.Drink;
import br.com.bluesburger.orderingsystem.ports.out.DrinkPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class DrinkPortImpl implements DrinkPort {

    @Autowired
    private DrinkRepository drinkRequest;

    @Override
    public Drink findById(Long id) {
        var drinkEntityOptional = drinkRequest.findById(id);
        var drinkEntity = drinkEntityOptional.orElseThrow(DrinkNotFoundException::new);

        return DrinkMapper.drinkToDrinkEntity(drinkEntity);
    }

    @Override
    public List<Drink> findAll() {
        var dishEntityList = drinkRequest.findAll();

        return dishEntityList.stream()
                .map(DrinkMapper::drinkToDrinkEntity)
                .collect(Collectors.toList());
    }
}
