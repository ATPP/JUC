package com.hom.juc.threadPool.executorSerice;

import java.util.*;
import java.util.concurrent.*;

/**
 * 在预定时间内请求旅游报价
 */
public class QuoteTask  implements Callable<QuoteTask.TravelQuote> {

    private final TravelCompany company;
    private final TravelInfo info;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public QuoteTask(TravelCompany travelCompany, TravelInfo travelInfo) {
        this.company = travelCompany;
        this.info = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(info);
    }

    public List<TravelQuote> getRankedTraveQuotes(TravelInfo travelInfo, Set<TravelCompany> companies, Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException, ExecutionException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany travelCompany : companies){
            tasks.add(new QuoteTask(travelCompany, travelInfo));
        }
        List<Future<TravelQuote>> futures = executorService.invokeAll(tasks, time, unit);
        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> f : futures){
            QuoteTask task = taskIterator.next();
            quotes.add(f.get());
        }
        Collections.sort(quotes, ranking);
        return quotes;
    }


    public class TravelQuote{

    }

    public class TravelCompany{
        public TravelQuote solicitQuote(TravelInfo info){
            return null;
        }
    }

    public class TravelInfo{

    }
}


