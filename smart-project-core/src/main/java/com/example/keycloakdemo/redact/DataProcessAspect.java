package com.example.keycloakdemo.redact;


import com.alibaba.fastjson.JSONObject;

import com.example.keycloakdemo.annotation.SensitiveFields;
import com.example.keycloakdemo.util.AESUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Aspect
@Component
public class DataProcessAspect {

 /*   @Pointcut("execution(* com.example.keycloakdemo.*.*(..)) || @annotation(com.example.keycloakdemo.annotation.SensitiveField)")
    public void pointCut() {
        log.info("pointCut start");||  @annotation(com.example.keycloakdemo.annotation.SensitiveField
    }*/

    @Pointcut("execution(* com.example.keycloakdemo.dao.*Dao.*(..)) ||  @annotation(com.example.keycloakdemo.annotation.SensitiveFields)")
    public void pointCut() {
        log.info("pointCut start");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around start");
        Object[] pointArgs = joinPoint.getArgs();
        for (int i = 0; i < pointArgs.length; i++) {
            Object arg = pointArgs[i];
            JSONObject jsonObject = (JSONObject)JSONObject.toJSON(arg);
            Field[] declaredFields = pointArgs[i].getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                log.info("obj |{} |{}", pointArgs[i], field.getName());
                SensitiveFields annotation = field.getAnnotation(SensitiveFields.class);
                if (annotation != null) {
                    if(!StringUtils.isEmpty(jsonObject.getString(field.getName()))){
                        field.setAccessible(true);
                        field.set(pointArgs[i], AESUtil.encrypt(jsonObject.getString(field.getName())));
                        System.out.println("field : " + field.getName() + " \n o encrypt value :" + pointArgs[i]);
                    }
                }
            }
        }
        Object resultData = joinPoint.proceed();
        if(ObjectUtils.isEmpty(resultData)){
        }
        if (resultData instanceof Collection){
            List<Object> objectList = (List) resultData;
            for (Object item : objectList) {
                Class aClass = item.getClass();
                /*Class classT = (Class) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0];*/
                Field[] fields = aClass.getDeclaredFields();
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(item));
                for(Field field : fields){
                    SensitiveFields annotation = field.getAnnotation(SensitiveFields.class);
                    if (annotation != null) {
                        if(!StringUtils.isEmpty(jsonObject.getString(field.getName()))){
                            field.setAccessible(true);
                            field.set(item, AESUtil.decryptTest(jsonObject.getString(field.getName())));
                            System.out.println("field : " + field.getName() + " \n o encrypt value :" + resultData);
                        }
                    }
                }
            }
        }else {
            JSONObject jsonObject = (JSONObject)JSONObject.toJSON(resultData);
            String name = resultData.getClass().getName();
            Class<?> aClass = Class.forName(name);
            Field[] declaredFields = aClass.getDeclaredFields();
            for(Field field : declaredFields){
                SensitiveFields annotation = field.getAnnotation(SensitiveFields.class);
                if (annotation != null) {
                    if(!StringUtils.isEmpty(jsonObject.getString(field.getName()))){
                        field.setAccessible(true);
                        field.set(resultData, AESUtil.encrypt(jsonObject.getString(field.getName())));
                        System.out.println("field : " + field.getName() + " \n o encrypt value :" + resultData);
                    }
                }
            }
        }

        return resultData;

    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        log.info("after log");
        Object[] args = joinPoint.getArgs();

        for(int i=0; i<args.length;i++){
            Object arg = args[i];
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Class returnType = methodSignature.getReturnType();
        String name = signature.getName();

        Object target = joinPoint.getTarget();
        Class<?> aClass = target.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field field : declaredFields){
            log.info("field name |{}",field.getName());
        }


    }
}
