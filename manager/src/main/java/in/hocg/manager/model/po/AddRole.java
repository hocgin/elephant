package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by hocgin on 2019/1/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class AddRole implements BaseParameter {
    private String mark;
    private String name;
    private String description;
    private Collection<String> resources = Collections.emptyList();
}
