package test.com.zeffee.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by Zeffee on 2017/7/14.
 */
public class ThemeControllerTest extends ControllerBaseTest {

    @Test
    public void testGetMyThemeListWithLogin() throws Exception {
        mockMvc.perform(get("/getMyThemeList").sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }

    @Test
    public void testGetMyThemeListWithoutLogin() throws Exception {
        mockMvc.perform(get("/getMyThemeList"))
                .andExpect(isResponseContainSomeContent(ERROR_STATUS));
    }

    @Test
    public void testGetThemeDetail() throws Exception {
        int tid = 1;
        String thefirstOptionContent = "shitter options 1";

        mockMvc.perform(get("/getThemeDetail/" + tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(thefirstOptionContent));
    }


    @Test
    public void testDeleteTheme() throws Exception {
        int tid = 1;

        mockMvc.perform(delete("/deleteTheme/" + tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }

    @Test
    public void testDeleteThemeByInvalidTid() throws Exception {
        int invalid_tid = -99999;

        mockMvc.perform(delete("/deleteTheme/" + invalid_tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(ERROR_STATUS));
    }

    @Test
    public void testUpdateTheme() throws Exception {

        String requestJson = "{\"tid\":1,\"title\":\"aaa666666\",\"description\":\"this is description, sizt of that needs between 0 and 100!\",\"start_time\":\"2016-10-01 15:45:30\",\"end_time\":\"2016-10-02 15:45:30\",\"votes_per_user\":3,\"anonymous\":0,\"options\":[{\"content\":\"aa1123\"},{\"content\":\"aa22334\"}]}";

        mockMvc.perform(put("/updateTheme").contentType(MediaType.APPLICATION_JSON).content(requestJson).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }

    @Test
    public void testAddTheme() throws Exception {
        String requestJson = "{\"title\":\"aaa6\",\"description\":\"this is description, sizt of that needs between 0 and 100!\",\"start_time\":\"2016-10-01 15:45:30\",\"end_time\":\"2016-10-02 15:45:30\",\"votes_per_user\":3,\"anonymous\":0,\"options\":[{\"content\":\"aa1123\"},{\"content\":\"aa22334\"}]}";

        mockMvc.perform(post("/addTheme").contentType(MediaType.APPLICATION_JSON).content(requestJson).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }

}
