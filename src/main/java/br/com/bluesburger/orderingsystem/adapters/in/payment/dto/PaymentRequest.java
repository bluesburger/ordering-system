package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    @JsonProperty(value = "total_value")
    private BigDecimal totalValue;

    @JsonProperty(value = "payment_value")
    private String paymentMethod;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "order")
    private Order order;
}
