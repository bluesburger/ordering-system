package br.com.bluesburger.orderingsystem.core.ports.out;

import br.com.bluesburger.orderingsystem.adapters.out.repository.OrderRepository;
import br.com.bluesburger.orderingsystem.adapters.out.repository.UserRepository;
import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@Transactional
public class OrderPortImpl implements OrderPort {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
