package test.com.zeffee.controller;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.com.zeffee.dao.DaoBaseTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeffee on 2017/7/15.
 */
public class ControllerBaseTest extends DaoBaseTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    protected final static String ERROR_STATUS = "\"status\":500";
    protected final static String RIGHT_STATUS = "\"status\":200";

    protected Map<String, Object> sessionAttrs = new HashMap<String, Object>() {{
        put("openid", "zeffee");
    }};

    @Before
    @Override
    public void init() throws DatabaseUnitException, SQLException, IOException {
        super.init();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
