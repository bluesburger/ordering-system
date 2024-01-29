package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class DessertRequest implements ProductDto, Serializable {

    private static final long serialVersionUID = 6337428207930359497L;

    @NonNull
    private final Long id;

    private final Integer quantity;
}
