package com.example.yuxuehai.wallpager.ui.interfaces;

/**
 * Created by yuxuehai on 17-12-11.
 */

public class LoadPhotoEvent {

    private long progress;
    private String photoId;
    private String message;
    private boolean isRunning;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

}
