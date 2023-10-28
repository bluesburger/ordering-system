package br.com.bluesburger.orderingsystem.adapters.out.repository;

import br.com.bluesburger.orderingsystem.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findUserByCpf(String cpf);

}
