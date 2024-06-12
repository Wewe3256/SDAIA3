package org.example.server;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.databaseacess.jobDOA;

import java.io.Serializable;

@ApplicationScoped
public class SessionService implements Serializable {

        private int count;
    @Inject
    jobDOA dao;
        public int getCount() {
            return ++count;
        }

    @PostConstruct
    public void init() {
        System.out.println("session object creat");
    }
    @PreDestroy
    public void kill() {
        System.out.println("session object killed");
    }
    }

