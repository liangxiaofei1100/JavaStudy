package com.alex.study.springsecurity.domain.common.http;

public class ResponseHeader {
    /**
     * 执行结果码
     */
    public int resultCode;
    /**
     * 执行结果文字
     */
    public String resultText;

    public static final int RESULT_CODE_OK = 0;
    public static final String RESULT_TEXT_OK_DEFAULT = "执行成功";
    public static final int RESULT_CODE_FAIL = 1;
    public static final String RESULT_TEXT_FAIL_DEFAULT = "执行失败";

    public static final int RESULT_CODE_SERVER_ERROR = -1;
    public static final String RESULT_TEXT_SERVER_ERROR = "服务端错误";

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "resultCode=" + resultCode +
                ", resultText='" + resultText +
                '}';
    }
}
