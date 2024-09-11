package com.shop.clients.service.services;

import com.shop.clients.service.entities.Client;
import com.shop.clients.service.feignclients.OrderFeignClient;
import com.shop.clients.service.repositories.ClientRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.shop.clients.service.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderFeignClient orderFeignClient;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public List<Order> getOrdersByClientId(int clientId){
        return restTemplate.getForObject("http://localhost:8003/order/client/"+clientId, List.class);
    }

    public Order saveOrder(int clientId, Order order){
        order.setClientId(clientId);
        Order newOrder = orderFeignClient.save(order);
        return newOrder;
    }

    public Client getClientById(int id){
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Map<String, Object> getClientAndOrders(int id){
        Map<String, Object> result = new HashMap<>();
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null){
            result.put("Message", "User does not exist");
            return result;
        }

        result.put("User", client);
        List<Order> orders = orderFeignClient.getOrdersByClientId(id);

        if (orders.isEmpty())
        {
            result.put("Orders", "The user has no orders");
        }else{
            result.put("Orders", orders);
        }

        return  result;
    }
}
