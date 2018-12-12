package com.wxtoplink.api4.util;

import android.support.annotation.NonNull;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.constant.ResourceType;
import com.wxtoplink.api4.constant.Status;
import com.wxtoplink.api4.sqlite.bean.DeviceResourceFile;
import com.wxtoplink.api4.sqlite.bean.ResourceFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12852 on 2018/11/2.
 */

public class ResourceFileUtil {

    public static final String SUFFIX = "_temp";

    public static final String projectPath = API4Manager.getInstance().getAPI4Request() != null ?API4Manager.getInstance().getAPI4Request().getProjectPath():"";


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
        return checkResourceFile(resourceFile.getHash(),resourceFile.getSize(),targetLocalFile);
    }

    public static boolean checkResourceFile(@NonNull DeviceResourceFile deviceResourceFile ,@NonNull File targetLocalFile){
        return checkResourceFile(deviceResourceFile.getHash(),deviceResourceFile.getSize(),targetLocalFile);
    }


    public static boolean checkResourceFile(@NonNull String md5,@NonNull String fileSize,File targetLocalFile) {
        if (targetLocalFile.exists() && targetLocalFile.isFile()) {
            if (Long.parseLong(fileSize) == targetLocalFile.length()) {
                String localFileMd5 = EncryptionCheckUtil.md5sum(targetLocalFile.getAbsolutePath());
                return md5.equals(localFileMd5);//hash值相同，是同一个文件，hash值不相同，不是同一文件。
            } else {
                return false;//文件大小不一样，不是同一个文件
            }
        } else {
            return false;//文件不存在，或者为文件夹，标志不为同一个文件
        }
    }

    /**
     * 依据资源类型，以及资源文件，给出对应于APP的存储路径
     * @param resourceFile  资源文件
     * @return
     */
    public static String getResourceFilePath(ResourceFile resourceFile){
        return getResourceFilePath(resourceFile.getPath(),resourceFile.getFileName());
    }

    /**
     * 获取本地资源文件记录的绝对文件路径
     * @param deviceResourceFile
     * @return
     */
    public static String getResourceFilePath(DeviceResourceFile deviceResourceFile){
        return getResourceFilePath(deviceResourceFile.getPath(),deviceResourceFile.getFileName());
    }

    /**
     * 获取资源文件的路径
     * @param relativePath  相对路径，相对于项目目录
     * @param fileName  文件名称
     * @return  文件实际存储地址
     */
    public static String getResourceFilePath(String relativePath,String fileName){

        if(relativePath != null){
            String relativeFolder = relativePath.substring(0,relativePath.lastIndexOf(fileName));
            File folder = new File(String.format("%s/%s",projectPath,relativeFolder));
            if(!folder.exists()){
                folder.mkdirs();
            }
            return new File(folder,fileName).getAbsolutePath();
        }else{
            return String.format("%s/%s",projectPath,fileName);
        }
    }

    //获取文件路径的临时文件路径
    public static String getTempFilePath(String filePath){
        return String.format("%s%s",filePath,SUFFIX);
    }

    //获取相对路径拼凑成的临时文件的绝对路径
    public static String getTempFilePath(ResourceFile resourceFile){
        return String.format("%s%s",getResourceFilePath(resourceFile),SUFFIX);
    }

    //获取相对路径拼凑成的临时文件的绝对路径
    public static String getTempFilePath(DeviceResourceFile deviceResourceFile){
        return String.format("%s%s",
                getResourceFilePath(
                        deviceResourceFile.getPath(),
                        deviceResourceFile.getFileName()),SUFFIX);
    }

    //临时文件替换重命名为目标文件
    public static boolean removeSuffer(String tempFilePath,boolean overwrite){
        //文件为临时文件，且存在
        if(tempFilePath.endsWith(SUFFIX) && new File(tempFilePath).exists()){
            //实际需要存储或替换的文件路径，去掉下载前添加的后缀
            String targetFilePath = tempFilePath.substring(0,tempFilePath.length() - SUFFIX.length());
            //文件名替换
            return FileUtil.renameFileName(tempFilePath,targetFilePath,overwrite);
        }else{
            return false ;
        }
    }
}
