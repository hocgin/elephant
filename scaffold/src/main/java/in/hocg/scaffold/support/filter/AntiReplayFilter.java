package in.hocg.scaffold.support.filter;


import in.hocg.scaffold.lang.exception.AntiReplayException;
import in.hocg.scaffold.support.cache.CacheService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.annotation.WebFilter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hocgin
 * @date 18-8-20
 * 防重放攻击
 **/
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@WebFilter(filterName = "AntiReplayFilter", urlPatterns = {"/*"})
public class AntiReplayFilter extends SimpleHandlerFilter {
    
    @NonNull
    private final CacheService cacheService;
    @NonNull
    private final List<String> ignoreUrls;
    
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public boolean preHandle(ServletWebRequest servletWebRequest) throws Exception {
        String requestURI = servletWebRequest.getRequest().getRequestURI();
        
        /*
        - GET 请求不处理
        - 满足匹配条件不处理
         */
        if (servletWebRequest.getHttpMethod() == HttpMethod.GET
                || ignoreUrls.parallelStream().anyMatch((url) -> antPathMatcher.match(url, requestURI))) {
            return true;
        }
        
        String sign = Strings.trimToNull(servletWebRequest.getParameter(AntiReplayConstant.ANTI_REPLAY_PARAMETER_SIGN));
        Long timestamp = Long.valueOf(Optional.ofNullable(servletWebRequest.getParameter(AntiReplayConstant.ANTI_REPLAY_PARAMETER_TIMESTAMP)).orElse("0"));
        String nonce = Strings.trimToNull(servletWebRequest.getParameter(AntiReplayConstant.ANTI_REPLAY_PARAMETER_NONCE));
        
        // sign, timestamp, nonce 必填
        if (Objects.isNull(sign)
                || Objects.isNull(nonce)) {
            log.debug(String.format("必填: sign(%s), timestamp(%s), nonce(%s)", sign, timestamp, nonce));
            
            throw AntiReplayException.wrap("参数校验失败");
        }
        
        // 验证timestamp
        long currentTimeMillis = System.currentTimeMillis();
        long startExpiredTimeMillis = currentTimeMillis - AntiReplayConstant.ANTI_REPLAY_INTERVAL;
        long endExpiredTimeMillis = currentTimeMillis + AntiReplayConstant.ANTI_REPLAY_INTERVAL;
        if (timestamp > startExpiredTimeMillis
                && timestamp < endExpiredTimeMillis) {
            log.debug(String.format("时间戳为%d, 检查范围: (%d, %d)", timestamp, startExpiredTimeMillis, endExpiredTimeMillis));
            throw AntiReplayException.wrap("参数校验失败");
        }
        
        // 验证 nonce
        String nonceKey = String.format("%s.%s", AntiReplayConstant.ANTI_REPLAY_PARAMETER_NONCE, nonce);
        if (cacheService.contains(nonceKey)) {
            log.debug(String.format("nonce[%s] 请检查nonce参数", nonce));
            throw AntiReplayException.wrap("参数校验失败");
        }
        
        // 验证 sign
        Map<String, String[]> parameterMap = servletWebRequest.getParameterMap();
        String[] keys = parameterMap.keySet()
                .toArray(new String[]{});
        
        // 格式: k=v&k=v&k=v, md5(k=v&k=v&k=v)
        Optional<String> str = Arrays.stream(keys)
                .filter(AntiReplayConstant.ANTI_REPLAY_PARAMETER_SIGN::equals)
//                .sorted()
                .map(key -> String.format("%s=%s", key, Arrays.toString(parameterMap.get(key))))
                .reduce((k1, k2) -> String.format("%s&%s", k1, k2));
        String encode = MD5Encoder.encode(str.orElse("").getBytes());
        if (!sign.equals(encode)) {
            log.debug(String.format("请求[%s] != 服务端[%s] 签名错误, 请检查sign字段及加密策略", sign, encode));
            throw AntiReplayException.wrap("参数校验失败");
        }
        
        cacheService.set(nonceKey, currentTimeMillis, endExpiredTimeMillis, TimeUnit.MILLISECONDS);
        return true;
    }
    
}
