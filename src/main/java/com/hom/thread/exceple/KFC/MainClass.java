package com.hom.thread.exceple.KFC;

public class MainClass {

    public static void main(String[] args) {
        KFC kfc = new KFC();
        Waiter waiter = new Waiter(kfc);
        Waiter waiter1 = new Waiter(kfc);
        Waiter waiter2 = new Waiter(kfc);
        Customer customer = new Customer(kfc);
        Customer customer1 = new Customer(kfc);
        Customer customer2 = new Customer(kfc);
        Customer customer3 = new Customer(kfc);
        Customer customer4 = new Customer(kfc);
        customer.start();
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        waiter.start();
        waiter1.start();
        waiter2.start();

    }
}
