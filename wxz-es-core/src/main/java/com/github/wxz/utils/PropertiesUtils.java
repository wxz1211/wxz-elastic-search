package com.github.wxz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class PropertiesUtils {
    protected static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    public static final Properties readFromText(String text) {
        Properties p = new Properties();
        try (Reader reader = new StringReader(text)) {
            p.load(reader);
        } catch (IOException e) {
            LOGGER.error(text, e);
        }
        return p;
    }
}
