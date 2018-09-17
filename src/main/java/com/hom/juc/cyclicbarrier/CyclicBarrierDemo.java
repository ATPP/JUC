package com.hom.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    public static class Soldier implements Runnable {

        private String soldierName;
        private CyclicBarrier cyclicBarrier;

        Soldier(CyclicBarrier cyclicBarrier, String soldierName) {
            this.cyclicBarrier = cyclicBarrier;
            this.soldierName = soldierName;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                Thread.sleep(1000);
                System.out.println(soldierName+"开始任务，任务完成");
                cyclicBarrier.await();
//                System.out.println("任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BarrierRun implements Runnable {

        boolean flag;
        int N;

        BarrierRun(boolean flag, int N) {
            this.flag = false;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：士兵" + N + "任务完成");
            } else {
                System.out.println("司令：士兵" + N + "集合完毕");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        int N = 10;
        Thread[] soldiers = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道");
            soldiers[i] = new Thread(new Soldier(cyclicBarrier, "姓名" + i));
            soldiers[i].start();
        }
    }
}
