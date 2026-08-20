package com.log.aggregator.services;

import com.log.aggregator.utils.LogEntry;

public class Parser {


    public static LogEntry parse(String line){
        String[] parts = line.trim().split("\\s+",5);
        if(parts.length < 4) {
            System.out.println("Cannot parse incomplete log line");
            return null;
        }
        String timestamp = parts[0] +  " " + parts[1];
        String logLevel = parts[2];
        String service = parts[3];
        String message = parts[4];

        LogEntry logEntry = new LogEntry(service,timestamp,logLevel,message.toString());

        return logEntry;
    }
}
