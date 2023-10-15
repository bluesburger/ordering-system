package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String cpf;
    private Boolean identified_user;
}

