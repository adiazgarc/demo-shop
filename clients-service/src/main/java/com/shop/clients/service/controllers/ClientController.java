package com.shop.clients.service.controllers;

import com.shop.clients.service.entities.Client;
import com.shop.clients.service.models.Order;
import com.shop.clients.service.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listClients(){
        List<Client> clients = clientService.getAll();
        if (clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable  int id){
        Client client = clientService.getClientById(id);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        return ResponseEntity.ok(clientService.saveClient(client));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Order>> getOrdersByClientId(@PathVariable int id){
        List<Order> orders = clientService.getOrdersByClientId(id);
        if (orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<Order> saveOrder(@PathVariable int id, @RequestBody Order order){
        Order newOrder = clientService.saveOrder(id, order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/clientAndOrders/{id}")
    public ResponseEntity<Object> getClientAndOrders(@PathVariable int id){
        return ResponseEntity.ok(clientService.getClientAndOrders(id));
    }
}
