package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentContextTest {

    @Mock
    private PaymentPixStrategy paymentPixStrategy;

    @Mock
    private PaymentDebitCardStrategy paymentDebitCardStrategy;

    @Mock
    private PaymentCreditCardStrategy paymentCreditCardStrategy;

    @InjectMocks
    private PaymentContext paymentContext;

    @Test
    void devePegarEstrategiaPixComSucesso() {
        Payment payment = Payment.builder().paymentMethod(PaymentMethodEnum.PIX).build();
        when(paymentPixStrategy.checkoutPayment(payment)).thenReturn("Payment pix successful");

        var result = paymentContext.processPayment(payment);

        assertEquals("Payment pix successful", result);
    }

    @Test
    void testProcessPaymentWithInvalidPaymentMethod() {
        Payment payment = Payment.builder().paymentMethod(PaymentMethodEnum.valueOf("BOLETO")).build();

        assertThrows(IllegalArgumentException.class, () -> paymentContext.processPayment(payment));
    }
}
