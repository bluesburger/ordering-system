package br.com.bluesburger.orderingsystem.core.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String cpf;

    private Boolean identified_user;

    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order source) {
        this.orders.add(source);
    }
}
