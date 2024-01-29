package br.com.bluesburger.orderingsystem.adapters.out.repository.order;


import br.com.bluesburger.orderingsystem.adapters.out.exceptions.OrderNotFoundException;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.entities.OrderEntity;
import br.com.bluesburger.orderingsystem.adapters.out.repository.order.mapper.OrderMapper;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.User;
import br.com.bluesburger.orderingsystem.ports.out.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.bluesburger.orderingsystem.adapters.out.repository.order.mapper.OrderMapper.mapperOrderToOrderEntity;

@Component
@Transactional
public class OrderPortImpl implements OrderPort {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrdersByUser(User user) {
        var orderEntityList = orderRepository.findAllByUserEntity(user);

        return orderEntityList.stream()
                .map(OrderMapper::mapperOrderToOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Order findById(Long orderId) {
        var orderEntityOptional = orderRepository.findById(orderId);
        OrderEntity orderEntity = orderEntityOptional.orElseThrow(OrderNotFoundException::new);

        return mapperOrderToOrderEntity(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        var orderEntityList = orderRepository.findAll();

        return orderEntityList.stream()
                .map(OrderMapper::mapperOrderToOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllCustom() {
        var orderEntityList = orderRepository.findAllOrdered();

        return orderEntityList.stream()
                .map(OrderMapper::mapperOrderToOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        var orderEntityList = orderRepository.findAllByStatus(status);

        return orderEntityList.stream()
                .map(OrderMapper::mapperOrderToOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        var orderEntity = orderMapper.mapperOrderEntityToOrder(order);
        var orderSaved = orderRepository.save(orderEntity);

        completedOrder(orderSaved);

        var orderSavedComplete = orderRepository.save(orderEntity);
        return mapperOrderToOrderEntity(orderSavedComplete);
    }

    private void completedOrder(OrderEntity orderSaved) {
        orderSaved.getDishEntities()
                .forEach(x -> x.setOrderId(orderSaved));
        orderSaved.getDrinkEntities()
                .forEach(x -> x.setOrderId(orderSaved));
        orderSaved.getDessertEntities()
                .forEach(x -> x.setOrderId(orderSaved));
    }
}
