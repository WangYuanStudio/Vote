package test.com.zeffee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.zeffee.dao.ThemeDAOTest;
import test.com.zeffee.dao.UserDAOTest;
import test.com.zeffee.dao.VoteDAOTest;

/**
 * Created by Zeffee on 2017/7/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ThemeDAOTest.class, VoteDAOTest.class, UserDAOTest.class})
public class AllDaoTest {
}
