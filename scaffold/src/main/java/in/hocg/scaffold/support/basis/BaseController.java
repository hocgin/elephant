package in.hocg.scaffold.support.basis;

/**
 * @author hocgin
 * @date 2018/6/12
 * email: hocgin@gmail.com
 */
public abstract class BaseController {
    /**
     * 重定向
     *
     * @param redirect
     * @return
     */
    public String redirect(String redirect) {
        return String.format("redirect:%s", redirect);
    }
}
