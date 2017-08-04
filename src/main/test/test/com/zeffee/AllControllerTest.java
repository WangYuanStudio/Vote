package test.com.zeffee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.zeffee.controller.DebugControllerTest;
import test.com.zeffee.controller.ThemeControllerTest;
import test.com.zeffee.controller.UserControllerTest;
import test.com.zeffee.controller.VoteControllerTest;

/**
 * Created by Zeffee on 2017/7/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ThemeControllerTest.class, VoteControllerTest.class, UserControllerTest.class, DebugControllerTest.class})
public class AllControllerTest {
}