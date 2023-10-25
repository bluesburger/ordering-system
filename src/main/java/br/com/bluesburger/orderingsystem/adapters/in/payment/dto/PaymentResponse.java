package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.core.domain.Dish;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    @JsonProperty("totalAmount")
    private double totalAmount;

}
