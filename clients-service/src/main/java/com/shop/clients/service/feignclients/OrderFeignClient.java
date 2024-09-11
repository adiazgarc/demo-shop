package com.shop.clients.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import  com.shop.clients.service.models.Order;

import java.util.List;

@FeignClient(name = "orders-service", url = "http://localhost:8003")
@RequestMapping("/order")
public interface OrderFeignClient {

    @PostMapping()
    public Order save(@RequestBody Order order);

    @GetMapping("/client/{clientId}")
    public List<Order> getOrdersByClientId(@PathVariable("clientId") int clientId);

}
