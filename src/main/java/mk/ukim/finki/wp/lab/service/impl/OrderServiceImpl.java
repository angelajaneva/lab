package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService  {

    @Override
    public Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address) {
        Long orderId = new Random().nextLong();
        return new Order(pizzaType, pizzaSize, clientName, address, orderId);
    }
}
