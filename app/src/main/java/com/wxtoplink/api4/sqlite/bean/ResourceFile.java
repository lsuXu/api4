package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 资源文件对象
 * Created by 12852 on 2018/11/2.
 */

@Entity
public class ResourceFile {

    @Id(autoincrement = true)
    private Long id ;
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
    //文件相对路径
    private String path ;
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getSize() {
        return this.size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getHash() {
        return this.hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getFileType() {
        return this.fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ResourceFile(String fileName, String fileType, String hash, String size, String fileUrl, String path) {
        this.id = null ;
        this.fileName = fileName;
        this.fileType = fileType;
        this.hash = hash;
        this.size = size;
        this.fileUrl = fileUrl;
        this.path = path;
    }

    @Generated(hash = 19438760)
    public ResourceFile(Long id, String fileName, String fileType, String hash,
            String size, String fileUrl, String path) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.hash = hash;
        this.size = size;
        this.fileUrl = fileUrl;
        this.path = path;
    }
    @Generated(hash = 2116103844)
    public ResourceFile() {
    }
    

}