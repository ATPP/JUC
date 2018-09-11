package com.hom.thread.exceple.Bank;

/**
 * 对同一个对象进行操作
 * 两个人AB通过一个账户A在柜台取钱和B在ATM机取钱
 */
public class Bank {

    // 假设一个账户有1000块钱
    static int money = 10000;
    // 柜台Counter取钱的方法
    public void Counter(int money) {// 参数是每次取走的钱
        synchronized (Bank.class) {
            Bank.money -= money;//取钱后总数减少
            System.out.println("A取走了" + money + "还剩下" + (Bank.money));
        }
    }
    // ATM取钱的方法
    public void ATM(int money) {// 参数是每次取走的钱
        synchronized (Bank.class) {
            Bank.money -= money;//取钱后总数减少
            System.out.println("B取走了" + money + "还剩下" + (Bank.money));
        }
    }
}
