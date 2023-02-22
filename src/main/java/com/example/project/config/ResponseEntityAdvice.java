package com.example.project.config;

import com.alibaba.fastjson.JSONObject;
import com.example.project.vo.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ResponseEntityAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        if(servletResponse.getStatus() != HttpServletResponse.SC_OK){
            return body;
        }
        String path = request.getURI().getPath();
        if(path.startsWith("/swagger-resources") || path.startsWith("/webjars") || path.startsWith("/v2") || path.startsWith("/actuator")){
            return body;
        }
        servletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        ResponseEntity responseEntity;
        if(!(body instanceof  ResponseEntity)){
            responseEntity = new ResponseEntity<>(body);
            if(body instanceof String){
                return JSONObject.toJSONString(responseEntity);
            }
        }else {
            responseEntity =  (ResponseEntity) body;
        }
        return responseEntity;
    }
}
