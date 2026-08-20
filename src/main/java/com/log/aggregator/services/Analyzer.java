package com.log.aggregator.services;

import com.log.aggregator.utils.LogEntry;
import com.log.aggregator.utils.Metrics;
import com.log.aggregator.utils.Constants;
public class Analyzer {
    private static final String ERROR = Constants.LOG_LEVEL_ERROR;
    private static final String INFO = Constants.LOG_LEVEL_INFO;
    private static final String WARN = Constants.LOG_LEVEL_WARN;
    private static final String DEBUG = Constants.LOG_LEVEL_DEBUG;

    public void processLogLine(LogEntry logEntry,Metrics metrics){
        String logLevel = logEntry.getLogLevel();
        String service = logEntry.getService().replace("[", "").replace("]", "");
        String message = logEntry.getMessage();
        String timestamp = logEntry.getTimestamp();

        switch(logLevel){
            case INFO:
                metrics.incrementInfoCount();
                break;
            case WARN:
                metrics.incrementWarnCount();
                break;
            case ERROR:
                metrics.incrementErrorCount();
                metrics.addServiceError(service);
                break;
        }


    }

}
