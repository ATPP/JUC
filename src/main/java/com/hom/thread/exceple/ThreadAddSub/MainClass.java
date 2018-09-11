package com.hom.thread.exceple.ThreadAddSub;

public class MainClass {
    /**
     * (线程同步)
     * */
    public static void main(String[] args) {
        //创建一个存放ThreadAddSub对象的数组
        ThreadAddSub[] tSub=new ThreadAddSub[4];
        for (int i = 0; i < tSub.length; i++) {
            //把实例化ThreadAddSub对象赋值到数组内
            //第一三个是true，二四个是false
            tSub[i]=new ThreadAddSub(i%2==0?true:false);
            //让线程开始工作
            tSub[i].start();
        }
    }
}
