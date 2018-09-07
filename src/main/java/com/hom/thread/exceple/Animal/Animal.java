package com.hom.thread.exceple.Animal;

public abstract class Animal extends Thread {

    protected double len = 200;

    protected abstract void running();

    @Override
    public void run() {
        super.run();
        while (len > 0) {
            running();
        }
    }

    public static interface Callback {
        public void win();
    }

    public Callback callback;
}
