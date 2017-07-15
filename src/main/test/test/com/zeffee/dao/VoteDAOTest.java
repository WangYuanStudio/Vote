package test.com.zeffee.dao;

import com.zeffee.dao.VoteDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zeffee on 2017/7/15.
 */
public class VoteDAOTest extends DaoBaseTest {
    @Autowired
    private VoteDAO dao;

    @Test
    public void testGetSelfVoteRecordByTid() {
        int tid = 1;
        String uid = "zeffee";
        int expectCount = 2;
        int actualCount = dao.getSelfVoteRecordByTid(uid, tid);

        assertEquals("getSelfVoteRecordByTid went wrong! Not the same data!", expectCount, actualCount);
    }

    @Test
    public void testGetPerVoteAndOidListByTid() {
        int tid = 1;
        int votes_per_user = 2;
        String oid_list = "[1,2]";
        Map<String, Object> dataMap = dao.getPerVoteAndOidListByTid(tid);

        assertEquals("getPerVoteAndOidListByTid went wrong! Not the same data!", votes_per_user, (byte)dataMap.get("votes_per_user") & 0xFF);
        assertEquals("getPerVoteAndOidListByTid went wrong! Not the same data!", oid_list, dataMap.get("oid_list"));
    }
}
