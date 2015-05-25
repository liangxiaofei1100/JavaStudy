package com.alex.javastudy.spring.resttemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo {
    private static Logger logger = LogManager.getLogger(RestTemplateDemo.class);

    public void launch() {
        RestTemplate restTemplate = new RestTemplate();
        IpInfo ipInfo = restTemplate
                .getForObject(
                        "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=",
                        IpInfo.class);

        logger.debug("IpInfo: " + ipInfo);
    }
}
