package com.exposit;

import com.exposit.dao.StoreDao;
import com.exposit.dao.impl.StoreDaoImpl;
import com.exposit.model.Store;
import com.exposit.service.StoreService;
import com.exposit.service.impl.StoreServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class Starter {
    protected static final StoreDao storeDao = new StoreDaoImpl();
    protected static final StoreService storeService = new StoreServiceImpl(storeDao);

    public static void main(String[] args) {
        System.out.println("Enter the name of store:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter the address of store:");
        scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        try {
            storeService.add(new Store(name, address));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
