package br.com.bluesburger.orderingsystem.objectsmother;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.bluesburger.orderingsystem.objectsmother.MenuMother.*;
import static br.com.bluesburger.orderingsystem.objectsmother.UserMother.buildUserMock;
import static java.time.LocalDateTime.now;

public class OrderMother {

    public static List<Order> buildOrderListMock(int iterator) {
        List<Order> orderList = new ArrayList<>();

        for(int i = 1; i < iterator; i++) {
            var user = buildUserMock();

            var order = Order.builder()
                    .id((long) i)
                    .createdTime(now())
                    .dishes(buildDishesMock(user))
                    .drinks(buildDrinksMock())
                    .desserts(buildDessertMock())
                    .totalValue(BigDecimal.valueOf(50))
                    .status(OrderStatus.PEDIDO_REALIZADO)
                    .user(user)
                    .build();

            orderList.add(order);
        }

        return orderList;
    }
}
