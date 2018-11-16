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

    private boolean initSuccess ;

    private SqliteOperateUtil(){}

    public void init(Context context) {
        if(context == null){
            return;
        }
        daoHelp = new DaoMaster.DevOpenHelper(context,"testDb",null);
        db = daoHelp.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        bodyInductionDao = daoSession.getBodyInductionDao();
        btnDao = daoSession.getBtnDao();
        customerDao = daoSession.getCustomerDao();
        productDao = daoSession.getProductDao();
        resourceFileDao = daoSession.getResourceFileDao();
        initSuccess = true ;
    }

    private static class SqliteOperateUtilHolder{
        private static final SqliteOperateUtil sqliteOperateUtil = new SqliteOperateUtil();
    }

    public static SqliteOperateUtil getInstance(){
        return SqliteOperateUtilHolder.sqliteOperateUtil;
    }

    public BodyInductionDao getBodyInductionDao() {
        if(!initSuccess){
            return null ;
        }
        return bodyInductionDao;
    }

    public BtnDao getBtnDao() {
        if(!initSuccess){
            return null ;
        }
        return btnDao;
    }

    public CustomerDao getCustomerDao() {
        if(!initSuccess){
            return null ;
        }
        return customerDao;
    }

    public ProductDao getProductDao() {
        if(!initSuccess){
            return null ;
        }
        return productDao;
    }

    public ResourceFileDao getResourceFileDao(){
        if(!initSuccess){
            return null ;
        }
        return resourceFileDao ;
    }

    public SQLiteDatabase getDb() {
        if(!initSuccess){
            return null ;
        }
        return db;
    }

    public long insertCustomer(Customer customer){
        if(!initSuccess){
            return -1 ;
        }
        return customerDao.insert(customer);
    }

    public long insertBtn(Btn btn){
        if(!initSuccess){
            return -1 ;
        }
        return btnDao.insert(btn);
    }

    public long insertBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return -1 ;
        }
        return bodyInductionDao.insert(bodyInduction);
    }

    public long insertProduct(Product product){
        if(!initSuccess){
            return -1 ;
        }
        return productDao.insert(product);
    }

    public void updateCustomer(Customer customer){
        if(!initSuccess){
            return ;
        }
        customerDao.update(customer);
    }

    public void updateBtn(Btn btn){
        if(!initSuccess){
            return ;
        }
        btnDao.update(btn);
    }

    public void updateBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return ;
        }
        bodyInductionDao.update(bodyInduction);
    }

    public void updateProduct(Product product){
        if(!initSuccess){
            return ;
        }
        productDao.update(product);
    }

    //重新装载资源文件列表
    public void reloadResourceFile(Iterable<ResourceFile> resourceFiles){
        if(!initSuccess){
            return ;
        }
        resourceFileDao.deleteAll();
        resourceFileDao.insertInTx(resourceFiles);
    }

    //根据文件名称查询ResourceFile
    public ResourceFile queryResourceFileByFileName(String fileName){
        if(!initSuccess){
            return new ResourceFile();
        }
        return resourceFileDao.queryBuilder()
                .where(ResourceFileDao.Properties.FileName.eq(fileName))
                .build()
                .unique();
    }

    //新增资源文件
    public void insertResourceFile(ResourceFile resourceFile){
        if(!initSuccess){
            return ;
        }
        resourceFileDao.insert(resourceFile);
    }

    //更新资源文件列表
    public void updateResourceFile(ResourceFile resourceFile){
        if(!initSuccess){
            return ;
        }
        resourceFileDao.update(resourceFile);
    }

}
