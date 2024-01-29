package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.User;

public interface UserPort {

    User getUserByCpf(String cpf);

    User saveUser(User user);

    User updateUserByCpf(User user);
}
