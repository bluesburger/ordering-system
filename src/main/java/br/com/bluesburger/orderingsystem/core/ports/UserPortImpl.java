package br.com.bluesburger.orderingsystem.core.ports;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.bluesburger.orderingsystem.core.domain.User;

@Component
public class UserPortImpl implements UserPort {

	@Override
	public Optional<User> getUserByCpf(String cpf) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
