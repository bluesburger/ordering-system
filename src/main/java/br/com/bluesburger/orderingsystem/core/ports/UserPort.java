package br.com.bluesburger.orderingsystem.core.ports;

import java.util.Optional;

import br.com.bluesburger.orderingsystem.core.domain.User;

public interface UserPort {

    Optional<User> getUserByCpf(String cpf);
    User saveUser(User user);
    User updateUser(User user);
}
