package com.example.keycloakdemo.config;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author wy
 * Date 2018/9/19 0019
 * Description: 异常统一处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, IllegalArgumentException.class})
    public ResponseEntity otherExceptionHandler(Exception e) {
        log.error("otherExceptionHandler.getMessage | {} | {}", e.getMessage(), e);
        String exceptionMsg = getStackTrace(e.fillInStackTrace());
        log.error(exceptionMsg);

        Map exceptionMap = new HashMap(4);
        exceptionMap.put("message", e.getMessage());
        exceptionMap.put("error", exceptionMsg);
        exceptionMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionMap.put("timestamp", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

        return new ResponseEntity(exceptionMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取详细的异常链信息--精准定位异常位置
     *
     * @param aThrowable
     * @return
     */
    public String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }


    /**
     * Bean 校验异常 Validate
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class) //400
    @ResponseBody
    public ResponseEntity methodArgumentValidationHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.info("异常:" + request.getRequestURI(), exception);
        log.error("请求参数错误！{}", getExceptionDetail(exception), "参数数据：" + showParams(request));
        Map<String, Object> exceptionMap = new HashMap(4);
        if (exception.getBindingResult() != null && !CollectionUtils.isEmpty(exception.getBindingResult().getAllErrors())) {
            exceptionMap.put("message", exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            exceptionMap.put("error", "参数校验错误");
            exceptionMap.put("code", HttpStatus.BAD_REQUEST.value());
            exceptionMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } else {
            exceptionMap.put("message", exception.getMessage());
            exceptionMap.put("error", "参数校验错误");
            exceptionMap.put("code", HttpStatus.BAD_REQUEST.value());
            exceptionMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        return new ResponseEntity<>(exceptionMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * 异常详情
     *
     * @param e
     * @return
     */
    private String getExceptionDetail(Exception e) {
        StringBuilder stringBuffer = new StringBuilder(e.toString() + "\n");
        StackTraceElement[] messages = e.getStackTrace();
        Arrays.stream(messages).filter(Objects::nonNull).forEach(stackTraceElement -> {
            stringBuffer.append(stackTraceElement.toString() + "\n");
        });
        return stringBuffer.toString();
    }

    /**
     * 请求参数
     *
     * @param request
     * @return
     */
    public String showParams(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration paramNames = request.getParameterNames();
        stringBuilder.append("----------------参数开始-------------------");
        stringBuilder.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (Objects.nonNull(paramNames)) {
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length > 0) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        stringBuilder.append("参数名:").append(paramName).append("参数值:").append(paramValue);
                    }
                }
            }
        }
        stringBuilder.append("----------------参数结束-------------------");
        return stringBuilder.toString();
    }
/*
    *//**
     * 第三方调用服务异常
     *
     * @param exception
     * @return
     *//*
    @ExceptionHandler(value = {RetryableException.class, FeignException.class})
    public ResponseEntity retryableExceptionHandler(Exception exception) {
        log.error("exception.getMessage | {} | {}", exception.getMessage(), exception);
        String exceptionMsg = getStackTrace(exception.fillInStackTrace());
        Map<String, Object> exceptionMap = new HashMap(4);
        exceptionMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (exception instanceof FeignException) {
            Request request = ((FeignException) exception).request();
            RequestTemplate requestTemplate = request.requestTemplate();
            String name = requestTemplate.feignTarget().name();
            exceptionMap.put("message", "调用第三方服务[" + name + "]获取数据失败");
            exceptionMap.put("error", exceptionMsg);
            log.info("messageError | {}", exception.getMessage());
        } else {
            exceptionMap.put("message", exception.getMessage());
            exceptionMap.put("error", exceptionMsg);
        }
        return new ResponseEntity(exceptionMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
