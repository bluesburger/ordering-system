package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.services.strategies.menu.MenuContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private MenuContext menuContext;

    @InjectMocks
    private MenuService menuService;

    @Test
    void deveExibirMenuDefaultSucesso() {

    }
}
