package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.core.domain.Menu;
import br.com.bluesburger.orderingsystem.core.domain.User;

public class MenuContext {

    private MenuDefaultStrategy menuDefaultStrategy;
    private MenuPromotionalStrategy menuPromotionalStrategy;

    public Menu showMenu(User user) {
        if (user.getIdentified_user()) {
            return menuPromotionalStrategy.showMenu(user);
        }
        return menuDefaultStrategy.showMenu(user);
    }
}
