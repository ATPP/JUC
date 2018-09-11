package com.hom.thread.exceple.Animal;


public class LetOneStop implements Animal.Callback {
    private Animal animal;

    public LetOneStop(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void win() {
        animal.stop();
    }
}
