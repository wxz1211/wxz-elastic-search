package com.github.wxz.syncer;

import com.github.wxz.entity.IdAble;
import com.github.wxz.entity.UpdatedObject;
import com.github.wxz.enums.IndexType;
import com.github.wxz.manager.EsClientManager;
import com.github.wxz.utils.EsProp;
import com.github.wxz.utils.ExecutorManager;
import com.github.wxz.utils.PropertiesUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class IndexSyncer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexSyncer.class);
    private static EsProp esProp = new EsProp();

    static {
        InputStream esIn = EsClientManager.class.getClassLoader().getResourceAsStream("esmapper/es.properties");
        try {
            Properties properties = PropertiesUtils.readFromText(IOUtils.toString(esIn).trim());
            esProp.setClusterName(properties.getProperty("esName"));
            esProp.setHost(properties.getProperty("esHost"));
            esProp.setInDice(properties.getProperty("esIndice"));
        } catch (IOException e) {
            LOGGER.error("get es config error ...");
        } finally {
            IOUtils.closeQuietly(esIn);
        }
    }

    private final String name;
    private final IndexType indexType;

    public IndexSyncer(IndexType indexType) {
        this.indexType = indexType;
        this.name = indexType.getDataName();
    }

    public void start() {
        //startBatch();
        ExecutorManager.execute(() -> loadByUpdate());
    }


    private void loadInit() {

    }

    private void loadByUpdate() {
        while (true) {
            UpdatedObject<? extends IdAble> updated = null;
            try {
                updated = indexType.takeQueue();
            } catch (InterruptedException e) {
                LOGGER.error("index sync take queue error ...");
                continue;
            }
            try {
                if (updated != null) {
                    updated.getType().operator(esProp.getInDice(), indexType, updated.getObject());
                }
            } catch (Exception e) {
                LOGGER.error("in " + name + "update index error", e);
                //indexType.offerQueue(updated);
            }
        }
    }
}
