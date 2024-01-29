package br.com.bluesburger.orderingsystem.adapters.out.payment.request;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRequestFactory {

    public static PaymentRequestDto buildPaymentRequest(Payment payment) {
        return PaymentRequestDto.builder()
                .title(String.format("Order %s", payment.getOrder().getId()))
                .description("Payment Order Blues Burguer")
                .externalReference(payment.getOrder().getId().toString())
                .items(buildItemsPayment(payment.getOrder()))
                .notificationUrl("https://webhook.site/ac1a92dd-3da8-45e0-90ca-2ba7dac033a8")
                .totalAmount(payment.getTotalValue())
                .build();
    }

    private static List<ItemRequestDto> buildItemsPayment(Order order) {
        var items = new ArrayList<ItemRequestDto>();

        items.addAll(order.getDishes().stream().map(dishItem -> ItemRequestDto.builder()
                .title(dishItem.getDish().getName())
                .description(dishItem.getDish().getDescription())
                .category(dishItem.getDish().getCategory())
                .quantity(dishItem.getQuantity())
                .unitPrice(dishItem.getDish().getPrice())
                .unitMeasure(UnitMeasureEnum.UNIT.name())
                .totalAmount(dishItem.getTotalAmount())
                .build()).collect(Collectors.toList()));

        items.addAll(order.getDrinks().stream().map(drinkItem -> ItemRequestDto.builder()
                .title(drinkItem.getDrink().getName())
                .description(drinkItem.getDrink().getDescription())
                .category(drinkItem.getDrink().getCategory())
                .quantity(drinkItem.getQuantity())
                .unitPrice(drinkItem.getDrink().getPrice())
                .unitMeasure(UnitMeasureEnum.UNIT.name())
                .totalAmount(drinkItem.getTotalAmount())
                .build())
                .collect(Collectors.toList()));

        items.addAll(order.getDesserts().stream().map(dessertItem -> ItemRequestDto.builder()
                .title(dessertItem.getDessert().getName())
                .description(dessertItem.getDessert().getDescription())
                .category(dessertItem.getDessert().getCategory())
                .quantity(dessertItem.getQuantity())
                .unitPrice(dessertItem.getDessert().getPrice())
                .unitMeasure(UnitMeasureEnum.UNIT.name())
                .totalAmount(dessertItem.getTotalAmount())
                .build()).collect(Collectors.toList()));

        return items;
    }
}
