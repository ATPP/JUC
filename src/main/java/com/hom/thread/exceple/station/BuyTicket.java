package com.hom.thread.exceple.station;

public class BuyTicket {
    public static void main(String[] args) {
        Station thread1 = new Station("窗口01");
        Station thread2 = new Station("窗口02");
        Station thread3 = new Station("窗口03");
        Station thread4 = new Station("窗口04");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
