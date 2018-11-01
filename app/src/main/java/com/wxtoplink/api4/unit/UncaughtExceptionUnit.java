package com.wxtoplink.api4.unit;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.bean.ErrorBean;
import com.wxtoplink.api4.util.DeviceUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by 12852 on 2018/10/31.
 */

public class UncaughtExceptionUnit {

    public static final String TAG = UncaughtExceptionUnit.class.getSimpleName();

    //获取上报错误信息的bean对象
    public ErrorBean getErrorBean(Throwable throwable, Context context){
        API4Request api4Request = API4Manager.getInstance().getAPI4Request();

        String systemVersion = Build.VERSION.RELEASE;
        String boardName = Build.MODEL;
        String versionCode = api4Request.getVersionCode(context);
        String mac = api4Request.getMac(context);
        String densityDpi = String.valueOf(DeviceUtil.getScreenDensityDpi(context));
        String screenWidth = String.valueOf(DeviceUtil.getScreenWidth(context));
        String screenHeight = String.valueOf(DeviceUtil.getScreenHeight(context));
        String errorTitle = throwable.getMessage();
        String errorDetail = getDetailMessage(throwable);
        return new ErrorBean(systemVersion,boardName,versionCode,mac,densityDpi,screenWidth,screenHeight,errorTitle,errorDetail);

    }

    //获取详细错误信息
    public String getDetailMessage(Throwable throwable){
        String filePath = Environment.getExternalStorageDirectory()+ File.separator + new Date().getTime();
        String errorMsg = null;
        if(saveErrorMessage(throwable,filePath)){
            errorMsg = loadString(filePath);
        };
        if(new File(filePath).exists()){
            new File(filePath).delete();
        }

        return errorMsg;
    }

    //保存错误日志信息
    public boolean saveErrorMessage(Throwable throwable,String filePath){
        PrintWriter pw = null;
        FileWriter fw = null;
        try {
            File logFile = new File(filePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile,true);
            pw = new PrintWriter(fw);
            throwable.printStackTrace(pw);

        }catch (Exception e){
            e.printStackTrace();
            return false ;
        }finally{
            if(pw!= null){
                pw.close();
            }
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true ;
    }

    //从文件中加载内容
    private String loadString(String filePath){
        FileInputStream in = null;
        BufferedReader reader = null ;
        StringBuffer buffer = new StringBuffer();

        File file = new File(filePath);
        if(file.exists()) {
            try {
                in = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(in));
                String line =  "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
            }catch (Exception e){
                e.printStackTrace();
                return null ;
            }finally {
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(in !=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return buffer.toString();
    }

    private UncaughtExceptionUnit() {
    }

    private static final class UnitInstance{
        private static final UncaughtExceptionUnit instance = new UncaughtExceptionUnit();
    }

    public static UncaughtExceptionUnit getInstance(){
        return UnitInstance.instance;
    }
}
