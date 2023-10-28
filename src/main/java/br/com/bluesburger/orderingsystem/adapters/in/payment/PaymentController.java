package br.com.bluesburger.orderingsystem.adapters.in.payment;

import br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentRequest;
import br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentResponse;
import br.com.bluesburger.orderingsystem.core.ports.out.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentMapper.mapperPaymentRequestToPayment;
import static br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentMapper.mapperPaymentToPaymentResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentPort paymentPort;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {

        var payment = mapperPaymentRequestToPayment(paymentRequest);

        final var paymentProcessed = paymentPort.processPayment(payment);
        var paymentResponse = mapperPaymentToPaymentResponse(paymentProcessed);

        return ResponseEntity.ok(paymentResponse);
    }

}
