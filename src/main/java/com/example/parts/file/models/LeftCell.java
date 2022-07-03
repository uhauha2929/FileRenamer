package com.example.parts.file.models;

import java.io.File;
import java.time.Instant;

public class LeftCell {

    // 该文件的状态，1代表删除
    private int status;
    // 添加至列表的时间
    private Instant addTime;
    public File file;

    public LeftCell(File file) {
        this.file = file;
    }

    public LeftCell(File file, Instant addTime) {
        this.file = file;
        this.addTime = addTime;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Instant getAddTime() {
        return addTime;
    }

    public void setAddTime(Instant addTime) {
        this.addTime = addTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
