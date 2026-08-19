package com.log.aggregator.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Metrics {
    private LongAdder errorCount = new LongAdder();
    private LongAdder warnCount = new LongAdder();
    private LongAdder infoCount = new LongAdder();
    private ConcurrentHashMap<String,Integer> metrics = new ConcurrentHashMap<>();
    // getters
    public int getErrorCount (){
       return errorCount.intValue();
    }
    public int getWarnCount (){
        return warnCount.intValue();
    }
    public int getInfoCount (){
        return infoCount.intValue();
    }
    // setters
    public void incrementErrorCount(){
        errorCount.increment();
    }

    public void incrementWarnCount(){
        warnCount.increment();
    }

    public void incrementInfoCount(){
        infoCount.increment();
    }

}
