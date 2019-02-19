package in.hocg.manager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.hocg.manager.service.AccessLogService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.support.aspect.log.Level;
import in.hocg.scaffold.support.aspect.log.LogRepository;
import in.hocg.scaffold.util.RequestKit;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author hocgin
 * @date 2018/12/23
 * email: hocgin@gmail.com
 */
@Service
@AllArgsConstructor
public class LogRepositoryImpl implements LogRepository {
    private final ObjectMapper objectMapper;
    private final StaffService staffService;
    private final AccessLogService accessLogService;
    
    @Override
    public void handle(Level level,
                       String mapping,
                       String message,
                       String source,
                       String operating,
                       long timeMillis,
                       Object result) {
        log(level, mapping, message, source, operating, timeMillis, result);
    }
    
    @SneakyThrows
    private void log(Level level,
                     String mapping,
                     String message,
                     String source,
                     String operating,
                     long timeMillis,
                     Object result) {
        HttpServletRequest request = RequestKit.get();
        String ip = RequestKit.getClientIP(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((String) authentication.getPrincipal());
        Optional<Staff> staff = staffService.findByUsername(username);
        AccessLog log = AccessLog.builder()
                .ip(ip)
                .level(level.name())
                .uri(request.getRequestURI())
                .usageTime(timeMillis)
                .method(request.getMethod())
                .parameters(objectMapper.writeValueAsString(request.getParameterMap()))
                .visitor(staff.map(Staff::getId).orElse("Unknown"))
                .mapping(mapping)
                .response(objectMapper.writeValueAsString(result))
                .message(message)
                .build();
        accessLogService.save(log);
    }
}
