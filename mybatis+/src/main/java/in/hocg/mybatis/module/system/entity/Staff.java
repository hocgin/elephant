package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.DefaultModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [用户模块] 员工表
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("staff")
public class Staff extends DefaultModel<Staff> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 头像地址
     */
    @TableField("avatar_uri")
    private String avatarUri;
    /**
     * 性别(0:女, 1:男)
     */
    @TableField("gender")
    private Boolean gender;
    /**
     * 过期状态(0:为正常状态;1:为过期状态)
     */
    @TableField("expired")
    private Boolean expired;
    /**
     * 锁定状态(0:为正常状态;1:为锁定状态)
     */
    @TableField("locked")
    private Boolean locked;
    /**
     * 启用状态(0:为正常状态;1:为禁用状态)
     */
    @TableField("enabled")
    private Boolean enabled;
    /**
     * 注册时使用的IP
     */
    @TableField("sign_up_ip")
    private String signUpIp;
    /**
     * 最后登陆时使用的IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    
    
    public static final String NICK_NAME = "nick_name";
    
    public static final String USERNAME = "username";
    
    public static final String PASSWORD = "password";
    
    public static final String AVATAR_URI = "avatar_uri";
    
    public static final String GENDER = "gender";
    
    public static final String EXPIRED = "expired";
    
    public static final String LOCKED = "locked";
    
    public static final String ENABLED = "enabled";
    
    public static final String SIGN_UP_IP = "sign_up_ip";
    
    public static final String LAST_LOGIN_IP = "last_login_ip";
    
}
