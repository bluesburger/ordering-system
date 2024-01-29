package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.User;
import org.springframework.stereotype.Service;

import br.com.bluesburger.orderingsystem.ports.out.UserPort;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public User validateUser(User user) {
        final var userRecovered = userPort.getUserByCpf(user.getCpf());

        return isNull(userRecovered.getId()) ? userPort.saveUser(user) : userRecovered;
    }
}
