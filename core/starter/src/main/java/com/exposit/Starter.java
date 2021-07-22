package com.exposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {


    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
//        System.out.println("Enter the name of store:");
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//        System.out.println("Enter the address of store:");
//        scanner = new Scanner(System.in);
//        String address = scanner.nextLine();
//        try {
//            storeService.add(new Store(name, address));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
