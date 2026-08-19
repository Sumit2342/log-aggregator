package com.log.aggregator.services;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.log.aggregator.services.threads.ProcessLogs;
import com.log.aggregator.utils.Constants;
import com.log.aggregator.utils.Metrics;

public class FileReader {

    public void readFile(String fileName, Metrics metrics) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.BUFFER_SIZE);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        while(fileChannel.read(byteBuffer) >0){
            byteBuffer.flip();
            ByteBuffer chunkBuffer = extractCompleteChunk(byteBuffer); // create copy of the complete logs not incomplete 
            if(chunkBuffer != null){
              executorService.execute(new ProcessLogs(chunkBuffer,metrics));
            }

            byteBuffer.compact(); // move the unread bytes if available to the beginning of buffer
        }
        file.close();
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private ByteBuffer extractCompleteChunk(ByteBuffer byteBuffer){
        int lastNewLine = -1;
        for(int i = 0; i<byteBuffer.limit(); i++){
            if(byteBuffer.get(i) == '\n'){
                lastNewLine = i;
            }
        }

        if(lastNewLine == -1) return null;

        int completeBytes = lastNewLine + 1;
        ByteBuffer chunk = ByteBuffer.allocate(completeBytes);
        
        for(int i = 0;i<completeBytes;i++){
            chunk.put(byteBuffer.get());
        }

        chunk.flip();
        return chunk;
    }
}
