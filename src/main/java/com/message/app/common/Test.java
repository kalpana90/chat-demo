package com.message.app.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String args[]){
        String name1 = "priit";
        String name2 = "maksim";
        String name3 = "ann";
        String name4 = "james";

        String password1 = new BCryptPasswordEncoder().encode(name1);
        String password2 = new BCryptPasswordEncoder().encode(name2);
        String password3 = new BCryptPasswordEncoder().encode(name3);
        String password4 = new BCryptPasswordEncoder().encode(name4);


        System.out.println(password1);
        System.out.println(password2);
        System.out.println(password3);
        System.out.println(password4);

    }
}
