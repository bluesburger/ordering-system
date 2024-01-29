package br.com.bluesburger.orderingsystem.core.domain.factory;

import br.com.bluesburger.orderingsystem.core.domain.User;

import java.util.Map;

public class UserFactory {

    public static User buildUser(Map<String, String> params) {
        return User.builder()
                .cpf(params.get("cpf"))
                .identified_user(Boolean.valueOf(params.get("user_identified")))
                .build();
    }
}
