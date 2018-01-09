package com.github.wxz.entity;


import com.github.wxz.enums.IndexType;
import com.github.wxz.enums.OperatorType;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class IndexUpdateObject {
    private final Integer id;

    private final OperatorType operatorType;

    private final IndexType indexType;

    public IndexUpdateObject(Integer id, OperatorType operatorType, IndexType indexType) {
        super();
        this.id = id;
        this.operatorType = operatorType;
        this.indexType = indexType;
    }


    public void offer() {
        this.indexType.offerQueue(new UpdatedObject(id, operatorType));
    }
}
