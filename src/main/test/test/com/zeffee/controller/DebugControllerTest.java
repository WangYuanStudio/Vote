package test.com.zeffee.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Zeffee on 2017/7/18.
 */
public class DebugControllerTest extends ControllerBaseTest {
    @Test
    public void testRollBack() throws Exception {
        mockMvc.perform(get("/rollback"))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }
}
