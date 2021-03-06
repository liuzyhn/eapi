package com.shawn.sys.swagger.controller;;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ObjectMapper objectMapper;

    protected void logRequest(String service, Object... params) {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(service).append("] 请求数据: ");
        for (int i = 0; i <  params.length; i++) {
            if (i%2 == 0) {
                sb.append(params[i]).append(":");
            } else {
                try {
                    sb.append(objectMapper.writeValueAsString(params[i]));
                } catch (JsonProcessingException e) {
                    sb.append("解析异常！");
                    e.printStackTrace();
                }
                if (i < params.length - 1) {
                    sb.append(", ");
                }
            }
        }
        logger.info(sb.toString());
    }
    protected void logResponse(String service, Object response) {
        String responseJson;
        try {
            responseJson = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            responseJson = "解析异常！";
        }

        if(responseJson.length() > 1000){
            responseJson = responseJson.substring(0, 1000);
            responseJson += "......";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("[").append(service).append("] 响应数据: ");
        sb.append(responseJson);
        logger.info(sb.toString());
    }
}
