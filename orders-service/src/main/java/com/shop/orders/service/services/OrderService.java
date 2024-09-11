package com.shop.orders.service.services;

import com.shop.orders.service.entities.Order;
import com.shop.orders.service.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getOrderById(int id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByClientId(int clientId){
        return orderRepository.findByClientId(clientId);
    }

}
