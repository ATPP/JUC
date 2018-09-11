package com.hom.thread.exceple.Animal;

public abstract class Animal extends Thread {

    public double len = 20;//比赛的长度

    public abstract void running();//抽象方法需要子类实现

    //在父类重写run方法，在子类只要重写running方法就可以了
    @Override
    public void run() {
        super.run();
        while (len > 0) {
            running();
        }
    }

    //在需要回调数据的地方（两个子类需要），声明一个接口
    public static interface Callback {
        public void win();
    }

    //2.创建接口对象
    public Callback callback;
}
