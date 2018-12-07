package com.wxtoplink.api4.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4DBOperateCallBack;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.constant.ResourceType;
import com.wxtoplink.api4.constant.Status;
import com.wxtoplink.api4.sqlite.bean.BodyInduction;
import com.wxtoplink.api4.sqlite.bean.Btn;
import com.wxtoplink.api4.sqlite.bean.Customer;
import com.wxtoplink.api4.sqlite.bean.Product;
import com.wxtoplink.api4.sqlite.bean.Resource;
import com.wxtoplink.api4.sqlite.bean.ResourceFile;
import com.wxtoplink.api4.sqlite.db.BodyInductionDao;
import com.wxtoplink.api4.sqlite.db.BtnDao;
import com.wxtoplink.api4.sqlite.db.CustomerDao;
import com.wxtoplink.api4.sqlite.db.DaoMaster;
import com.wxtoplink.api4.sqlite.db.DaoSession;
import com.wxtoplink.api4.sqlite.db.ProductDao;
import com.wxtoplink.api4.sqlite.db.ResourceDao;
import com.wxtoplink.api4.sqlite.db.ResourceFileDao;
import com.wxtoplink.api4.util.GSONUtils;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
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

    private ResourceDao resourceDao ;

    private boolean initSuccess ;

    private API4DBOperateCallBack api4DBOperateCallBack ;

    private SqliteOperateUtil(){}

    public void init(Context context) {
        API4Request api4Request = API4Manager.getInstance().getAPI4Request();
        if(api4Request == null ||context == null){
            return;
        }

        //获取数据库名称
        String dbName = api4Request.getDbName() ;
        if(dbName == null || dbName.length() == 0){
            dbName = "api4DB" ;//设置默认的数据库名称
        }

        daoHelp = new DaoMaster.DevOpenHelper(context, dbName,null);
        db = daoHelp.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        bodyInductionDao = daoSession.getBodyInductionDao();
        btnDao = daoSession.getBtnDao();
        customerDao = daoSession.getCustomerDao();
        productDao = daoSession.getProductDao();
        resourceFileDao = daoSession.getResourceFileDao();
        resourceDao = daoSession.getResourceDao();
        initSuccess = true ;
    }

    public API4DBOperateCallBack getApi4DBOperateCallBack() {
        return api4DBOperateCallBack;
    }

    public void setApi4DBOperateCallBack(API4DBOperateCallBack api4DBOperateCallBack) {
        this.api4DBOperateCallBack = api4DBOperateCallBack;
    }

    private static class SqliteOperateUtilHolder{
        private static final SqliteOperateUtil sqliteOperateUtil = new SqliteOperateUtil();
    }

    public static SqliteOperateUtil getInstance(){
        return SqliteOperateUtilHolder.sqliteOperateUtil;
    }

    public ResourceDao getResourceDao() {
        return initSuccess?resourceDao:null;
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
        operateDataCall(customer,OperateType.INSERT);
        return customerDao.insert(customer) ;
    }

    public long insertBtn(Btn btn){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(btn,OperateType.INSERT);
        return btnDao.insert(btn);
    }

    public long insertBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(bodyInduction,OperateType.INSERT);
        return bodyInductionDao.insert(bodyInduction);
    }

    public long insertProduct(Product product){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(product,OperateType.INSERT);
        return productDao.insert(product);
    }

    public void updateCustomer(Customer customer){
        if(!initSuccess){
            return ;
        }
        operateDataCall(customer,OperateType.UPDATE);
        customerDao.update(customer);
    }

    public void updateBtn(Btn btn){
        if(!initSuccess){
            return ;
        }
        operateDataCall(btn,OperateType.UPDATE);
        btnDao.update(btn);
    }

    public void updateBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return ;
        }
        operateDataCall(bodyInduction,OperateType.UPDATE);
        bodyInductionDao.update(bodyInduction);
    }

    public void updateProduct(Product product){
        if(!initSuccess){
            return ;
        }
        operateDataCall(product,OperateType.UPDATE);
        productDao.update(product);
    }

    //根据文件名称查询ResourceFile
    public ResourceFile queryResourceFileByFileName(String fileName){
        if(!initSuccess){
            return new ResourceFile();
        }
        ResourceFile resourceFile =  resourceFileDao.queryBuilder()
                .where(ResourceFileDao.Properties.FileName.eq(fileName))
                .build()
                .unique();
        operateDataCall(resourceFile == null?new ResourceFile():resourceFile,OperateType.QUERY);
        return resourceFile ;
    }

    //新增资源文件
    public void insertResourceFile(ResourceFile resourceFile){
        if(!initSuccess){
            return ;
        }
        operateDataCall(resourceFile,OperateType.INSERT);
        resourceFileDao.insert(resourceFile);
    }

    //更新资源文件列表
    public void updateResourceFile(ResourceFile resourceFile){
        if(!initSuccess){
            return ;
        }
        operateDataCall(resourceFile,OperateType.UPDATE);
        resourceFileDao.update(resourceFile);
    }

    //新增资源类别
    public long insertResource(Resource resource){
        if(!initSuccess){
            return -1;
        }
        operateDataCall(resource,OperateType.INSERT);
        return resourceDao.insert(resource);
    }

    //更新资源类别
    public void updateResource(Resource resource){
        if(!initSuccess){
            return ;
        }
        operateDataCall(resource,OperateType.UPDATE);
    }

    //根据条件查询唯一的资源类别
    public Resource queryResourceUnique(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return null;
        }
        return resourceDao.queryBuilder().where(cond,condMore).unique();
    }

    //根据条件查询资源类别列表
    public List<Resource> queryResourceList(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>();
        }
        return resourceDao.queryBuilder().where(cond,condMore).list();
    }

    //获取当前资源类别的可用版本
    public Resource queryEnableResource(ResourceType resourceType){
        return queryEnableResource(resourceType.getTypeId());
    }

    public Resource queryEnableResource(long resourceTypeId){
        if(!initSuccess){
            return null ;
        }
        return resourceDao.queryBuilder()
                .where(ResourceDao.Properties.TypeId.eq(resourceTypeId)
                        ,ResourceDao.Properties.Status.eq(Status.ENABLE.getStatusId())).unique();
    }

    private void operateDataCall(Object obj,OperateType type){
        if(api4DBOperateCallBack != null){
            api4DBOperateCallBack.operateData(GSONUtils.toJson(obj),type);
        }
    }



}
