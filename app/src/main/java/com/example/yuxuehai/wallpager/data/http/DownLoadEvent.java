package com.example.yuxuehai.wallpager.data.http;

/**
 * Created by yuxuehai on 17-12-17.
 */

public class DownLoadEvent {

    private long totalLength;
    private long bytesRead;

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }


    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

}
