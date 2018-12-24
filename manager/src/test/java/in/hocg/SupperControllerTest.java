package in.hocg;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by hocgin on 2018/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@Transactional
@Rollback
public abstract class SupperControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    
    @Before
    public abstract void before();
    
    @After
    public void after() {
    }
    
    public MockMvc mockMvc(BaseController controller) {
        return MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @SneakyThrows
    private MockHttpServletRequestBuilder getMockMvcRequestBodyBuilder(HttpMethod method, String url, String authorization, Object object, MultiValueMap<String, String> params) {
        MockHttpServletRequestBuilder requestBuilder;
        switch (method) {
            case GET:
                requestBuilder = MockMvcRequestBuilders.get(url);
                if (Objects.nonNull(params)) {
                    requestBuilder.params(params);
                }
                break;
            case POST:
                requestBuilder = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON);
                if (Objects.nonNull(object)) {
                    requestBuilder.content(objectMapper.writeValueAsString(object));
                }
                break;
            case PUT:
                requestBuilder = MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON);
                if (Objects.nonNull(object)) {
                    requestBuilder.content(objectMapper.writeValueAsString(object));
                }
                break;
            case PATCH:
                requestBuilder = MockMvcRequestBuilders.patch(url).contentType(MediaType.APPLICATION_JSON);
                if (Objects.nonNull(object)) {
                    requestBuilder.content(objectMapper.writeValueAsString(object));
                }
                break;
            case DELETE:
                requestBuilder = MockMvcRequestBuilders.delete(url);
                if (Objects.nonNull(params)) {
                    requestBuilder.params(params);
                }
                break;
            default:
                throw new Exception("Error: This request method is not supported.");
            
        }
        if (!StringUtils.isEmpty(authorization)) {
            requestBuilder.header("Authorization", String.format("Bearer %s", authorization));
        }
        return requestBuilder;
    }
    
    public MockHttpServletRequestBuilder get(String url, String authorization, MultiValueMap<String, String> params) {
        return getMockMvcRequestBodyBuilder(HttpMethod.GET, url, authorization, null, params);
    }
    
    public MockHttpServletRequestBuilder get(String url, String authorization) {
        return getMockMvcRequestBodyBuilder(HttpMethod.GET, url, authorization, null, null);
    }
    
    public MockHttpServletRequestBuilder delete(String url, String authorization, MultiValueMap<String, String> params) {
        return getMockMvcRequestBodyBuilder(HttpMethod.DELETE, url, authorization, null, params);
    }
    
    public MockHttpServletRequestBuilder delete(String url, String authorization) {
        return getMockMvcRequestBodyBuilder(HttpMethod.DELETE, url, authorization, null, null);
    }
    
    public MockHttpServletRequestBuilder post(String url, String authorization, Object object) {
        return getMockMvcRequestBodyBuilder(HttpMethod.POST, url, authorization, object, null);
    }
    
    public MockHttpServletRequestBuilder post(String url, String authorization) {
        return getMockMvcRequestBodyBuilder(HttpMethod.POST, url, authorization, null, null);
    }
    
    public MockHttpServletRequestBuilder put(String url, String authorization, Object object) {
        return getMockMvcRequestBodyBuilder(HttpMethod.PUT, url, authorization, object, null);
    }
    
    public MockHttpServletRequestBuilder put(String url, String authorization) {
        return getMockMvcRequestBodyBuilder(HttpMethod.PUT, url, authorization, null, null);
    }
    
    public MockHttpServletRequestBuilder patch(String url, String authorization, Object object) {
        return getMockMvcRequestBodyBuilder(HttpMethod.PATCH, url, authorization, object, null);
    }
    
    public MockHttpServletRequestBuilder patch(String url, String authorization) {
        return getMockMvcRequestBodyBuilder(HttpMethod.PATCH, url, authorization, null, null);
    }
    
    public ResultActions isOk(MockMvc mvc, MockHttpServletRequestBuilder builder) throws Exception {
        return getResultActions(mvc, builder).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    public ResultActions isNoContent(MockMvc mvc, MockHttpServletRequestBuilder builder) throws Exception {
        return getResultActions(mvc, builder).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    
    public ResultActions isCreated(MockMvc mvc, MockHttpServletRequestBuilder builder) throws Exception {
        return getResultActions(mvc, builder).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    private ResultActions getResultActions(MockMvc mvc, MockHttpServletRequestBuilder builder) throws Exception {
        return mvc.perform(builder).andDo(MockMvcResultHandlers.print());
    }
    
    public <T> T getResult(MockMvc mvc, MockHttpServletRequestBuilder builder,
                           TypeReference<Result<T>> reference) throws Exception {
        String responseString = getResponse(mvc, builder).getContentAsString();
        Result<T> responses = objectMapper.readValue(responseString, reference);
        return responses.getData();
    }
    
    public MockHttpServletResponse getResponse(MockMvc mvc, MockHttpServletRequestBuilder builder) throws Exception {
        return isOk(mvc, builder).andReturn().getResponse();
    }
    
    
}
