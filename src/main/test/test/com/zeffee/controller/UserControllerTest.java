package test.com.zeffee.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Zeffee on 2017/7/16.
 */
public class UserControllerTest extends ControllerBaseTest {
    @Test
    public void testGetUserListByOid() throws Exception {
        String expect = "[\"zeffee chan\"]";

        mockMvc.perform(get("/option/userList/1").sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS))
                .andExpect(isResponseContainSomeContent(expect));
    }
}
