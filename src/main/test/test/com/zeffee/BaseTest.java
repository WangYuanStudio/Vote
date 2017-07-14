package test.com.zeffee;

import com.zeffee.config.DBConfig;
import com.zeffee.config.WebConfig;
import org.hamcrest.CoreMatchers;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Zeffee on 2017/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class, WebConfig.class})
@WebAppConfiguration
@Transactional
public class BaseTest {
    protected ResultMatcher isResponseContainSomeContent(String content){
        return content().string(CoreMatchers.containsString(content));
    }
}
