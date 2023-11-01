package br.com.bluesburger.orderingsystem.core.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.ports.out.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public User validateUser(User user) {
        final var userRecovered = userPort.getUserByCpf(user.getCpf());

        return Objects.isNull(userRecovered) ? userPort.saveUser(user) : userRecovered;
    }
}
