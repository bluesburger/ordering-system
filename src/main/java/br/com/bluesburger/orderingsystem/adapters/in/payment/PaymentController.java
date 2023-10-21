package br.com.bluesburger.orderingsystem.adapters.in.payment;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.core.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody Payment payment) {
        //mapeamento da classe paymentrequest para a classe payment

        //chamar a classe de serviço fazendo um pedido
        paymentService.processPayment(payment);

        return ResponseEntity.ok("");
    }

}
