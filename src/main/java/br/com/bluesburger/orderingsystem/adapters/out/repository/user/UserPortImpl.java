package br.com.bluesburger.orderingsystem.adapters.out.repository.user;

import br.com.bluesburger.orderingsystem.adapters.out.repository.user.mapper.UserMapper;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import br.com.bluesburger.orderingsystem.ports.out.UserPort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import static br.com.bluesburger.orderingsystem.adapters.out.repository.user.mapper.UserMapper.userToUserEntity;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {

    private final UserRepository userRepository;

    @Override
    public User getUserByCpf(String cpf) {
        return userToUserEntity(userRepository.getUserByCpf(cpf));
    }

    @Override
    public User saveUser(User userObj) {
        var userEntity = UserEntity.builder()
                .id(userObj.getId())
                .cpf(userObj.getCpf())
                .identified_user(userObj.getIdentified_user())
                .build();

        var userEntityResponse =  userRepository.save(userEntity);

        return userToUserEntity(userEntityResponse);
    }

    @Override
    public User updateUserByCpf(User user) {
    	if (user.getCpf() != null) {
	        var userRecovery = getUserByCpf(user.getCpf());
	        updateuser(user, userRecovery);
    	}
    	var userEntity = UserMapper.userEntityToUser(user);
        var userEntityResponse =  userRepository.save(userEntity);

        return userToUserEntity(userEntityResponse);
    }

    private void updateuser(User userSource, User userTarget) {
        userSource.getOrders().forEach(userTarget::addOrder);
    }
}
