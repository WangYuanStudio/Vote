package test.com.zeffee.controller;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.com.zeffee.dao.DaoBaseTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * Created by Zeffee on 2017/7/14.
 */
public class ThemeControllerTest extends DaoBaseTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Map<String, Object> sessionAttrs = new HashMap<String, Object>() {{
        put("openid", "zeffee");
    }};

    @Before
    @Override
    public void init() throws DatabaseUnitException, SQLException, IOException {
        super.init();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetThemeWithLogin() throws Exception {
        mockMvc.perform(get("/getTheme").sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent("\"status\":200"));
    }

    @Test
    public void testGetThemeWithoutLogin() throws Exception {
        mockMvc.perform(get("/getTheme"))
                .andExpect(isResponseContainSomeContent("\"status\":500"));
    }


    @Test
    public void testDeleteTheme() throws Exception {
        int tid = 1;

        mockMvc.perform(post("/deleteTheme/" + tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent("\"status\":200"));
    }

    @Test
    public void testDeleteThemeByInvalidTid() throws Exception {
        int invalid_tid = -99999;

        mockMvc.perform(post("/deleteTheme/" + invalid_tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent("\"status\":500"));
    }

    @Test
    public void testAddTheme() throws Exception {
        String requestJson = "{\"title\":\"aaa6\",\"start_time\":\"2016-10-01 15:45:30\",\"end_time\":\"2016-10-02 15:45:30\",\"votes_per_user\":3,\"options\":[{\"content\":\"aa1123\"},{\"content\":\"aa22334\"}]}";

        mockMvc.perform(post("/addTheme").contentType(MediaType.APPLICATION_JSON).content(requestJson).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent("\"status\":200"));
    }

}
