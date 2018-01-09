package com.github.wxz.enums;


import com.github.wxz.entity.IdAble;
import com.github.wxz.manager.IndexManager;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public enum OperatorType {
    ADD("add") {
        @Override
        public <T extends IdAble> void operator(String index, IndexType type, T data) {
            IndexManager.addDocument(index, type, data);
        }

    },
    UPDATE("update") {
        @Override
        public <T extends IdAble> void operator(String index, IndexType type, T data) {
            IndexManager.updateDocument(index, type, data);
        }
    },
    DELETE("delete") {
        @Override
        public <T extends IdAble> void operator(String index, IndexType type, T data) {
            IndexManager.deleteDocument(index, type, data.getId());
        }
    };

    private String name;

    OperatorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    abstract public <T extends IdAble> void operator(String index, IndexType type, T data);
}
