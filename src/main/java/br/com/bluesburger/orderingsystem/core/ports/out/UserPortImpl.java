package br.com.bluesburger.orderingsystem.core.ports.out;

import br.com.bluesburger.orderingsystem.adapters.out.repository.UserRepository;
import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByCpf(String cpf) {
        return userRepository.findUserByCpf(cpf);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
