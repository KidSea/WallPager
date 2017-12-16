package com.example.yuxuehai.wallpager.ui.interfaces;

/**
 * Created by yuxuehai on 17-12-11.
 */

public class LoadPhotoEvent {

    private int progress;
    private String photoId;
    private String message;


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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
