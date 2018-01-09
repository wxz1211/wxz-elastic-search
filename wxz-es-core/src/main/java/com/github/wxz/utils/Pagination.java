package com.github.wxz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianzhi.wang
 * @date 2018/1/9 -14:55
 */
public class Pagination<T> {
    /**
     * 记录总数
     */
    private Integer totalCount = 0;

    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 列表记录
     */
    private List<T> dataList = new ArrayList<T>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        if (dataList != null) {
            this.dataList = dataList;
        }
    }
}
