package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.DefaultModel;
import in.hocg.mybatis.enums.Gender;
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
    @TableField(NICK_NAME)
    private String nickName;
    /**
     * 用户名
     */
    @TableField(USERNAME)
    private String username;
    /**
     * 密码
     */
    @TableField(PASSWORD)
    private String password;
    /**
     * 头像地址
     */
    @TableField(AVATAR_URI)
    private String avatarUri;
    /**
     * 性别(0:女, 1:男)
     */
    @TableField(GENDER)
    private Gender gender;
    /**
     * 过期状态(0:为过期状态;1:为正常状态)
     */
    @TableField(NON_EXPIRED)
    private boolean nonExpired;
    /**
     * 锁定状态(0:为过期状态;1:为正常状态)
     */
    @TableField(NON_LOCKED)
    private boolean nonLocked;
    /**
     * 启用状态(0:为禁用状态;1:为正常状态)
     */
    @TableField(ENABLED)
    private boolean enabled;
    /**
     * 注册时使用的IP
     */
    @TableField(SIGN_UP_IP)
    private String signUpIp;
    /**
     * 最后登陆时使用的IP
     */
    @TableField(LAST_LOGIN_IP)
    private String lastLoginIp;
    
    
    public static final String NICK_NAME = "nick_name";
    
    public static final String USERNAME = "username";
    
    public static final String PASSWORD = "password";
    
    public static final String AVATAR_URI = "avatar_uri";
    
    public static final String GENDER = "gender";
    
    public static final String NON_EXPIRED = "non_expired";
    
    public static final String NON_LOCKED = "non_locked";
    
    public static final String ENABLED = "enabled";
    
    public static final String SIGN_UP_IP = "sign_up_ip";
    
    public static final String LAST_LOGIN_IP = "last_login_ip";
    
}
