package br.com.bluesburger.orderingsystem.core.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.User;

import java.util.Optional;

public interface UserPort {

   User getUserByCpf(String cpf);

    User saveUser(User user);

    User updateUserByCpf(User user);
}
