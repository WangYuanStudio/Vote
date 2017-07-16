package test.com.zeffee.dao;

import com.zeffee.dao.UserDAO;
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
}
