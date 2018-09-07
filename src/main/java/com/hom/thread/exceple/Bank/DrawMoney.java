package com.hom.thread.exceple.Bank;

public class DrawMoney {

    public static void main(String[] args) {
        // 实力化一个银行对象
        Bank bank = new Bank();
        // 实例化两个人，传入同一个银行的对象
        PersonA pA = new PersonA(bank);
        PersonB pB = new PersonB(bank);
        // 两个人开始取钱
        pA.start();
        pB.start();
    }
}
