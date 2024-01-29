package br.com.bluesburger.orderingsystem.adapters.out.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentResponseDto {

    @JsonProperty("in_store_order_id")
    private String orderId;

    @JsonProperty("qr_data")
    private String qrData;

}
