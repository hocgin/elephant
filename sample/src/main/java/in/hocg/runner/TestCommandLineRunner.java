package in.hocg.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Slf4j
@Component
public class TestCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("@@TestCommandLineRunner {}", Arrays.toString(args));
    }
}
