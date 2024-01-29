package br.com.bluesburger.orderingsystem.core.domain;

import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderSituationIllegal;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private BigDecimal totalValue;

    private OrderStatus status;

    private List<OrderItemDish> dishes = new ArrayList<>();

    private List<OrderItemDessert> desserts = new ArrayList<>();

    private List<OrderItemDrink> drinks = new ArrayList<>();

    private User user;

    public void add(Dish... newDishes) {
        verifyIfCanModifyItems();
//        dishes.addAll(Arrays.asList(newDishes));
    }

    public void remove(Dish dish) {
        verifyIfCanModifyItems();
        dishes.remove(dish);
    }

    public void add(Dessert... newDessert) {
        verifyIfCanModifyItems();
//        desserts.addAll(Arrays.asList(newDessert));
    }

    public void remove(Dessert dessert) {
        verifyIfCanModifyItems();
        desserts.remove(dessert);
    }

    public void add(Drink... newDrinkEntities) {
        verifyIfCanModifyItems();
//        drinks.addAll(Arrays.asList(newDrinkEntities));
    }

    public void remove(Drink drink) {
        verifyIfCanModifyItems();
        drinks.remove(drink);
    }

    private void verifyIfCanModifyItems() {
        if (!OrderStatus.PEDIDO_RECEBIDO.equals(this.status)) {
            throw new OrderSituationIllegal();
        }
    }

    public void start() {
        setStatusIfAllowed(OrderStatus.PEDIDO_EM_PREPARACAO);
    }

    public void ready() {
        setStatusIfAllowed(OrderStatus.PEDIDO_PRONTO);
    }

    public void complete() {
        setStatusIfAllowed(OrderStatus.PEDIDO_FINALIZADO);
    }

    private void setStatusIfAllowed(OrderStatus newStatus) {
        switch (newStatus) {
            case PEDIDO_RECEBIDO:
//                currentStatusNeedsToBe(OrderStatus.PEDIDO_REALIZADO);
                currentStatusISAlready(newStatus);
                break;
            case PEDIDO_EM_PREPARACAO:
//                currentStatusNeedsToBe(OrderStatus.PEDIDO_REALIZADO);
                currentStatusISAlready(newStatus);
                break;
            case PEDIDO_PRONTO:
//                currentStatusNeedsToBe(OrderStatus.PEDIDO_EM_PREPARACAO);
                currentStatusISAlready(newStatus);
                break;
            case PEDIDO_FINALIZADO:
//                currentStatusNeedsToBe(OrderStatus.PREPARO_PRONTO);
                currentStatusISAlready(newStatus);
                break;
        }
        this.status = newStatus;
    }

    /**
     * Verifica se status atual já se encontra na situação
     *
     * @param target
     */
    private void currentStatusISAlready(OrderStatus target) {
        if (target.equals(this.status)) {
            throw new OrderSituationIllegal();
        }
    }

    /**
     * Verifica se a reagra de status atual é equivalente ao esperado
     *
     * @param target
     */
//    private void currentStatusNeedsToBe(OrderStatus target) {
//        if (!target.equals(this.status)) {
//            throw new OrderSituationIllegal();
//        }
//    }
}
