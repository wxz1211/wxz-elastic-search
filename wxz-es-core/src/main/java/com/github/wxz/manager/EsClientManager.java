package com.github.wxz.manager;


import com.github.wxz.enums.IndexType;
import com.github.wxz.utils.EsProp;
import com.github.wxz.utils.PropertiesUtils;
import com.google.common.base.Preconditions;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class EsClientManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsClientManager.class);
    private static EsProp esProp = new EsProp();
    private static volatile TransportClient client;

    static {
        InputStream esIn = EsClientManager.class.getClassLoader().getResourceAsStream("esmapper/es.properties");
        try {
            Properties properties = PropertiesUtils.readFromText(IOUtils.toString(esIn).trim());
            esProp.setClusterName(properties.getProperty("esName"));
            esProp.setHost(properties.getProperty("esHost"));
            esProp.setPort(Integer.valueOf(properties.getProperty("esPort")));
            esProp.setInDice(properties.getProperty("esIndice"));
        } catch (IOException e) {
            LOGGER.error("get es config error ...");
        }
    }

    private EsClientManager() {
    }

    public static Client getClient() {
        if (client == null) {
            synchronized (EsClientManager.class) {
                if (client == null) {
                    try {
                        LOGGER.info("esName {}", esProp.getClusterName());
                        LOGGER.info("host {}", esProp.getHost());
                        LOGGER.info("port {}", esProp.getPort());
                        Settings settings = Settings
                                .builder()
                                .put("cluster.name", esProp.getClusterName())
                                .put("client.transport.ignore_cluster_name", true)
                                .put("client.transport.sniff", true).build();
                        client = new PreBuiltTransportClient(settings);
                        client = client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esProp.getHost()), esProp.getPort()));
                        int nodeSize = client.connectedNodes().size();
                        LOGGER.info("nodeSize {}", nodeSize);
                        Preconditions.checkArgument(nodeSize >= 1, "this is no available node");
                        initIndex();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return client;
    }

    /**
     * 初始化索引
     *
     * @throws Exception
     */
    private static void initIndex() throws Exception {
        String indice = esProp.getInDice();
        IndicesAdminClient c = client.admin().indices();
        //创建一个空的
        boolean a = c.prepareExists(indice).get().isExists();
        LOGGER.info("index {} isExists  {}",indice, a);
        if (!c.prepareExists(indice).get().isExists()) {
            CreateIndexResponse createIndexResponse =c.prepareCreate(indice).get();
            LOGGER.info("create index {}", createIndexResponse);
        }
        for (IndexType type : IndexType.values()) {
            TypesExistsResponse typesExistsResponse = c.typesExists(new TypesExistsRequest(new String[]{indice}, type.getDataName())).get();
            if (typesExistsResponse.isExists()) {
                continue;
            }
            String esMapper = type.getMapper();
            InputStream in = EsClientManager.class.getResourceAsStream(esMapper);
            String mappingStr = IOUtils.toString(in).trim();
            IOUtils.closeQuietly(in);
            c.preparePutMapping(indice).setType(type.getDataName()).setSource(mappingStr).get();
        }
    }


}

