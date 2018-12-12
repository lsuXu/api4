package com.wxtoplink.api4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by 12852 on 2018/12/11.
 */

public class FileUtil {

    /**
     * 文件重命名
     *
     * @param filePath 完整路径
     * @param newFilePath   完整路径
     * @param overwrite 是否覆盖重名文件
     * @return
     */
    public static boolean renameFileName(String filePath, String newFilePath ,boolean overwrite) {
        File oldFile = new File(filePath);
        File newFile = new File(newFilePath);
        if (!oldFile.exists()) {
            return false;//重命名文件不存在
        }
        if (newFile.exists()){
            if(overwrite)
                newFile.delete();
            else{
                return false ;
            }
        }
        oldFile.renameTo(newFile);
        return true;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件完整路径
     */
    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                deleteFiles(file1.getAbsolutePath());
            }
        } else {
            file.delete();
        }
    }

    /**
     * 复制指定文件到指定目录
     * @param fromFile  需要复制的文件全路径
     * @param toFile    目标文件全路径
     * @return  true:操作成功
     */
    public static boolean copyFile(File fromFile,File toFile){
        if(!fromFile.exists() ||  toFile == null)
            return false;
        try {
            FileInputStream ins = new FileInputStream(fromFile);
            FileOutputStream out = new FileOutputStream(toFile);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }

            ins.close();
            out.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
