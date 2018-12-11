package in.hocg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hocgin on 2018/10/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@SpringBootApplication
public class ApiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    
    @RequestMapping("worked")
    public ResponseEntity<String> worked() {
        return ResponseEntity.ok(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
    }
    
}
