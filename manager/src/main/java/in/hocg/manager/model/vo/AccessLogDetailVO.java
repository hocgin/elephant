package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/2/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AccessLogDetailVO extends AccessLog
        implements BaseParameter {
}
