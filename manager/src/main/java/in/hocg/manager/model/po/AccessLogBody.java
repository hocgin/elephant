package in.hocg.manager.model.po;

import in.hocg.scaffold.support.aspect.log.Level;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/2/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class AccessLogBody implements BaseParameter {
    private LocalDateTime[] createdAt;
    private Level level;
    private String uri;
    private String visitor;
}
