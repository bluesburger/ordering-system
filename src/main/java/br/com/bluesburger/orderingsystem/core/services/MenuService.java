package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.Menu;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.services.strategies.menu.MenuContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final UserService userService;
    private final MenuContext menuContext;

    public Menu processMenu(User user) {
        var userValidate = userService.validateUser(user);
        return menuContext.showMenu(userValidate);
    }
}
