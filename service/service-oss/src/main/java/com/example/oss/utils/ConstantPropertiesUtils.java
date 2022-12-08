package com.example.oss.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * com.example.oss.utils
 *
 * @author xzwnp
 * 2022/1/28
 * 18:53
 * 常用属性类,从application.properties中读取阿里云oss配置信息
 * 实现InitializingBean方法,当组件的几个成员变量被赋值后,会执行afterPropertiesSet()
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class ConstantPropertiesUtils implements InitializingBean {
    private String endpoint;

    private String keyid;

    private String keysecret;
    private String bucketname;
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(endpoint+"\n"+bucketname);
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public void setKeysecret(String keysecret) {
        this.keysecret = keysecret;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }
}
