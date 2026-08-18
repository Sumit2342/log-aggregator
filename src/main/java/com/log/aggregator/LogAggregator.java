package com.log.aggregator;

import com.log.aggregator.services.FileReader;

public class LogAggregator{
    public static void main(String[] args){
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFile("logs/sample.txt");
        } catch (Exception e) {
            System.out.println("Failed to read File:"+e.getMessage());
        }
        
    }
}
