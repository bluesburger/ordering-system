package br.com.bluesburger.orderingsystem.adapters.in.menu;

import br.com.bluesburger.orderingsystem.adapters.in.menu.dto.MenuResponse;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static br.com.bluesburger.orderingsystem.adapters.in.menu.dto.MenuMapper.mapperMenuToMenuResponse;
import static br.com.bluesburger.orderingsystem.core.domain.factory.UserFactory.buildUser;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/")
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
        // TODO - verificar se o cpf informado é válido
    }

    private User buildUserWithParams(String cpf, String userIdentified) {
        Map<String, String> params = new HashMap<>();
        params.put("cpf", cpf);
        params.put("user_identified", userIdentified);

        return buildUser(params);
    }
}
