package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private String message;
}
