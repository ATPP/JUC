package com.hom.thread.exceple.Animal;

public class Tortoise extends Animal {

    public Tortoise() {
        setName("乌龟");// Thread的方法，给线程赋值名字
    }

    @Override
    public void running() {
        double dis = 0.1;
        len = len - dis;
        if (len <= 0) {
            System.out.println("Tortoise win");
            if (callback != null) {
                callback.win();
            }
        }
        System.out.println("tortoise跑了" + dis + "m,还剩" + (int)len + "m");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
