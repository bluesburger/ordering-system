package br.com.bluesburger.orderingsystem.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

