package br.com.bluesburger.orderingsystem.adapters.out.repository.dish;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DishNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dish.mapper.DishMapper;
import br.com.bluesburger.orderingsystem.core.domain.Dish;
import br.com.bluesburger.orderingsystem.ports.out.DishPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class DishPortImpl implements DishPort {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish findById(Long id) {
        var dishEntityOptional = dishRepository.findById(id);
        var dishEntity = dishEntityOptional.orElseThrow(DishNotFoundException::new);

        return DishMapper.dishToDishEntity(dishEntity);
    }

    @Override
    public List<Dish> findAll() {
        var dishEntityList = dishRepository.findAll();

        return dishEntityList.stream()
                .map(DishMapper::dishToDishEntity)
                .collect(Collectors.toList());
    }
}
