package in.hocg.sample;

import org.springframework.web.util.HtmlUtils;

/**
 * Created by hocgin on 2018/12/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class HtmlTest {
    public static void main(String[] args) {
        System.out.println(HtmlUtils.htmlEscape("<div>666</div>"));
    }
}
