package br.com.bluesburger.orderingsystem.core.ports;

import br.com.bluesburger.orderingsystem.core.domain.User;

import java.util.Optional;

public interface UserPort {

    Optional<User> getUserByCpf(String cpf);
    User saveUser(User user);
    User updateUser(User user);
}
