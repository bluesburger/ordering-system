package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.ports.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public User validateUser(User user) {
        if (!user.getIdentified_user()) {
            return user;
        }

        final var userRecovered = userPort.getUserByCpf(user.getCpf());

        return userRecovered.isEmpty() ? userPort.saveUser(user) : userRecovered.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ocorreu algum problema na bsuca do usuario do banco de dados"));
    }
}
