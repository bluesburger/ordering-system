package br.com.bluesburger.orderingsystem.core.services.objectsmother;

import br.com.bluesburger.orderingsystem.core.domain.User;

public class UserMother {

    public static User buildAnonymousUserMock() {
        return User.builder()
                .id(1L)
                .cpf("")
                .identified_user(Boolean.FALSE)
                .build();
    }

    public static User buildUserMock() {
        return User.builder()
                .id(2L)
                .cpf("45206417050")
                .identified_user(Boolean.TRUE)
                .build();
    }
}
