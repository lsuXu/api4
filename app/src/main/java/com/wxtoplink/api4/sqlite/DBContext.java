package com.wxtoplink.api4.sqlite;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.io.File;
import java.io.IOException;

/**
 * Created by 12852 on 2018/11/22.
 */

public class DBContext extends ContextWrapper{


    private Context mBase ;

    private String mDBFolder;

    public DBContext(Context base,String dbFolder){
        super(base);
        this.mBase = base ;
        this.mDBFolder = dbFolder ;
    }

    @Override
    public File getDatabasePath(String name) {
        if(mDBFolder == null || mDBFolder.length() == 0){//若目标文件夹为空，则返回默认文件路径
            return mBase.getDatabasePath(name);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                return mBase.getDatabasePath(name);//权限不允许访问，则创建默认数据库
            }
        }
        //权限允许
        File dbFolder = new File(mDBFolder);
        if(dbFolder.exists()){
            if(dbFolder.isFile()) {
                throw new IllegalArgumentException(String.format("Expected to be a folder, but a file:%s", dbFolder));
            }else{
                return getFile(dbFolder,name);
            }
        }else{
            dbFolder.mkdirs();//文件夹不存在，创建文件夹
            return getFile(dbFolder,name);
        }
    }

    private File getFile(File folder,String name){
        File dbFile = new File(folder,name);
        boolean fileCreateSuccess = false;
        if(!dbFile.exists()){
            try {
                //文件不存在，则创建文件
                fileCreateSuccess = dbFile.createNewFile();//文件创建成功为true,否则为false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            fileCreateSuccess = true ;
        }
        if(fileCreateSuccess){
            return dbFile ;
        }else{
            return mBase.getDatabasePath(name);
        }
    }


    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     * SQLiteDatabase.CursorFactory,
     * DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

}
