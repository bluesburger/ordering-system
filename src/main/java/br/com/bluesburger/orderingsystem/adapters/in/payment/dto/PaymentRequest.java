package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.adapters.in.order.dto.request.CommandRequest;
import br.com.bluesburger.orderingsystem.core.services.strategies.payment.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    @JsonProperty(value = "total_value")
    private BigDecimal totalValue;

    @JsonProperty(value = "payment_method")
    private PaymentMethodEnum paymentMethod;

    @JsonProperty(value = "order")
    private CommandRequest order;
}
