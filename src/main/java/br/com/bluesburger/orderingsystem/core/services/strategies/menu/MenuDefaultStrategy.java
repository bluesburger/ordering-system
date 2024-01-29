package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.core.domain.Menu;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.ports.out.DessertPort;
import br.com.bluesburger.orderingsystem.ports.out.DishPort;
import br.com.bluesburger.orderingsystem.ports.out.DrinkPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.bluesburger.orderingsystem.core.domain.factory.MenuFactory.buildMenu;

@RequiredArgsConstructor
@Service
public class MenuDefaultStrategy implements MenuStrategy {

    private final DishPort dishPort;
    private final DrinkPort drinkPort;
    private final DessertPort dessertPort;

    @Override
    public Menu showMenu(User user) {
        final var dishList = dishPort.findAll();
        final var drinkList = drinkPort.findAll();
        final var dessertList = dessertPort.findAll();

        return buildMenu(dishList, drinkList, dessertList);
    }
}
