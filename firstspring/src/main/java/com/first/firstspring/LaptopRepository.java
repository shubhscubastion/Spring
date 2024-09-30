package com.first.firstspring;

import org.springframework.stereotype.Repository;

import com.first.firstspring.model.Laptop;

@Repository
public class LaptopRepository {
    public void save(Laptop lap) {
        System.out.println("Saved in DB");
    }
}
