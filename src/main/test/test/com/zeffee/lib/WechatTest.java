package test.com.zeffee.lib;

import com.zeffee.lib.Wechat;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Wechat Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class WechatTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTokenByCode()
     */
    @Test
    public void testGetTokenByCode_ByErrorCodeToTestServerStatus() throws Exception {
        String token = Wechat.getOpenidByCode("051SePe401OqjF1057f40CYRe40SePe4");

        //assertThat(token, containsString("openid")); 是否包含openid,有openid说明服务器正常

        assertEquals("get token by code server down?", "code error", token);
    }

} 
