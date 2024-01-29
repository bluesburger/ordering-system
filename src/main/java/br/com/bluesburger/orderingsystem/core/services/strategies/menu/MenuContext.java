package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.Menu;

@Component
@RequiredArgsConstructor
public class MenuContext {

    private final MenuDefaultStrategy menuDefaultStrategy;
    private final MenuPromotionalStrategy menuPromotionalStrategy;

    public Menu showMenu(User user) {
        if (user.getIdentified_user()) {
            return menuPromotionalStrategy.showMenu(user);
        }
        return menuDefaultStrategy.showMenu(user);
    }
}
