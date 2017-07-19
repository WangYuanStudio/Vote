package test.com.zeffee.controller;

import com.zeffee.lib.Wechat;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by Zeffee on 2017/7/19.
 */
public class WechatControllerTest extends ControllerBaseTest {
    @Test
    public void testWechatVerify() throws Exception {
//        mockMvc.perform(get("/verify_{code}_vote.html"))
//                .andDo(MockMvcResultHandlers.print());
        //oreFpwQ5XiUhDdalzLbHVJDyorGs

        assertNull(Wechat.getWechatInfoByCode("001pdH8I1zb6D409ML9I1D7G8I1pdH8-"));

//        JSONObject wechatInfo = Wechat.getWechatInfoByCode("061bbKky04UGYi1aD7ky0fDDky0bbKkb");
//        assertNotNull(wechatInfo);
//        System.out.println(wechatInfo);
    }
}
