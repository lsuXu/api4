package com.wxtoplink.api4.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wxtoplink.api4.sqlite.bean.BodyInduction;
import com.wxtoplink.api4.sqlite.bean.Btn;
import com.wxtoplink.api4.sqlite.bean.Customer;
import com.wxtoplink.api4.sqlite.bean.Product;
import com.wxtoplink.api4.sqlite.db.BodyInductionDao;
import com.wxtoplink.api4.sqlite.db.BtnDao;
import com.wxtoplink.api4.sqlite.db.CustomerDao;
import com.wxtoplink.api4.sqlite.db.DaoMaster;
import com.wxtoplink.api4.sqlite.db.DaoSession;
import com.wxtoplink.api4.sqlite.db.ProductDao;


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

}
