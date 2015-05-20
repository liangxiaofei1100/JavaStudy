package com.alex.javastudy;

import com.alex.javastudy.log4j.Log4jApp;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("Main app launched.");
        
        Log4jApp log4japp = new Log4jApp();
        log4japp.launch();
    }
}
