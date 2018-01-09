package com.github.wxz.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class EsDataUtil {
    /**
     * jackson用于序列化操作的mapper
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    public static byte[] toBytes(Object value) {
        try {
            return mapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readValue(byte[] bytes, Class<T> valueType) {
        try {
            return mapper.readValue(bytes, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
