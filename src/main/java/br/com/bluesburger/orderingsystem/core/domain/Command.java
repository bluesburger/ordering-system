package br.com.bluesburger.orderingsystem.core.domain;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    @Setter
    private OrderStatus status;

    @NonNull
    private List<Dish> dishes;

    @NonNull
    private List<Drink> drinks;

    @NonNull
    private List<Dessert> desserts;

    @NonNull
    private User user;
}
