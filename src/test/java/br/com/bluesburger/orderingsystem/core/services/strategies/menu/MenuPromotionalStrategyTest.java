package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.adapters.out.repository.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;
import br.com.bluesburger.orderingsystem.core.ports.out.OrderPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static br.com.bluesburger.orderingsystem.objectsmother.MenuMother.*;
import static br.com.bluesburger.orderingsystem.objectsmother.OrderMother.buildOrderListMock;
import static br.com.bluesburger.orderingsystem.objectsmother.UserMother.buildUserMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuPromotionalStrategyTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private DessertRepository dessertRepository;

    @Mock
    private OrderPort orderPort;

    @InjectMocks
    private MenuPromotionalStrategy menuPromotionalStrategy;

    @Test
    void deveExibirMenuPromotionalComDescontosSucesso() {
        final var user = buildUserMock();
        when(dishRepository.findAll()).thenReturn(buildDishesMock(user));
        when(drinkRepository.findAll()).thenReturn(buildDrinksMock());
        when(dessertRepository.findAll()).thenReturn(buildDessertMock());
        when(orderPort.getOrdersByUser(user)).thenReturn(buildOrderListMock(12));

        final var response = menuPromotionalStrategy.showMenu(user);

        assertEquals(new BigDecimal("8.10"), response.getDishes().get(0).getPrice());
        assertEquals(new BigDecimal("9.50"), response.getDrinks().get(0).getPrice());
        assertEquals(new BigDecimal("8.50"), response.getDesserts().get(0).getPrice());
    }

    @Test
    void deveExibirMenuPromotionalSemDescontosSucesso() {
        final var user = buildUserMock();
        when(dishRepository.findAll()).thenReturn(buildDishesMock(user));
        when(drinkRepository.findAll()).thenReturn(buildDrinksMock());
        when(dessertRepository.findAll()).thenReturn(buildDessertMock());
        when(orderPort.getOrdersByUser(user)).thenReturn(buildOrderListMock(3));

        final var response = menuPromotionalStrategy.showMenu(user);

        assertEquals(new BigDecimal("9"), response.getDishes().get(0).getPrice());
        assertEquals(new BigDecimal("10"), response.getDrinks().get(0).getPrice());
        assertEquals(new BigDecimal("10"), response.getDesserts().get(0).getPrice());
    }

    @Test
    void deveLancarRuntimeExceptionShowMenuPromotionalError() {
        final var user = buildUserMock();
        when(orderPort.getOrdersByUser(user)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> menuPromotionalStrategy.showMenu(user));
    }
}
