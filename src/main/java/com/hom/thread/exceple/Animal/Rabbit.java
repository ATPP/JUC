package com.hom.thread.exceple.Animal;

public class Rabbit extends Animal {

    @Override
    protected void running() {
        double dis = 0.5;
        len = len - dis;
        if(len<0){
            System.out.println("rabbit win");
            if (callback!=null){
                callback.win();
            }
        }

    }
}
