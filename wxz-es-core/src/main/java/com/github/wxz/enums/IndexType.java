package com.github.wxz.enums;


import com.github.wxz.entity.Goods;
import com.github.wxz.entity.IdAble;
import com.github.wxz.entity.UpdatedObject;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public enum IndexType {
    GOODS("goods") {
        @Override
        public String getMapper() {
            return "/esmapper/goodsMapper.txt";
        }

        @Override
        public Class<? extends IdAble> getTypeClass() {
            return Goods.class;
        }
    };
    private String dataName;

    IndexType(String name) {
        this.dataName = name;
    }

    public String getDataName() {
        return dataName;
    }

    abstract public String getMapper();

    abstract public Class getTypeClass();

    /**
     * 生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费
     */
    private TransferQueue<UpdatedObject<? extends IdAble>> transferQueue = new LinkedTransferQueue<>();

    public UpdatedObject<? extends IdAble> takeQueue() throws InterruptedException {
        return transferQueue.take();
    }

    public boolean offerQueue(UpdatedObject<? extends IdAble> updatedObject) {
        if (transferQueue.tryTransfer(updatedObject)) {
            return true;
        }
        return transferQueue.offer(updatedObject);
    }
}
