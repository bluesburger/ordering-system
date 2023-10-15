package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.core.ports.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public User validateUser(User user) {
        if (!user.getIdentified_user()) {
            return user;
        }

        final var userRecovered = userPort.getUserByCpf(user.getCpf());

        return userRecovered.isEmpty() ? userPort.saveUser(user) : userRecovered.get();
    }
}
