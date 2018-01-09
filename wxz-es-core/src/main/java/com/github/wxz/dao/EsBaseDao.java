package com.github.wxz.dao;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public interface EsBaseDao<T> {
    /**
     * add
     * @param t
     * @return
     */
    boolean add(T t);

    /**
     * update
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     * delete
     * @param t
     * @return
     */
    boolean delete(T t);
}
