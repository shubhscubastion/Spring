package com.shubh.graphqlnew.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.shubh.graphqlnew.entities.Order;
import com.shubh.graphqlnew.entities.User;
import com.shubh.graphqlnew.services.OrderService;
import com.shubh.graphqlnew.services.UserService;

@Controller
public class OrderController {
    private UserService userService;
    private OrderService orderService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // create order
    @MutationMapping
    public Order createOrder(
            @Argument String orderDetail,
            @Argument String address,
            @Argument int price,
            @Argument int userId) {

        User user = userService.getUser(userId);

        Order order = new Order();
        order.setOrderDetail(orderDetail);
        order.setAddress(address);
        order.setPrice(price);
        order.setUser(user);

        Order order1 = orderService.createOrder(order);
        return order1;
    }

    @QueryMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @QueryMapping
    public Order getOrder(@Argument int orderId){
        return orderService.getOrder(orderId);
    }

    @MutationMapping
    public boolean deleteOrder(int orderId){
        return orderService.deleteOrder(orderId);
    }

}
