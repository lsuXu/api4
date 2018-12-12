package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 12852 on 2018/12/7.
 */
@Entity
public class DeviceResourceFile {

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
    //资源的前缀基础路径，index.html 跟在该路径后
    private String prefixPath ;
    //资源类别Id
    private long resourceTypeId ;
    //资源版本
    private String resourceVersion ;
    //资源类表所属名称
    private String resourceTypeName ;
    //标志下载状态
    private int status ;

    public DeviceResourceFile(Resource resource, ResourceFile resourceFile){
        this.id = null ;
        this.fileName = resourceFile.getFileName();
        this.fileType = resourceFile.getFileType();
        this.hash = resourceFile.getHash();
        this.size = resourceFile.getSize();
        this.fileUrl = resourceFile.getFileUrl();
        this.path = resourceFile.getPath();
        this.prefixPath = resource.getPrefixPath();
        this.resourceTypeId = resource.getTypeId();
        this.resourceTypeName = resource.getTypeName();
        this.resourceVersion = resource.getVersion();
        this.status = resourceFile.getStatus();
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResourceTypeName() {
        return this.resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public String getResourceVersion() {
        return this.resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public long getResourceTypeId() {
        return this.resourceTypeId;
    }

    public void setResourceTypeId(long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public String getPrefixPath() {
        return this.prefixPath;
    }

    public void setPrefixPath(String prefixPath) {
        this.prefixPath = prefixPath;
    }

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

    @Generated(hash = 1746017882)
    public DeviceResourceFile(Long id, String fileName, String fileType,
            String hash, String size, String fileUrl, String path,
            String prefixPath, long resourceTypeId, String resourceVersion,
            String resourceTypeName, int status) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.hash = hash;
        this.size = size;
        this.fileUrl = fileUrl;
        this.path = path;
        this.prefixPath = prefixPath;
        this.resourceTypeId = resourceTypeId;
        this.resourceVersion = resourceVersion;
        this.resourceTypeName = resourceTypeName;
        this.status = status;
    }

    @Generated(hash = 896608337)
    public DeviceResourceFile() {
    }
    
}
