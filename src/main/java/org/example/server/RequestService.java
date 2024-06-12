package org.example.server;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped

public class RequestService {
    private int count;

    public int getCount() {
        return ++count;
    }

    @PostConstruct
    public void init() {
        System.out.println("Request object creat");
    }
    @PreDestroy
    public void kill() {
        System.out.println("Request object killed");
    }

}

