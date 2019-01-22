package com.hom.juc.threadPool.executorDemo;

import java.io.IOException;
import java.io.PipedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    private static final int NTHREADS = 10;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(89);
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(connection);
                    }
                };
                exec.execute(task);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket){

    }
}
