package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.adapters.out.repository.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;
import br.com.bluesburger.orderingsystem.core.domain.Menu;
import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.bluesburger.orderingsystem.core.domain.factory.MenuFactory.buildMenu;

@RequiredArgsConstructor
@Service
public class MenuDefaultStrategy implements MenuStrategy {

    private final DishRepository dishRepository;
    private final DrinkRepository drinkRepository;
    private final DessertRepository dessertRepository;

    @Override
    public Menu showMenu(User user) {
        final var dishList = dishRepository.findAll();
        final var drinkList = drinkRepository.findAll();
        final var dessertList = dessertRepository.findAll();

        return buildMenu(dishList, drinkList, dessertList);
    }
}
