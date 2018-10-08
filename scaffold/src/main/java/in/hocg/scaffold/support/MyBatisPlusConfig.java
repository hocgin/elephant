package in.hocg.scaffold.support;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import in.hocg.scaffold.annotation.Dev;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Configuration
@MapperScan("in.hocg.*.mapper*")
public class MyBatisPlusConfig {
    
    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**
     * 设置 dev test 环境开启
     * @return
     */
    @Bean
    @Dev
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
