package com.github.wxz.utils;


import com.github.wxz.enums.IndexType;
import com.github.wxz.syncer.IndexSyncer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
@Service
public class Configs {
    private static final Logger LOGGER = LoggerFactory.getLogger(Configs.class);


    @PostConstruct
    private void init() {
        InputStream in = null;
        ClassLoader classLoader = Configs.class.getClassLoader();

        InputStream esIn = classLoader.getResourceAsStream("esmapper/es.properties");
        try {
            String config = IOUtils.toString(in).trim();
            String esConfig = IOUtils.toString(esIn).trim();
            Properties properties = PropertiesUtils.readFromText(config);
            Properties esProperties = PropertiesUtils.readFromText(esConfig);
            OutputStream fos = new FileOutputStream(classLoader.getResource("esmapper/es.properties").getPath());
            esProperties.setProperty("esName", properties.getProperty("es.name"));
            esProperties.setProperty("esHost", properties.getProperty("es.host"));
            esProperties.setProperty("esPort", properties.getProperty("es.port"));
            esProperties.setProperty("esIndice", properties.getProperty("es.indice"));
            esProperties.store(fos, null);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LOGGER.error("get config error ...");
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(esIn);
        }
        try {
            new IndexSyncer(IndexType.GOODS).start();
        } catch (Exception e) {
            LOGGER.error("init goods index error ...");
        }

    }


}
