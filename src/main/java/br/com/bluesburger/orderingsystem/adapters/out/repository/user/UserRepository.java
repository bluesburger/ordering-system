package br.com.bluesburger.orderingsystem.adapters.out.repository.user;

import br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity getUserByCpf(String cpf);

}
