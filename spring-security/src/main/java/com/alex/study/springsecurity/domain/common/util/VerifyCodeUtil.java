package com.alex.study.springsecurity.domain.common.util;

import java.util.Random;

public class VerifyCodeUtil {

    /**
     * 生成随机4位验证码
     */
    public static String generateVerifyCode() {
        Random random = new Random();
        int n = random.nextInt(9000);
        return String.valueOf(1000 + n);
    }
}
