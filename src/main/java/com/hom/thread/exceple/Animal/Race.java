package com.hom.thread.exceple.Animal;

public class Race {
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();
        LetOneStop letOneStop2 = new LetOneStop(rabbit);
        tortoise.callback = letOneStop2;
        LetOneStop letOneStop1 = new LetOneStop(tortoise);
        rabbit.callback = letOneStop1;
        rabbit.start();
        tortoise.start();
    }
}
