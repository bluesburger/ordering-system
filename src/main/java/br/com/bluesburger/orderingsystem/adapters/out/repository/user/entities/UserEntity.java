package br.com.bluesburger.orderingsystem.adapters.out.repository.user.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
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
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5909937819028217891L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @NonNull
    private Boolean identified_user;

    @OneToMany(mappedBy = "userEntity", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("userEntity")
    private List<OrderEntity> orderEntities = new ArrayList<>();

    public void addOrder(OrderEntity orderEntity){
        this.orderEntities.add(orderEntity);
    }
}

