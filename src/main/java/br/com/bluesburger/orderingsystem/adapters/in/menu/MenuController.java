package br.com.bluesburger.orderingsystem.adapters.in.menu;

import static br.com.bluesburger.orderingsystem.adapters.in.menu.dto.MenuMapper.mapperMenuToMenuResponse;
import static br.com.bluesburger.orderingsystem.core.domain.factory.UserFactory.buildUser;
import static java.lang.Character.getNumericValue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesburger.orderingsystem.adapters.in.menu.dto.MenuResponse;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.services.MenuService;
import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<MenuResponse> listDishes(
            @RequestParam(name = "cpf_user", required = false) String cpfUSer,
            @RequestParam(name = "user_identified", required = true) String userIdentified) {

        validateCPF(cpfUSer);
        var user = buildUserWithParams(cpfUSer, userIdentified);
        var menu = menuService.processMenu(user);

        final var menuResponse = mapperMenuToMenuResponse(menu);

        return ResponseEntity.ok().body(menuResponse);

    }

    private void validateCPF(String cpf) {
        if (!cpf.isBlank()) {
            final var cpfFormated = cpf.replaceAll("\\D", "");

            if (cpfFormated.length() != 11) {
                throw new IllegalArgumentException("O CPF informado é inválido! pois não possui 11 dígitos");
            }

            var sum = IntStream.range(0, 9)
                    .map(i -> getNumericValue(cpfFormated.charAt(i)) * (10 - i))
                    .sum();

            var firstVerificationDigit = 11 - (sum % 11);
            if (firstVerificationDigit >= 10) {
                firstVerificationDigit = 0;
            }

            if (getNumericValue(cpfFormated.charAt(9)) != firstVerificationDigit) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            sum = IntStream.range(0, 10)
                    .map(i -> getNumericValue(cpfFormated.charAt(i)) * (11 - i))
                    .sum();

            var secondVerificationDigit = 11 - (sum % 11);
            if (secondVerificationDigit >= 10) {
                secondVerificationDigit = 0;
            }

            if (getNumericValue(cpf.charAt(10)) != secondVerificationDigit) {
                throw new IllegalArgumentException("CPF inválido.");
            }
        }
    }

    private User buildUserWithParams(String cpf, String userIdentified) {
        Map<String, String> params = new HashMap<>();
        params.put("cpf", cpf);
        params.put("user_identified", userIdentified);

        return buildUser(params);
    }
}
