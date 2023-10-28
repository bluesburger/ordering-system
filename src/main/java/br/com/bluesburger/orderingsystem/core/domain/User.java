package br.com.bluesburger.orderingsystem.core.domain;

import br.com.bluesburger.orderingsystem.core.domain.valueobject.Cpf;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_table")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String cpf;

    @NonNull
    private Boolean identified_user;

    @OneToMany
    private List<Order> orders = new ArrayList<>();
}

