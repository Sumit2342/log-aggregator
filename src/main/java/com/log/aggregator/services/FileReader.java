package com.log.aggregator.services;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.log.aggregator.services.threads.ProcessLogs;
import com.log.aggregator.utils.Constants;
import com.log.aggregator.utils.Metrics;

public class FileReader {

    public void readFile(String fileName, Metrics metrics) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.BUFFER_SIZE);

        ExecutorService executorService = Executors.newCachedThreadPool();
        while(fileChannel.read(byteBuffer) >0){
            byteBuffer.flip();
            ByteBuffer chunkBuffer = ByteBuffer.allocate(byteBuffer.remaining()); // create copy of the buffer
            executorService.execute(new ProcessLogs(chunkBuffer,metrics));
            byteBuffer.compact(); // move the unread bytes if available to the beginning of buffer
        }
        file.close();
        executorService.shutdown();
    }

}
