package com.wxtoplink.api4.util;

import android.support.annotation.NonNull;

import com.wxtoplink.api4.constant.ResourceType;
import com.wxtoplink.api4.constant.Status;
import com.wxtoplink.api4.sqlite.bean.ResourceFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12852 on 2018/11/2.
 */

public class ResourceFileUtil {

    /**
     * 获取资源文件信息列表
     *
     * @param filePath         资源文件目录
     * @param ignoreFolderName 需要过滤的文件夹名称
     * @return 本地资源文件信息列表
     */
    public static List<ResourceFile> getLocalResourceList(@NonNull String filePath, ResourceType resourceType, String... ignoreFolderName) {
        return getLocalResourceList(new File(filePath),resourceType, ignoreFolderName);
    }

    /**
     * 获取资源文件信息列表
     *
     * @param folder         资源文件目录
     * @param ignoreFolderName 需要过滤的文件夹名称
     * @return 本地资源文件信息列表
     */
    public static List<ResourceFile> getLocalResourceList(@NonNull File folder,ResourceType resourceType, String... ignoreFolderName) {
        if (folder.exists() && folder.isDirectory()) {
            for (String ignoreName : ignoreFolderName) {
                if (folder.getName().equals(ignoreName)) {
                    return new ArrayList<>();
                }
            }
            List<ResourceFile> fileList = new ArrayList();
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String md5 = EncryptionCheckUtil.md5sum(file.getAbsolutePath());
                    String fileName = file.getName();
                    String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
                    ResourceFile resourceFile = new ResourceFile(null,fileName, fileType, md5, String.valueOf(file.length()), null,null,resourceType.getTypeId(), Status.ENABLE.getStatusId());
                    fileList.add(resourceFile);
                } else {
                    fileList.addAll(getLocalResourceList(file,resourceType,ignoreFolderName));
                }
            }

            return fileList;
        } else
            return new ArrayList<>();
    }

    /**
     * 校验指定的文件路径对应文件是否和目标文件为同一文件
     *
     * @param resourceFile  目标文件
     * @param targetLocalFilePath 指定的本地文件路径
     * @return 同一文件返回true, 不同文件返回false;
     */
    public static boolean checkResourceFile(@NonNull ResourceFile resourceFile, @NonNull String targetLocalFilePath) {
        return checkResourceFile(resourceFile,new File(targetLocalFilePath));
    }

    /**
     * 校验指定的文件路径对应文件是否和目标文件为同一文件
     *
     * @param resourceFile  目标文件
     * @param targetLocalFile 指定的本地文件
     * @return 同一文件返回true, 不同文件返回false;
     */
    public static boolean checkResourceFile(@NonNull ResourceFile resourceFile, @NonNull File targetLocalFile) {
        if (targetLocalFile.exists() && targetLocalFile.isFile()) {
            if (Long.parseLong(resourceFile.getSize()) == targetLocalFile.length()) {
                String localFileMd5 = EncryptionCheckUtil.md5sum(targetLocalFile.getAbsolutePath());
                return resourceFile.getHash().equals(localFileMd5);//hash值相同，是同一个文件，hash值不相同，不是同一文件。
            } else {
                return false;//文件大小不一样，不是同一个文件
            }
        } else {
            return false;//文件不存在，或者为文件夹，标志不为同一个文件
        }
    }
}
