package com.alex.javastudy.spring.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * IP接口 新浪接口(ip值为空的时候 获取本地的) </br>
 * 
 * http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255
 * .255 </br>
 * 
 * { "ret": 1, "start": -1, "end": -1, "country": "中国", "province": "上海",
 * "city": "上海", "district": "", "isp": "", "type": "", "desc": "" }
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfo {

    private String country;
    private String province;
    private String city;

    @Override
    public String toString() {
        return "IpInfo [country=" + country + ", province=" + province
                + ", city=" + city + "]";
    }
}
