package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    private String cpf;

    private Boolean identified_user;
}
