package com.hom.juc.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 *  但当两个线程同时调用compute时存在一个漏洞，可能导致计算得到相同值，if代码块仍然是非原子的
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memoizer3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computable.compute(arg);
                }
            };
            FutureTask<V> futureTask = new FutureTask<>(eval);
            f = futureTask;
            cache.put(arg, futureTask);
//            这里将调用computable.compute
            futureTask.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw launderThtowable(e.getCause());
        }
    }

    public static RuntimeException launderThtowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("not unchecked", t);
        }
    }
}
