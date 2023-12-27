package br.com.bluesburger.orderingsystem.core.services.strategies.menu;

import br.com.bluesburger.orderingsystem.adapters.out.repository.DessertRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DishRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.DrinkRepository;
import br.com.bluesburger.orderingsystem.objectsmother.MenuMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static br.com.bluesburger.orderingsystem.objectsmother.UserMother.buildUserMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuDefaultStrategyTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private DessertRepository dessertRepository;

    @InjectMocks
    private MenuDefaultStrategy menuDefaultStrategy;

    @Test
    @DisplayName("Deve exibir Menu default com sucesso")
    void deveExibirMenuDefaultSucesso() {
        final var user = buildUserMock();
        final var dish = MenuMother.buildDishesMock(user);
        final var drink = MenuMother.buildDrinksMock();
        final var dessert = MenuMother.buildDessertMock();
        when(dishRepository.findAll()).thenReturn(dish);
        when(drinkRepository.findAll()).thenReturn(drink);
        when(dessertRepository.findAll()).thenReturn(dessert);

        final var response = menuDefaultStrategy.showMenu(user);

        assertEquals("falafel", response.getDishes().get(1).getName().toLowerCase());
        assertEquals("coca cola", response.getDrinks().get(0).getName().toLowerCase());
        assertEquals("bolo", response.getDesserts().get(0).getName().toLowerCase());
        assertEquals(BigDecimal.TEN, response.getDishes().get(1).getPrice());
        assertEquals(BigDecimal.TEN, response.getDrinks().get(0).getPrice());
        assertEquals(BigDecimal.TEN, response.getDesserts().get(0).getPrice());
    }

    @Test
    @DisplayName("Deve lancar exception runtime ao capturar excecao")
    void deveLancarRuntimeExceptionShowMenuError() {
        final var user = buildUserMock();
        when(dishRepository.findAll()).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> menuDefaultStrategy.showMenu(user));
    }
}
