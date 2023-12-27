package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static br.com.bluesburger.orderingsystem.objectsmother.MenuMother.buildMenuMock;
import static br.com.bluesburger.orderingsystem.objectsmother.UserMother.buildAnonymousUserMock;
import static br.com.bluesburger.orderingsystem.objectsmother.UserMother.buildUserMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MenuContextTest {

    @Mock
    private MenuDefaultStrategy menuDefaultStrategy;

    @Mock
    private MenuPromotionalStrategy menuPromotionalStrategy;

    @InjectMocks
    private MenuContext menuContext;


    @Test
    @DisplayName("Deve exibir Menu default com sucesso")
    void deveExibirMenuDefaultSucesso() {
        final var anonymousUser = buildAnonymousUserMock();
        Mockito.when(menuDefaultStrategy.showMenu(anonymousUser)).thenReturn(buildMenuMock(anonymousUser));

        var response = menuContext.showMenu(anonymousUser);

        assertEquals("peixe", response.getDishes().get(0).getName().toLowerCase());
        assertEquals(BigDecimal.TEN, response.getDishes().get(0).getPrice());
    }

    @Test
    @DisplayName("Deve exibir Menu promocional com sucesso")
    void deveExibirMenuPromotionalSucesso() {
        final var user = buildUserMock();
        Mockito.when(menuPromotionalStrategy.showMenu(user)).thenReturn(buildMenuMock(user));

        var response = menuContext.showMenu(user);

        assertEquals("peixe", response.getDishes().get(0).getName().toLowerCase());
        assertEquals(BigDecimal.valueOf(9), response.getDishes().get(0).getPrice());
    }

}
