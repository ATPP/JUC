package com.hom.thread.exceple.Animal;

public class Rabbit extends Animal {

    @Override
    public void running() {
        double dis = 0.5;
        len = len - dis;
        if (len <= 0) {
            System.out.println("rabbit win");
            if (callback != null) {
                callback.win();
            }
        }
        System.out.println("rabbit跑了" + dis + "m,还剩" + (int)len + "m");
        if (len % 2 == 0) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
