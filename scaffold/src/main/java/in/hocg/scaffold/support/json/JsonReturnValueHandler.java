package in.hocg.scaffold.support.json;

import in.hocg.scaffold.constant.Charset;
import in.hocg.scaffold.support.json.annotation.JSON;
import in.hocg.scaffold.support.json.annotation.JSONs;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hocgin on 2018/9/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class JsonReturnValueHandler implements HandlerMethodReturnValueHandler,
        BeanPostProcessor {
    
    /**
     * 判断是否返回值类型
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(JSON.class) != null
                || returnType.getMethodAnnotation(JSONs.class) != null;
    }
    
    /**
     * 处理返回值
     *
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled(true);
        
        for (ResponseBodyAdvice<Object> ad : advices) {
            if (ad.supports(returnType, null)) {
                returnValue = ad.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, null,
                        new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class)),
                        new ServletServerHttpResponse(webRequest.getNativeResponse(HttpServletResponse.class)));
            }
        }
        
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        nativeResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        nativeResponse.setCharacterEncoding(Charset.UTF_8);
        Annotation[] methodAnnotations = returnType.getMethodAnnotations();
        DefaultJsonSerializer jsonSerializer = new DefaultJsonSerializer();
        Arrays.asList(methodAnnotations).forEach(a -> {
            // 解析注解，设置过滤条件
            if (a instanceof JSON) {
                JSON json = (JSON) a;
                jsonSerializer.filter(json);
            } else if (a instanceof JSONs) {
                // 使用多重注解时，实际返回的是 @Repeatable(JSONs.class) 内指定的 @JSONs 注解
                JSONs jsons = (JSONs) a;
                Arrays.asList(jsons.value())
                        .forEach(jsonSerializer::filter);
            }
        });
        
        String json = jsonSerializer.toJson(returnValue);
        nativeResponse.getWriter().write(json);
    }
    
    private List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ResponseBodyAdvice) {
            advices.add((ResponseBodyAdvice<Object>) bean);
        } else if (bean instanceof RequestMappingHandlerAdapter) {
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(
                    ((RequestMappingHandlerAdapter) bean).getReturnValueHandlers());
            JsonReturnValueHandler jsonHandler = null;
            for (HandlerMethodReturnValueHandler handler : handlers) {
                if (handler instanceof JsonReturnValueHandler) {
                    jsonHandler = (JsonReturnValueHandler) handler;
                    break;
                }
            }
            if (jsonHandler != null) {
                handlers.remove(jsonHandler);
                handlers.add(0, jsonHandler);
                ((RequestMappingHandlerAdapter) bean).setReturnValueHandlers(handlers);
            }
        }
        return bean;
    }
}
