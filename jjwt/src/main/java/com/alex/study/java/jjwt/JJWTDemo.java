package com.alex.study.java.jjwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class JJWTDemo {

    public static void main(String[] args) {
        System.out.println("Hello world.");

        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = MacProvider.generateKey();

        String s = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();
        System.out.println("s = " + s);

        assert Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject().equals("Joe");

        Date date = new Date(new Date().getTime() + 10 * 24 * 60 * 60 * 1000);

        String siningKey = "dilemu";
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        s = Jwts.builder().setSubject(uuid).setExpiration(date).signWith(SignatureAlgorithm.HS256, siningKey).compact();
        System.out.println("s = " + s);
        Jws<Claims> jwt = Jwts.parser().setSigningKey(siningKey).parseClaimsJws(s);

        Header jwtHeader = jwt.getHeader();
        System.out.println("jwtHeader = " + jwtHeader);

        final Claims jwtBody = jwt.getBody();
        System.out.println("jwtBody = " + jwtBody);

        String issuer = jwtBody.getIssuer();
        System.out.println("issuer = " + issuer);

        Date expiration = jwtBody.getExpiration();
        System.out.println("expiration = " + expiration);

        String subject = jwtBody.getSubject();
        System.out.println("subject = " + subject);
    }

}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmOGUyOGQyMC01MzVmLTQzZTQtYjA4MC1kYmEwY2M0MzAxNjgifQ.wKQ888AIX2MgPJaa-RpE6ZFry4hYeg6QpfjW2l1ZGak