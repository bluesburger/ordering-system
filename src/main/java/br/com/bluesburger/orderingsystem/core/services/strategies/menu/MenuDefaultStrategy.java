package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.core.domain.Menu;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.ports.InventoryPort;
import lombok.RequiredArgsConstructor;

import static br.com.bluesburger.orderingsystem.core.domain.factory.MenuFactory.buildMenu;

@RequiredArgsConstructor
public class MenuDefaultStrategy implements MenuStrategy {

    private final InventoryPort inventoryPort;

    @Override
    public Menu showMenu(User user) {
        final var dishList = inventoryPort.getAllDishes();
        final var drinkList = inventoryPort.getAllDrinks();
        final var dessertList = inventoryPort.getAllDesserts();

        return buildMenu(dishList, drinkList, dessertList);
    }
}
