package com.log.aggregator.services;

import com.log.aggregator.utils.Metrics;

public class Analyzer {

    public void processLogLine(StringBuilder line,Metrics metrics){
       String[] parts = line.toString().split(" ");
       if(parts.length < 4){
        System.out.println("Incomplete log line cannot be analyzed");
        return;
       }

       String logLevel = parts[2];
       
        switch (logLevel) {
            case "INFO":
                metrics.incrementInfoCount();
                break;
            case "WARN":
                metrics.incrementWarnCount();
                break;
            case "ERROR":
                metrics.incrementErrorCount();
                break;
            default:
                break;
        }


    }

}
