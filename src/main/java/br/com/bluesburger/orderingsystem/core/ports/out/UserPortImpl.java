package br.com.bluesburger.orderingsystem.core.ports.out;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.bluesburger.orderingsystem.adapters.out.repository.UserRepository;
import br.com.bluesburger.orderingsystem.core.domain.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {

    private final UserRepository userRepository;

    @Override
    public User getUserByCpf(String cpf) {
//        Optional<User> recoveredUser = Optional.ofNullable(userRepository.findUserByCpf(cpf));
//        return recoveredUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return userRepository.getUserByCpf(cpf);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserByCpf(User user) {
    	if (user.getCpf() != null) {
	        var userRecovery = getUserByCpf(user.getCpf());
	        updateuser(user, userRecovery);
    	} else {
    		// TODO: create anonymous user
    	}
        return userRepository.save(user);
    }

    private void updateuser(User userSource, User userTarget) {

        userSource.getOrders().forEach(userTarget::addOrder);
    }

}
