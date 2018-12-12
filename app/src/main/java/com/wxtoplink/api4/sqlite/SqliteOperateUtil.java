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
import com.wxtoplink.api4.sqlite.bean.DeviceResourceFile;
import com.wxtoplink.api4.sqlite.bean.Product;
import com.wxtoplink.api4.sqlite.bean.Resource;
import com.wxtoplink.api4.sqlite.bean.ResourceFile;
import com.wxtoplink.api4.sqlite.db.BodyInductionDao;
import com.wxtoplink.api4.sqlite.db.BtnDao;
import com.wxtoplink.api4.sqlite.db.CustomerDao;
import com.wxtoplink.api4.sqlite.db.DaoMaster;
import com.wxtoplink.api4.sqlite.db.DaoSession;
import com.wxtoplink.api4.sqlite.db.DeviceResourceFileDao;
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

    private DeviceResourceFileDao deviceResourceFileDao ;

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
        deviceResourceFileDao = daoSession.getDeviceResourceFileDao();
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

    public DeviceResourceFileDao getDeviceResourceFileDao(){
        if(!initSuccess){
            return null ;
        }
        return deviceResourceFileDao ;
    }

    public SQLiteDatabase getDb() {
        if(!initSuccess){
            return null ;
        }
        return db;
    }

    /** Customer类操作方法 **/

    public long insertCustomer(Customer customer){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(customer,OperateType.INSERT);
        return customerDao.insert(customer) ;
    }

    public void updateCustomer(Customer customer){
        if(!initSuccess){
            return ;
        }
        operateDataCall(customer,OperateType.UPDATE);
        customerDao.update(customer);
    }

    public List<Customer> queryCustomerList(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>() ;
        }
        List<Customer> list = customerDao.queryBuilder().where(cond,condMore).list();
        operateDataCall(list,OperateType.QUERY);
        return list ;
    }

    public Customer queryCustomerUnique(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return null ;
        }
        Customer customer = customerDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(customer,OperateType.QUERY);
        return customer ;
    }

    /** Btn类操作方法 **/

    public long insertBtn(Btn btn){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(btn,OperateType.INSERT);
        return btnDao.insert(btn);
    }

    public void updateBtn(Btn btn){
        if(!initSuccess){
            return ;
        }
        operateDataCall(btn,OperateType.UPDATE);
        btnDao.update(btn);
    }

    public List<Btn> queryBtnList(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>() ;
        }
        List<Btn> list = btnDao.queryBuilder().where(cond,condMore).list();
        operateDataCall(list,OperateType.QUERY);
        return list ;
    }

    public Btn queryBtnUnique(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return null ;
        }

        Btn btn = btnDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(btn,OperateType.QUERY);
        return btn ;
    }

    /** BodyInduction 类操作方法 **/

    public long insertBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(bodyInduction,OperateType.INSERT);
        return bodyInductionDao.insert(bodyInduction);
    }

    public void updateBodyInduction(BodyInduction bodyInduction){
        if(!initSuccess){
            return ;
        }
        operateDataCall(bodyInduction,OperateType.UPDATE);
        bodyInductionDao.update(bodyInduction);
    }


    public List<BodyInduction> queryBodyInductionList(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>() ;
        }
        List<BodyInduction> list = bodyInductionDao.queryBuilder().where(cond,condMore).list();
        operateDataCall(list,OperateType.QUERY);
        return list ;
    }

    public BodyInduction queryBodyInductionUnique(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return null ;
        }

        BodyInduction bodyInduction = bodyInductionDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(bodyInduction,OperateType.QUERY);
        return bodyInduction ;
    }

    /** Product类操作方法 **/

    public long insertProduct(Product product){
        if(!initSuccess){
            return -1 ;
        }
        operateDataCall(product,OperateType.INSERT);
        return productDao.insert(product);
    }

    public void updateProduct(Product product){
        if(!initSuccess){
            return ;
        }
        operateDataCall(product,OperateType.UPDATE);
        productDao.update(product);
    }

    public List<Product> queryProductList(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>() ;
        }
        List<Product> list = productDao.queryBuilder().where(cond,condMore).list();
        operateDataCall(list,OperateType.QUERY);
        return list ;
    }

    public Product queryProductUnique(WhereCondition cond,WhereCondition... condMore){
        if(!initSuccess){
            return null ;
        }

        Product product = productDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(product,OperateType.QUERY);
        return product ;
    }


    /** ResourceFile 类操作方法**/

    //根据文件名称查询ResourceFile
    public ResourceFile queryResourceFileUnique(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return new ResourceFile();
        }
        ResourceFile resourceFile =  resourceFileDao.queryBuilder()
                .where(cond,condMore).unique();
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

    /** Resource 类操作方法 **/

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
        resourceDao.update(resource);
    }

    //更新资源类别
    public void updateResource(Iterable<Resource> iterable){
        if(!initSuccess){
            return ;
        }
        operateDataCall(iterable,OperateType.UPDATE);
        resourceDao.updateInTx(iterable);
    }

    //根据条件查询唯一的资源类别
    public Resource queryResourceUnique(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return null;
        }
        Resource resource = resourceDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(resource,OperateType.QUERY);
        return resource;
    }

    //根据条件查询资源类别列表
    public List<Resource> queryResourceList(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>();
        }
        List<Resource> resources = resourceDao.queryBuilder().where(cond,condMore).list();
        operateDataCall(resources,OperateType.QUERY);
        return resources;
    }

    //获取当前资源类别的可用版本
    public Resource queryEnableResource(ResourceType resourceType){
        return queryEnableResource(resourceType.getTypeId());
    }

    //获取当前资源类别的可用版本
    public Resource queryEnableResource(long resourceTypeId){
        if(!initSuccess){
            return null ;
        }
        Resource resource = resourceDao.queryBuilder()
                .where(ResourceDao.Properties.TypeId.eq(resourceTypeId)
                        ,ResourceDao.Properties.Status.eq(Status.ENABLE.getStatusId())).unique();
        operateDataCall(resource,OperateType.QUERY);
        return resource;
    }

    /** DeviceResourceFile 类的操作方法 **/

    //插入设备本地资源记录
    public long insertDeviceResourceFile(DeviceResourceFile deviceResourceFile){
        if(!initSuccess){
            return -1;
        }
        operateDataCall(deviceResourceFile,OperateType.INSERT);
        return deviceResourceFileDao.insert(deviceResourceFile);
    }

    //插入设备本地资源记录列表
    public void insertDeviceResourceFiles(Iterable<DeviceResourceFile> iterable){
        if(!initSuccess){
            return ;
        }
        operateDataCall(iterable,OperateType.INSERT);
        deviceResourceFileDao.insertInTx(iterable);
    }

    //删除本地资源列表记录
    public void deleteAllDeviceResourceFile(){
        if(!initSuccess){
            return;
        }
        operateDataCall(deviceResourceFileDao.loadAll(),OperateType.DELETE);
        deviceResourceFileDao.deleteAll();
    }

    //删除本地资源记录列表
    public void deleteDeviceResourceFiles(Iterable<DeviceResourceFile> iterable){
        if(!initSuccess){
            return;
        }
        operateDataCall(iterable,OperateType.DELETE);
        deviceResourceFileDao.deleteInTx(iterable);
    }

    //根据条件查询唯一的设备资源
    public DeviceResourceFile queryDeviceResourceFileUnique(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return null ;
        }
        DeviceResourceFile deviceResourceFile = deviceResourceFileDao.queryBuilder().where(cond,condMore).unique();
        operateDataCall(deviceResourceFile,OperateType.QUERY);
        return deviceResourceFile ;
    }

    //根据条件查询设备资源列表
    public List<DeviceResourceFile> queryDeviceResourceFileList(WhereCondition cond, WhereCondition... condMore){
        if(!initSuccess){
            return new ArrayList<>();
        }
        List<DeviceResourceFile> deviceResourceFiles = deviceResourceFileDao
                .queryBuilder().where(cond,condMore).list();
        operateDataCall(deviceResourceFiles,OperateType.QUERY);
        return deviceResourceFiles;
    }

    //更新本地资源记录
    public void updateDeviceResourceFile(DeviceResourceFile deviceResourceFile){
        if(!initSuccess){
            return;
        }
        operateDataCall(deviceResourceFile,OperateType.UPDATE);
        deviceResourceFileDao.update(deviceResourceFile);
    }

    private void operateDataCall(Object obj,OperateType type){
        if(api4DBOperateCallBack != null && obj != null){
            api4DBOperateCallBack.operateData(GSONUtils.toJson(obj),type);
        }
    }

}
