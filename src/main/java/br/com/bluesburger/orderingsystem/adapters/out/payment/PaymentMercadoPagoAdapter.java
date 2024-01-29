package br.com.bluesburger.orderingsystem.adapters.out.payment;

import br.com.bluesburger.orderingsystem.adapters.out.payment.request.PaymentRequestDto;
import br.com.bluesburger.orderingsystem.adapters.out.payment.response.PaymentResponseDto;
import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.ports.out.PaymentMercadoPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static br.com.bluesburger.orderingsystem.adapters.out.payment.request.PaymentRequestFactory.buildPaymentRequest;

@Service
@RequiredArgsConstructor
public class PaymentMercadoPagoAdapter implements PaymentMercadoPagoPort {

    private final String url = "https://api.mercadopago.com/instore/orders/qr/seller/collectors/1613065352/pos/SUC001POS002/qrs";
    private final RestTemplate restTemplate;
    private final String accessToken = "APP_USR-7148288794739918-122720-66b9ac918d470a3bf62df3f0e6030f15-1613065352";

    @Override
    public PaymentResponseDto generatePaymentQRCode(Payment payment) {
        try {
            final var headers = BuildHeaders();
            final var requestEntity = buildRequest(payment, headers);

            var response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PaymentResponseDto.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu algum erro na geração do QR code. ", e);
        }
    }

    private HttpHeaders BuildHeaders() {
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<PaymentRequestDto> buildRequest(Payment payment, HttpHeaders headers) {
        var requestEntity = buildPaymentRequest(payment);

        return new HttpEntity<>(requestEntity, headers);
    }
}
