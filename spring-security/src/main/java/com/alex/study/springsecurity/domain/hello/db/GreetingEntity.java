package com.alex.study.springsecurity.domain.hello.db;

import javax.persistence.*;

@Entity(name = "greeting")
public class GreetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "message")
    public String message;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GreetingEntity{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
