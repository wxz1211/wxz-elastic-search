package com.github.wxz.dao;

import com.github.wxz.entity.Goods;
import com.github.wxz.entity.UpdatedObject;
import com.github.wxz.enums.IndexType;
import com.github.wxz.enums.OperatorType;
import org.springframework.stereotype.Service;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
@Service
public class GoodsEsDao implements EsBaseDao<Goods> {
    @Override
    public boolean add(Goods goods) {
        UpdatedObject<Goods> object = new UpdatedObject<>(goods, OperatorType.ADD);
        return IndexType.GOODS.offerQueue(object);
    }

    @Override
    public boolean update(Goods goods) {
        UpdatedObject<Goods> object = new UpdatedObject<>(goods, OperatorType.UPDATE);
        return IndexType.GOODS.offerQueue(object);
    }

    @Override
    public boolean delete(Goods goods) {
        UpdatedObject<Goods> object = new UpdatedObject<>(goods, OperatorType.DELETE);
        return IndexType.GOODS.offerQueue(object);
    }

}
