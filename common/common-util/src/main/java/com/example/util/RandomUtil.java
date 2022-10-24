package com.example.util;

import org.springframework.util.unit.DataUnit;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * PACKAGE_NAME
 *
 * @author xzwnp
 * 2022/5/6
 * 16:17
 */
public class RandomUtil {
    /**
     * 指定位数的纯数字编号
     */
    public static String randomSerialNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    /**
     * 生成大于14位的日期开头的序列号
     *
     * @param length 序列号长度
     * @return
     */
    public static String randomDatesSn(int length) {
        if (length < 14) {
            throw new IllegalArgumentException("length can't be smaller than 14!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //生成纯数字日期,8位
        String date = formatter.format(LocalDateTime.now());

        return date + randomSerialNumber(length - 14);
    }


    public static int randomIntNumber(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("the start is larger than the end");
        }
        int interval = end - start;
        return start + (int) (Math.random() * interval);
    }


    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");

    }
}
