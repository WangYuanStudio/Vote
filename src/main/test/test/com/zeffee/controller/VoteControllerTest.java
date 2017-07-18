package test.com.zeffee.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Zeffee on 2017/7/15.
 */
public class VoteControllerTest extends ControllerBaseTest {
    @Test
    public void testVoteWithErrorOid() throws Exception {
        String requestJson = "{\"tid\":1,\"oid\":[3]}";
        mockVote(requestJson, ERROR_STATUS);
    }

    @Test
    public void testVoteWithErrorOid2() throws Exception {
        String requestJson = "{\"tid\":1,\"oid\":[1,2,3]}";
        mockVote(requestJson, ERROR_STATUS);
    }

    @Test
    public void testVoteWithErrorOid3() throws Exception {
        String requestJson = "{\"tid\":1,\"oid\":[\"2\"]}";
        mockVote(requestJson, ERROR_STATUS);
    }

    @Test
    public void testVoteWithCorrectOid() throws Exception {
        String requestJson = "{\"tid\":1,\"oid\":[1,2]}";
        mockVote(requestJson, RIGHT_STATUS);
    }

    private void mockVote(String requestJson, String expectContent) throws Exception {
        mockMvc.perform(post("/vote").contentType(MediaType.APPLICATION_JSON).content(requestJson).sessionAttr("openid", "zeffee2"))
                .andExpect(isResponseContainSomeContent(expectContent));
    }


    @Test
    public void testCheckVotesWithWrongUser() throws Exception {
        int tid = 1;

        mockMvc.perform(get("/checkVoted/" + tid).sessionAttrs(sessionAttrs))
                .andExpect(isResponseContainSomeContent(ERROR_STATUS));
    }

    @Test
    public void testCheckVotesWithCorrectUser() throws Exception {
        int tid = 1;

        mockMvc.perform(get("/checkVoted/" + tid).sessionAttr("openid", "zeffee2"))
                .andExpect(isResponseContainSomeContent(RIGHT_STATUS));
    }
}