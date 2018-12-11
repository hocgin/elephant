package in.hocg.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by hocgin on 2018/10/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DateKit {
    
    /**
     * LocalDateTime => Date
     *
     * @param localDateTime
     * @return
     */
    public static Date as(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(localDateTime.toInstant(zonedDateTime.getOffset()));
    }
}
