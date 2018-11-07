package com.wxtoplink.api4.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wxtoplink.api4.sqlite.bean.BodyInduction;
import com.wxtoplink.api4.sqlite.bean.Btn;
import com.wxtoplink.api4.sqlite.bean.Customer;
import com.wxtoplink.api4.sqlite.bean.Product;
import com.wxtoplink.api4.sqlite.bean.ResourceFile;
import com.wxtoplink.api4.sqlite.db.BodyInductionDao;
import com.wxtoplink.api4.sqlite.db.BtnDao;
import com.wxtoplink.api4.sqlite.db.CustomerDao;
import com.wxtoplink.api4.sqlite.db.DaoMaster;
import com.wxtoplink.api4.sqlite.db.DaoSession;
import com.wxtoplink.api4.sqlite.db.ProductDao;
import com.wxtoplink.api4.sqlite.db.ResourceFileDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;


/**
 * Sqlite数据库操作类
 * Created by 12852 on 2018/10/30.
 */

public class SqliteOperateUtil {

    public static final String TAG = "SqliteOperateUtil";

    private DaoMaster.DevOpenHelper daoHelp ;

    private SQLiteDatabase db ;

    private DaoMaster daoMaster ;

    private DaoSession daoSession ;

    private BodyInductionDao bodyInductionDao ;

    private BtnDao btnDao ;

    private CustomerDao customerDao ;

    private ProductDao productDao ;

    private ResourceFileDao resourceFileDao ;

    private SqliteOperateUtil(){}

    public void init(Context context) {
        daoHelp = new DaoMaster.DevOpenHelper(context,"testDb",null);
        db = daoHelp.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        bodyInductionDao = daoSession.getBodyInductionDao();
        btnDao = daoSession.getBtnDao();
        customerDao = daoSession.getCustomerDao();
        productDao = daoSession.getProductDao();
        resourceFileDao = daoSession.getResourceFileDao();
    }

    private static class SqliteOperateUtilHolder{
        private static final SqliteOperateUtil sqliteOperateUtil = new SqliteOperateUtil();
    }

    public static SqliteOperateUtil getInstance(){
        return SqliteOperateUtilHolder.sqliteOperateUtil;
    }

    public BodyInductionDao getBodyInductionDao() {
        return bodyInductionDao;
    }

    public BtnDao getBtnDao() {
        return btnDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ResourceFileDao getResourceFileDao(){
        return resourceFileDao ;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public long insertCustomer(Customer customer){
        return customerDao.insert(customer);
    }

    public long insertBtn(Btn btn){
        return btnDao.insert(btn);
    }

    public long insertBodyInduction(BodyInduction bodyInduction){
        return bodyInductionDao.insert(bodyInduction);
    }

    public long insertProduct(Product product){
        return productDao.insert(product);
    }

    public void updateCustomer(Customer customer){
        customerDao.update(customer);
    }

    public void updateBtn(Btn btn){
        btnDao.update(btn);
    }

    public void updateBodyInduction(BodyInduction bodyInduction){
        bodyInductionDao.update(bodyInduction);
    }

    public void updateProduct(Product product){
        productDao.update(product);
    }

    //重新装载资源文件列表
    public void reloadResourceFile(Iterable<ResourceFile> resourceFiles){
        resourceFileDao.deleteAll();
        resourceFileDao.insertInTx(resourceFiles);
    }

    //根据文件名称查询ResourceFile
    public ResourceFile queryResourceFileByFileName(String fileName){
        return resourceFileDao.queryBuilder()
                .where(ResourceFileDao.Properties.FileName.eq(fileName))
                .build()
                .unique();
    }

    //新增资源文件
    public void insertResourceFile(ResourceFile resourceFile){
        resourceFileDao.insert(resourceFile);
    }

    //更新资源文件列表
    public void updateResourceFile(ResourceFile resourceFile){
        resourceFileDao.update(resourceFile);
    }

}
