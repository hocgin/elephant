package in.hocg;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import in.hocg.scaffold.annotation.DevAndTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Configuration
@MapperScan("in.hocg.*.mapper*")
public class MyBatisPlusConfiguration {
    
    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**
     * SQL执行效率插件
     * 设置 dev test 环境开启
     *
     * @return
     */
    @Bean
    @DevAndTest
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
