package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Order;

public interface OrderService {

    Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address);
}
