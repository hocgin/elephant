package in.hocg.manager.controller;

import in.hocg.SupperControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by hocgin on 2018/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class FileControllerTest extends SupperControllerTest {
    
    @Autowired
    FileController fileController;
    private MockMvc mockMvc;
    
    @Override
    public void before() {
        mockMvc = mockMvc(fileController);
    }
    
    @Test
    public void upload() {
        log.debug("upload");
    }
    
    @Test
    public void download() {
    }
    
    @Test
    public void image() {
    
    }
}