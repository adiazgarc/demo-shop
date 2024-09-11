package com.shop.orders.service.controllers;

import com.shop.orders.service.entities.Order;
import com.shop.orders.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders(){
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable  int id){
        Order order = orderService.getOrderById(id);
        if (order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Order>> getOrdersByClientId(@PathVariable int id){
        List<Order> orders = orderService.getOrdersByClientId(id);
        if (orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }
}
