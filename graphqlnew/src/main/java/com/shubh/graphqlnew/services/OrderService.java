package com.shubh.graphqlnew.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shubh.graphqlnew.entities.Order;
import com.shubh.graphqlnew.helper.ExceptionHelper;
import com.shubh.graphqlnew.repositories.OrderRepo;

@Service
public class OrderService {
    private OrderRepo orderRepo;

    // constructor to be made everytime for service
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    // create order

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    // get all orders

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getOrder(int orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
        return order;
    }

    public boolean deleteOrder(int orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
        orderRepo.delete(order);
        return true;
    }
}
