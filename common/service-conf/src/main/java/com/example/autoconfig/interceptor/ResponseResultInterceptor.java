package com.example.autoconfig.interceptor;

import com.example.entity.InnerApi;
import com.example.entity.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author xzwnp
 * RestControllerAdvice：作用：对所有控制器中，被@RequestMapping注解标注的方法，
 * 进行增强,自动包上一层统一返回类
 */
@RestControllerAdvice(basePackages = "com.example")   // 控制器类增强：可以对Controller中所有使用@RequestMapping注解的方法增强
public class ResponseResultInterceptor<T> implements ResponseBodyAdvice<Object> {

    /**
     * 被拦截的响应，立即执行该方法。
     * body ：是请求控制器方法接口后，响应的内容。（其他参数不用了解）
     * 仅封装成功的返回,失败的返回请走统一异常处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            // 将数据包装在ResultVo里后转换为json串进行返回
            try {
                return objectMapper.writeValueAsString(R.success(body));
            } catch (JsonProcessingException e) {
                // 这里正常应该加如自定义的统一异常处理
                e.printStackTrace();
            }
        }
        //其他类型正常返回即可
        return R.success(body);
    }

    /**
     * 这个方法的返回值，决定是否启动结果响应拦截，当返回为true是，表示拦截
     * 返回类型如果已经为R就无需拦截了
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //如果是内部接口,就不需要包装了
        //InnerApi为自定义的注解
        if (returnType.getMethodAnnotation(InnerApi.class) != null) {
            return false;
        }
        //  getGenericParameterType获取到的type带有泛型,不使returnType = {HandlerMethod$ReturnValueMethodParameter@10455} "method 'getBusinessById' parameter -1"用
        return !returnType.getNestedParameterType().equals(R.class);
    }

}