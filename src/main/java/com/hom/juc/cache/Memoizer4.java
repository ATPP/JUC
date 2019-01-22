package com.hom.juc.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 *  缓存是Future而不是值时，会导致缓存污染，没有解决缓存逾期，缓存清理的问题
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer4<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memoizer4(Computable<A, V> computable) {
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
            //可以避免3的漏洞
            cache.putIfAbsent(arg, futureTask);
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
