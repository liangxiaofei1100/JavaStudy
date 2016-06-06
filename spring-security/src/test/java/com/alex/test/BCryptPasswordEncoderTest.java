package com.alex.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            String password = "asdfsafsaf11!2313124%#$%";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            long start = System.currentTimeMillis();
            String hashedPassword = passwordEncoder.encode(password);
            long end = System.currentTimeMillis();
            System.out.println("encode cost time: " + (end - start));
            System.out.println(hashedPassword);

            start = System.currentTimeMillis();
            boolean matched = passwordEncoder.matches(password,hashedPassword);
            System.out.println("matched: " + matched);
            end = System.currentTimeMillis();
            System.out.println("match cost time: " + (end - start));

            i++;
        }


    }
}
