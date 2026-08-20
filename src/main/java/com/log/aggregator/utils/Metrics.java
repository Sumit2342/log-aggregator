package com.log.aggregator.utils;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Metrics {
    private LongAdder errorCount = new LongAdder();
    private LongAdder warnCount = new LongAdder();
    private LongAdder infoCount = new LongAdder();
    private ConcurrentHashMap<String,LongAdder> serviceErrors = new ConcurrentHashMap<>();

    public int getErrorCount (){
       return errorCount.intValue();
    }
    public int getWarnCount (){
        return warnCount.intValue();
    }
    public int getInfoCount (){
        return infoCount.intValue();
    }

    public void getServiceErrors(){
        serviceErrors.forEach((service,count)-> 
            System.out.println(service + " : " + count.sum())
        );
    } 

    public void incrementErrorCount(){
        errorCount.increment();
    }

    public void incrementWarnCount(){
        warnCount.increment();
    }

    public void incrementInfoCount(){
        infoCount.increment();
    }

    public void addServiceError(String service){
        this.serviceErrors.computeIfAbsent(service, k->new LongAdder()).increment();
    }

}
