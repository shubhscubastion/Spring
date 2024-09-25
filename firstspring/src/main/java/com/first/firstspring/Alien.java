package com.first.firstspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Alien {

    @Autowired
    Laptop laptop1;


    public void code(){
        System.out.println("I am coding rn!");
        laptop1.compile();
    }
}
