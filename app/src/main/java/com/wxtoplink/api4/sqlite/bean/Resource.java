package com.wxtoplink.api4.sqlite.bean;

import com.wxtoplink.api4.constant.ResourceType;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.wxtoplink.api4.constant.Status;
import com.wxtoplink.api4.sqlite.db.DaoSession;
import com.wxtoplink.api4.sqlite.db.ResourceDao;
import com.wxtoplink.api4.sqlite.db.ResourceFileDao;

/**
 * Created by 12852 on 2018/11/2.
 */

@Entity
public class Resource {

    @Id(autoincrement = true)
    private Long id ;

    //资源类型
    private long typeId ;

    //资源类型名称
    private String typeName ;

    //当前状态
    private int status ;

    //资源版本号
    private String version ;

    //资源的前缀基础路径，index.html 跟在该路径后
    private String prefixPath ;

    @ToMany(referencedJoinProperty = "resourceId")
    private List<ResourceFile> fileList ;

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 666053351)
    public synchronized void resetFileList() {
        fileList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 164267985)
    public List<ResourceFile> getFileList() {
        if (fileList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ResourceFileDao targetDao = daoSession.getResourceFileDao();
            List<ResourceFile> fileListNew = targetDao._queryResource_FileList(id);
            synchronized (this) {
                if(fileList == null) {
                    fileList = fileListNew;
                }
            }
        }
        return fileList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 238739199)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getResourceDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 1235069143)
    private transient ResourceDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getPrefixPath() {
        return this.prefixPath;
    }

    public void setPrefixPath(String prefixPath) {
        this.prefixPath = prefixPath;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long getTypeId() {
        return this.typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 2074298078)
    public Resource(Long id, long typeId, String typeName, int status,
            String version, String prefixPath) {
        this.id = id;
        this.typeId = typeId;
        this.typeName = typeName;
        this.status = status;
        this.version = version;
        this.prefixPath = prefixPath;
    }

    @Generated(hash = 632359988)
    public Resource() {
    }

    public Resource(ResourceType resourceType , Status status, String prefixPath, String version, List<ResourceFile> fileList) {
        this.typeId = resourceType.getTypeId();
        this.typeName = resourceType.getTypeName();
        this.status = status.getStatusId();
        this.version = version;
        this.fileList = fileList;
        this.prefixPath = prefixPath ;
    }

}
