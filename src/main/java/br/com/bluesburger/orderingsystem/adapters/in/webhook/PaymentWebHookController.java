package br.com.bluesburger.orderingsystem.adapters.in.webhook;

import br.com.bluesburger.orderingsystem.adapters.in.webhook.dto.Event;
import br.com.bluesburger.orderingsystem.ports.in.OrderProcessingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentWebHookController {

    private final OrderProcessingServicePort orderPort;

    @PostMapping("/webhook/payments")
    public ResponseEntity<HttpStatus> processPayment(@RequestBody Event event) {
        if (isNull(event.getType())) {
            return ResponseEntity.noContent().build();
        }

        var orderId = Long.valueOf(event.getData().getId());
        var order = orderPort.startOrder(orderId);

        //TODO - adicionar futuramente envio para uma fila de notificação a cozinha
        //sqsPort.notifyKitchen();

        return ResponseEntity.ok().build();
    }
}
