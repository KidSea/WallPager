package com.example.yuxuehai.wallpager.data.http;

import android.support.annotation.Nullable;

import com.example.yuxuehai.wallpager.base.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by yuxuehai on 17-12-17.
 */

public class DownloadProgressResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private BufferedSource bufferedSource;

    public DownloadProgressResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }


    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if(totalBytesRead != -1){
                    DownLoadEvent downLoadEvent = new DownLoadEvent();
                    downLoadEvent.setTotalLength(contentLength());
                    downLoadEvent.setBytesRead(totalBytesRead);
                    RxBus.getDefault().post(downLoadEvent);
                }
                return bytesRead;
            }
        };

    }
}
