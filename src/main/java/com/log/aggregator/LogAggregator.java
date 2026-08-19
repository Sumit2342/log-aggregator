package com.log.aggregator;

import com.log.aggregator.services.FileReader;
import com.log.aggregator.utils.Metrics;

public class LogAggregator{
    public static void main(String[] args){
        FileReader fileReader = new FileReader();
        Metrics metrics = new Metrics();
        try {
            fileReader.readFile("logs/sample.txt",metrics);
        } catch (Exception e) {
            System.out.println("Failed to read File:"+e.getMessage());
        }
        
    }
}
