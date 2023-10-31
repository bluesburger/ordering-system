package br.com.bluesburger.orderingsystem.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesburger.orderingsystem.core.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	User getUserByCpf(String cpf);

}
