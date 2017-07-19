package test.com.zeffee.dao;

import com.zeffee.dao.UserDAO;
import com.zeffee.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zeffee on 2017/7/16.
 */
public class UserDAOTest extends DaoBaseTest {
    @Autowired
    private UserDAO dao;

    @Test
    public void testGetUserNameListByOid() {
        int oid = 1;
        List nameList = dao.getUserNameListByOid(oid);
        List expectList = new ArrayList() {{
            add("zeffee chan");
        }};

        assertEquals("getUserNameListByOid went wrong! it is not same data!", expectList, nameList);
    }

    @Test
    public void testUpdateUserInfoWithNewUser() {
        testUpdateUserByOpenidAndExpectCount("handsome zeffee", 2);
    }

    @Test
    public void testUpdateUserInfoWithOldUser() {
        testUpdateUserByOpenidAndExpectCount("zeffee", 1);
    }

    private void testUpdateUserByOpenidAndExpectCount(String openid, int expectCount) {
        String name = "shitter";
        String photo = "http://wx.qlogo.cn/mmhead/Mjzdia7evAzwzxichrTjAEwK7BLII2YIfMRiaMudFpyWM9yoNFOaH9KTA/0";
        User user = new User(openid, name, photo);
        dao.updateUserInfo(user);

        int actualCount = Integer.parseInt(dao.getSession().createSQLQuery("select count(*) from user").uniqueResult().toString());

        assertEquals("update user info fail ! ", expectCount, actualCount);
    }
}
