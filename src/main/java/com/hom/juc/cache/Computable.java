package com.hom.juc.cache;

/**
 * 使用hashmap和同步机制来初始化缓存
 * @param <A>
 * @param <V>
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
