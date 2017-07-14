package test.com.zeffee.lib;

import com.zeffee.lib.HttpRequest;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * HttpRequest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 17, 2017</pre>
 */
public class HttpRequestTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: get(String url)
     */
    @Test
    public void testGet() throws Exception {
        String result = HttpRequest.get("http://wechat-test.wangyuan.info/api/oauth/vote/snsapi_userinfo");
        assertEquals("Http Get Method Is U", result, "{\"status\":200,\"data\":\"https:\\/\\/open.weixin.qq.com\\/connect\\/oauth2\\/authorize?appid=wx7c437b55e616143f&redirect_uri=http%3A%2F%2Fwechat-test.wangyuan.info%2Fapi%2Fcallback%2F&response_type=code&scope=snsapi_userinfo&state=vote#wechat_redirect\"}");
    }

    /**
     * Method: post(String url)
     */
    @Test
    public void testPost() throws Exception {
        String result = HttpRequest.post("http://localhost/test/test.php", "a=gggg");
        assertEquals("fuck", result, "{\"name\":\"gggg\"}");
    }


    /**
     * Method: sendHttpRequest(String url, HttpMethod httpMethod)
     */
    @Test
    public void testSendHttpRequest() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = HttpRequest.getClass().getMethod("sendHttpRequest", String.class, HttpMethod.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
