package com.hom.juc.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
//        长时间计算后
        return new BigInteger(arg);
    }
}
