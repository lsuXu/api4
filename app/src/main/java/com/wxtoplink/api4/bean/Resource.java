package com.wxtoplink.api4.bean;

import com.wxtoplink.api4.sqlite.bean.ResourceFile;

import java.util.List;

/**
 * Created by 12852 on 2018/11/2.
 */

public class Resource {

    private long typeId;

    private String typeName;

    private String version ;

    private String prefixPath;

    private List<ResourceFile> fileList ;

    public Resource(long typeId, String typeName, String version, String prefixPath, List<ResourceFile> fileList) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.version = version;
        this.prefixPath = prefixPath;
        this.fileList = fileList;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ResourceFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<ResourceFile> fileList) {
        this.fileList = fileList;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPrefixPath() {
        return prefixPath;
    }

    public void setPrefixPath(String prefixPath) {
        this.prefixPath = prefixPath;
    }
}
