package com.baway.liuheng.playerdemo.dao;

import android.content.Context;

import com.baway.liuheng.playerdemo.bean.UserBean;
import com.baway.liuheng.playerdemo.utils.DBManager;
import com.bw.liuheng.greendao.gen.DaoMaster;
import com.bw.liuheng.greendao.gen.DaoSession;
import com.bw.liuheng.greendao.gen.UserBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by lenovo on 2017/11/23.
 */

public class MyUserDao {
    Context context;

    public MyUserDao(Context context) {
        this.context = context;
    }
    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(UserBean user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao beanDao = daoSession.getUserBeanDao();
        beanDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<UserBean> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        userBeanDao.insertInTx(users);
//        UserDao userDao = daoSession.getUserDao();
//        userDao.insertInTx(users);
    }
    /**
     * 查询用户列表
     */
    public List<UserBean> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userDao = daoSession.getUserBeanDao();
        QueryBuilder<UserBean> qb = userDao.queryBuilder();
        List<UserBean> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<UserBean> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userDao = daoSession.getUserBeanDao();
        QueryBuilder<UserBean> qb = userDao.queryBuilder();
        qb.where(UserBeanDao.Properties.Age.gt(age)).orderAsc(UserBeanDao.Properties.Age);
        List<UserBean> list = qb.list();
        return list;
    }
    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(UserBean user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userDao = daoSession.getUserBeanDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(UserBean user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(context).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userDao = daoSession.getUserBeanDao();
        userDao.update(user);
    }



}
