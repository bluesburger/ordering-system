package br.com.bluesburger.orderingsystem.adapters.in.payment;

import br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentRequest;
import br.com.bluesburger.orderingsystem.adapters.in.payment.dto.PaymentResponse;
import br.com.bluesburger.orderingsystem.ports.in.PaymentProcessingServicePort;
import br.com.bluesburger.orderingsystem.core.services.OrderService;
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

    private final OrderService orderService;
    private final PaymentProcessingServicePort paymentPort;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
        var payment = mapperPaymentRequestToPayment(paymentRequest);
        final var order = orderService.getOrderById(payment.getOrder().getId());
        payment.completeOrder(order);

        final var paymentProcessed = paymentPort.processPayment(payment);

        var paymentResponse = mapperPaymentToPaymentResponse(paymentProcessed);
        return ResponseEntity.ok(paymentResponse);
    }

}
