package br.com.bluesburger.orderingsystem.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity(name = "user_table")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 5909937819028217891L;

    @Id
    private String cpf;

    @NonNull
    private Boolean identified_user;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        this.orders.add(order);
    }
}

