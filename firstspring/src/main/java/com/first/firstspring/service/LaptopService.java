package com.first.firstspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.firstspring.LaptopRepository;
import com.first.firstspring.model.Laptop;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository repo;
    public void addLaptop(Laptop lap){
        // System.out.println("method Called");

        repo.save(lap);
    }

    public boolean isGoodForProg(Laptop lap) {
        return true;
    }
}
