package com.wxtoplink.api4.sqlite;

import com.wxtoplink.api4.api4interface.API4DBOperateCallBack;
import com.wxtoplink.api4.bean.EventType;
import com.wxtoplink.api4.sqlite.bean.BodyInduction;
import com.wxtoplink.api4.sqlite.bean.Btn;
import com.wxtoplink.api4.sqlite.bean.Customer;
import com.wxtoplink.api4.sqlite.bean.InteractiveData;
import com.wxtoplink.api4.sqlite.bean.Product;
import com.wxtoplink.api4.sqlite.db.BodyInductionDao;
import com.wxtoplink.api4.sqlite.db.BtnDao;
import com.wxtoplink.api4.sqlite.db.CustomerDao;
import com.wxtoplink.api4.sqlite.db.ProductDao;
import com.wxtoplink.api4.util.GSONUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用于构建未发送的数据列表
 * Created by 12852 on 2018/10/30.
 */

public class InteractiveDataBuilder {

    private boolean customer = false,btn = false ,bodyInduction = false,product = false ;

    private final API4DBOperateCallBack api4DBOperateCallBack ;

    public InteractiveDataBuilder() {
        this.api4DBOperateCallBack = SqliteOperateUtil.getInstance().getApi4DBOperateCallBack();
    }

    public InteractiveDataBuilder customer(){
        customer = true ;
        return this ;
    }

    public InteractiveDataBuilder product(){
        product = true ;
        return this ;
    }

    public InteractiveDataBuilder btn(){
        btn = true ;
        return this ;
    }

    public InteractiveDataBuilder bodyInduction(){
        bodyInduction = true ;
        return this ;
    }

    //构建目标数据
    public List<InteractiveData> build(){
        if(customer || btn || product || bodyInduction){
            List<InteractiveData> interactiveDataList = new ArrayList<>();
            if(customer){
                interactiveDataList.add(buildCustomer());
            }
            if(btn){
                interactiveDataList.add(buildBtn());
            }
            if(product){
                interactiveDataList.add(buildProduct());
            }
            if(bodyInduction){
                interactiveDataList.add(buildBodyInduction());
            }
            customer = false;btn = false;product = false;bodyInduction = false;
            if(api4DBOperateCallBack != null){
                api4DBOperateCallBack.operateData(GSONUtils.toJson(interactiveDataList),OperateType.QUERY);
            }
            return interactiveDataList;
        }

        return null;
    }

    //构建所有的交互数据
    public List<InteractiveData> buildAll(){
        List<InteractiveData> list = Arrays.asList(buildCustomer(),buildBtn(),buildProduct(),buildBodyInduction());
        if(api4DBOperateCallBack != null){
            api4DBOperateCallBack.operateData(GSONUtils.toJson(list),OperateType.QUERY);
        }

        return list ;
    }

    //构建用户数据
    public InteractiveData buildCustomer(){
        CustomerDao customerDao = SqliteOperateUtil.getInstance().getCustomerDao();
        if(customerDao == null){
            return new InteractiveData();
        }
        List<Customer> customers =  customerDao.queryBuilder().where(CustomerDao.Properties.State.eq(0)).build().list();
        for(Customer customer:customers){
            customer.setState(1);
        }
        //更新数据
        customerDao.updateInTx(customers);
        return new InteractiveData<Customer>(EventType.TYPE_3.toString(),customers);
    }

    //构建用户点击数据
    public InteractiveData buildBtn(){
        BtnDao btnDao = SqliteOperateUtil.getInstance().getBtnDao();
        if(btnDao == null){
            return new InteractiveData();
        }
        List<Btn> btns =  btnDao.queryBuilder().where(BtnDao.Properties.State.eq(0)).build().list();
        for(Btn btn:btns){
            btn.setState(1);
        }
        //更新数据
        btnDao.updateInTx(btns);
        return new InteractiveData<Btn>(EventType.TYPE_5.toString(),btns);
    }

    //构建产品拿起放下数据数据
    public InteractiveData buildProduct(){
        ProductDao productDao = SqliteOperateUtil.getInstance().getProductDao();
        if(productDao == null){
            return new InteractiveData();
        }
        List<Product> products =  productDao.queryBuilder().where(ProductDao.Properties.State.eq(0)).build().list();
        for(Product product:products){
            product.setState(1);
        }
        //更新数据
        productDao.updateInTx(products);
        return new InteractiveData<Product>(EventType.TYPE_4.toString(),products);
    }

    public InteractiveData buildBodyInduction(){
        BodyInductionDao bodyInductionDao = SqliteOperateUtil.getInstance().getBodyInductionDao();
        if(bodyInductionDao == null){
            return new InteractiveData();
        }
        List<BodyInduction> bodyInductions = bodyInductionDao.queryBuilder().where(BodyInductionDao.Properties.State.eq(0)).build().list();
        for(BodyInduction bodyInduction:bodyInductions){
            bodyInduction.setState(1);
        }
        //更新数据
        bodyInductionDao.updateInTx(bodyInductions);
        return new InteractiveData<BodyInduction>(EventType.TYPE_6.toString(),bodyInductions);
    }

}
