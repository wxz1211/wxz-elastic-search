package com.github.wxz.entity;


import com.github.wxz.enums.OperatorType;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class UpdatedObject <T> {
    private T object;

    private OperatorType type;

    public UpdatedObject(T object, OperatorType type) {
        this.object = object;
        this.type = type;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public OperatorType getType() {
        return type;
    }

    public void setType(OperatorType type) {
        this.type = type;
    }
}
