package in.hocg.mybatis.module.basic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.mybatis.basic.model.DefaultModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * [基础模块] 访问日志
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessLog extends DefaultModel<AccessLog> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 访问者账号ID
     */
    @TableField(VISITOR)
    private String visitor;
    /**
     * 响应结果(JSON)
     */
    @TableField(RESPONSE)
    private String response;
    /**
     * 日志级别
     */
    @TableField(LEVEL)
    private String level;
    /**
     * 请求参数(JSON)
     */
    @TableField(PARAMETERS)
    private String parameters;
    /**
     * 日志信息
     */
    @TableField(MESSAGE)
    private String message;
    /**
     * 发起请求的方式
     */
    @TableField(METHOD)
    private String method;
    /**
     * Class#Method
     */
    @TableField(MAPPING)
    private String mapping;
    /**
     * 请求路径
     */
    @TableField(URI)
    private String uri;
    /**
     * 耗时 单位:ms
     */
    @TableField(USAGE_TIME)
    private long usageTime;
    /**
     * 访问者IP地址
     */
    @TableField(IP)
    private String ip;
    
    
    public static final String VISITOR = "visitor";
    
    public static final String RESPONSE = "response";
    
    public static final String PARAMETERS = "parameters";
    
    public static final String MESSAGE = "message";
    
    public static final String METHOD = "method";
    
    public static final String URI = "uri";
    
    public static final String USAGE_TIME = "usage_time";
    
    public static final String IP = "ip";
    
    public static final String MAPPING = "mapping";
    
    private static final String LEVEL = "level";
    
}
