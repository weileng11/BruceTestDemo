package com.bruce.core.database.greendao.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Bruce on 2017/8/17.
 * <p>
 * function: greedDAO 基础辅助类(对数据的处理--如增删改查)--是所有实体类的相对应实现类的父类
 * 泛型：T表示实体类对象，K表示与该实体类对象相对应的主键id
 */

public class BaseDbHelper<T, K> {

    //(各实体类对象的)抽象GreenDao
    private AbstractDao<T, K> mDao;

    public BaseDbHelper(AbstractDao dao) {
        mDao = dao;
    }


    /**
     * function:添加数据--增加单个数据
     *
     * @param item
     */
    public void save(T item) {
        mDao.insert(item);
    }

    /**
     * function:添加数据--增加多个数据
     *
     * @param items
     */
    public void save(T... items) {
        mDao.insertInTx(items);
    }

    /**
     * function:添加数据--增加参数列表中所有的数据
     *
     * @param items
     */
    public void save(List<T> items) {
        mDao.insertInTx(items);
    }

    /**
     * function:添加数据，如果有重复则覆盖--增加单个数据
     *
     * @param item
     */
    public void saveOrUpdate(T item) {
        mDao.insertOrReplace(item);
    }

    /**
     * function:添加数据--增加多个数据(如果有重复则覆盖)
     *
     * @param items
     */
    public void saveOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * function:添加数据--增加参数列表中所有的数据(如果有重复则覆盖)
     *
     * @param items
     */
    public void saveOrUpdate(List<T> items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * function:删除数据
     *
     * @param key 该实体类对象所对应的主键key
     */
    public void deleteByKey(K key) {
        mDao.deleteByKey(key);
    }

    /**
     * function:删除数据--删除单个数据
     *
     * @param item
     */
    public void delete(T item) {
        mDao.delete(item);
    }

    /**
     * function:删除数据--删除多个数据
     *
     * @param items
     */
    public void delete(T... items) {
        mDao.deleteInTx(items);
    }

    /**
     * function:删除数据--删除参数列表中含有的数据
     *
     * @param items
     */
    public void delete(List<T> items) {
        mDao.deleteInTx(items);
    }

    /**
     * function:删除所有数据
     */
    public void deleteAll() {
        mDao.deleteAll();
    }


    /**
     * function:更新数据(获取到数据然后对数据进行修改之后需要更新)
     *
     * @param item
     */
    public void update(T item) {
        mDao.update(item);
    }


    public void update(T... items) {
        mDao.updateInTx(items);
    }

    public void update(List<T> items) {
        mDao.updateInTx(items);
    }

    /**
     * function:查询单个数据--根据主键值进行查找
     *
     * @param key
     * @return
     */
    public T query(K key) {
        return mDao.load(key);
    }

    /**
     * function:查询所有数据--返回一个List装载所有数据
     *
     * @return
     */
    public List<T> queryAll() {
        return mDao.loadAll();
    }

    /**
     * function:查询数据--使用原始SQL语句进行查找
     *
     * @param where
     * @param params
     * @return
     */
    public List<T> query(String where, String... params) {

        return mDao.queryRaw(where, params);
    }

    /**
     * function:返回QueryBuilder对象，帮助构建自定义的查询语句
     *
     * @return
     */
    public QueryBuilder<T> queryBuilder() {

        return mDao.queryBuilder();
    }

    /**
     * function:返回数据总数
     *
     * @return
     */
    public long count() {
        return mDao.count();
    }


    public void refresh(T item) {
        mDao.refresh(item);

    }

    public void detach(T item) {
        mDao.detach(item);
    }
}
