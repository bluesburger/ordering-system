package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

import lombok.*;

import java.io.Serializable;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DrinkRequest implements ProductDto, Serializable {

    private static final long serialVersionUID = -2380385291965737284L;

    @NonNull
    private Long id;

    @NonNull
    private Integer quantity;
}
