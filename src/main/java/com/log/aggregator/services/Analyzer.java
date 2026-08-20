package com.log.aggregator.services;

import com.log.aggregator.utils.LogEntry;
import com.log.aggregator.utils.Metrics;
import com.log.aggregator.utils.Constants;
public class Analyzer {
    private final String ERROR = Constants.LOG_LEVEL_ERROR;
    private final String INFO = Constants.LOG_LEVEL_INFO;
    private final String WARN = Constants.LOG_LEVEL_WARN;
    private final String DEBUG = Constants.LOG_LEVEL_DEBUG;

    public void processLogLine(LogEntry logEntry,Metrics metrics){
        String logLevel = logEntry.getLogLevel();
        switch(logLevel){
            case INFO:
                metrics.incrementInfoCount();
                break;
            case WARN:
                metrics.incrementWarnCount();
                break;
            case ERROR:
                metrics.incrementErrorCount();
                break;
        }

    }

}
