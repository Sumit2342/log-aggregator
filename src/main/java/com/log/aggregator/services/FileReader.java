package com.log.aggregator.services;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.log.aggregator.utils.Constants;

public class FileReader {

    public void readFile(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.BUFFER_SIZE);


        while(fileChannel.read(byteBuffer) >0){
            byteBuffer.flip();
            ByteBuffer chunkBuffer = byteBuffer.slice();
            readLine(chunkBuffer);
            byteBuffer.clear();
        }

        file.close();
    }

    private void readLine(ByteBuffer chunkBuffer){
        while(chunkBuffer.hasRemaining()){
            char c = (char) chunkBuffer.get();
            System.out.print(c);
        }
    }

}
