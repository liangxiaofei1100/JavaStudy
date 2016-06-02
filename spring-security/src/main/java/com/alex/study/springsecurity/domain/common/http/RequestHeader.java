package com.alex.study.springsecurity.domain.common.http;

public class RequestHeader {
    /**
     * 用户id
     */
    public String userToken;
    /**
     * app版本号
     */
    public String clientVersion;
    /**
     * 渠道号
     */
    public String sourceID;
    /**
     * 服务版本号
     */
    public String serviceVersion;
    /**
     * 客户端时间
     */
    public long requestTime;

    @Override
    public String toString() {
        return "RequestHeader{" +
                "userToken='" + userToken + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", sourceID='" + sourceID + '\'' +
                ", serviceVersion=" + serviceVersion +
                ", requestTime=" + requestTime +
                '}';
    }
}
