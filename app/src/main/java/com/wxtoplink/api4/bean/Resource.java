package com.wxtoplink.api4.bean;

import com.wxtoplink.api4.sqlite.bean.ResourceFile;

import java.util.List;

/**
 * Created by 12852 on 2018/11/2.
 */

public class Resource {

    private String version ;

    private List<ResourceFile> fileList ;

    public Resource(String version, List<ResourceFile> fileList) {
        this.version = version;
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
}
