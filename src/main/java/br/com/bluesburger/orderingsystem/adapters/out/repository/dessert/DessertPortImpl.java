package br.com.bluesburger.orderingsystem.adapters.out.repository.dessert;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.DessertNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.mapper.DessertMapper;
import br.com.bluesburger.orderingsystem.core.domain.Dessert;
import br.com.bluesburger.orderingsystem.ports.out.DessertPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.bluesburger.orderingsystem.adapters.out.repository.dessert.mapper.DessertMapper.dessertToDessertEntity;

@Component
@Transactional
public class DessertPortImpl implements DessertPort {

    @Autowired
    private DessertRepository dessertRepository;

    @Override
    public Dessert findById(Long id) {
        var dessertEntityOptional = dessertRepository.findById(id);
        var dessertEntity = dessertEntityOptional.orElseThrow(DessertNotFoundException::new);

        return dessertToDessertEntity(dessertEntity);
    }

    @Override
    public List<Dessert> findAll() {
        var dessertEntityList = dessertRepository.findAll();

        return dessertEntityList.stream()
                .map(DessertMapper::dessertToDessertEntity)
                .collect(Collectors.toList());
    }
}
