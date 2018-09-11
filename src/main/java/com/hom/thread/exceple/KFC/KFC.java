package com.hom.thread.exceple.KFC;

import java.util.ArrayList;
import java.util.List;

public class KFC {

    String[] names = {"汉堡", "薯条", "可乐", "鸡翅"};
    final int Max = 20;
    List<Food> foods = new ArrayList<>();

    public void produce(int num) {
        synchronized (this) {
        while (foods.size() > 20) {
                System.out.println("食材够了");
                this.notifyAll();
                String threadName = Thread.currentThread().getName();
                try {
                    this.wait();
                    System.out.println("暂停线程" + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产食物");
            for (int i = 0; i < num; i++) {
                Food food = new Food(names[(int) Math.random() * 4]);
                foods.add(food);
            }
        }
    }

    public void consumer(int num) {
        synchronized (this) {
            while (foods.size() <= num) {
                System.out.println("没食物了");
                this.notifyAll();
                String threadName = Thread.currentThread().getName();
                try {
                    this.wait();
                    System.out.println("暂停线程" + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费食物");
            for (int i = 0; i < num; i++) {
                Food food = foods.remove(foods.size() - 1);
            }
        }

    }
}
