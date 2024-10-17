package com.shubh.graphqlnew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubh.graphqlnew.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    
}
