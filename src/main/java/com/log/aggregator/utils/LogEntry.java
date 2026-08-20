package com.log.aggregator.utils;

public class LogEntry {
    private final String service;
    private final String timestamp;
    private final String logLevel;
    private final String message;

    public LogEntry(String service, String timestamp, String logLevel, String message){
        this.service = service;
        this.timestamp = timestamp;
        this.logLevel = logLevel;
        this.message = message;
    }

    public String getService(){
        return service;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public String getLogLevel(){
        return logLevel;
    }

    public String getMessage(){
        return message;
    }
    
}
