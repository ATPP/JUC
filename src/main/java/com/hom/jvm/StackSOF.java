package com.hom.jvm;

public class StackSOF {

    private int stackLength = 1;

    public void doSomething() {
        stackLength++;
        doSomething();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.doSomething();
        } catch (Throwable e) {//注意捕获的是Throwable
            System.out.println("栈深度：" + stackSOF.stackLength);
            throw e;
        }
    }
}
