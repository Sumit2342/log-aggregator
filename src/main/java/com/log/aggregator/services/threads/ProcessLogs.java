package com.log.aggregator.services.threads;

import java.nio.ByteBuffer;

import com.log.aggregator.services.Analyzer;
import com.log.aggregator.utils.Metrics;

public class ProcessLogs implements Runnable{
    private final ByteBuffer byteBuffer;
    private final Metrics metrics;
    public ProcessLogs(ByteBuffer byteBuffer,Metrics metrics){
        this.byteBuffer = byteBuffer;
        this.metrics = metrics;
    }

    @Override
    public void run(){
        StringBuilder line = new StringBuilder();
        Analyzer analyzer = new Analyzer();
        while(byteBuffer.hasRemaining()){
            char c = (char)byteBuffer.get();
            if(c == '\n'){
                analyzer.processLogLine(line,metrics);
                line.setLength(0);
            }
            else {
                line.append(c);
            }
        }
    }
    
}
