package com.wxtoplink.api4.bean;

/**
 * 资源文件对象
 * Created by 12852 on 2018/11/2.
 */

public class ResourceFile {

    //文件名
    private String fileName ;
    //文件类型
    private String fileType ;
    //文件的哈希值
    private String hash ;
    //文件大小
    private String size ;
    //文件下载地址
    private String fileUrl ;

    public ResourceFile(String fileName, String fileType, String hash, String size, String fileUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.hash = hash;
        this.size = size;
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
