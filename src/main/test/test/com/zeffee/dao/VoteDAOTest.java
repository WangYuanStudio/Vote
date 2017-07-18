package test.com.zeffee.dao;

import com.zeffee.dao.ThemeDAO;
import com.zeffee.dao.VoteDAO;
import com.zeffee.entity.Votes;
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

    @Autowired
    private ThemeDAO themeDAO;

    private final static int VOTE_SIZE = 2;

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

        assertEquals("getPerVoteAndOidListByTid went wrong! Not the same data!", votes_per_user, (byte) dataMap.get("votes_per_user") & 0xFF);
        assertEquals("getPerVoteAndOidListByTid went wrong! Not the same data!", oid_list, dataMap.get("oid_list"));
    }

    @Test
    public void testIncrementOptionCountByOid() {
        int oid = 1;
        int expectInfluenceLine = 1;
        int actualInfluenceLine = dao.incrementOptionCountByOid(oid);

        int expectedCount = 4;
        int actualCount = dao.getOptionsByOid(oid).getCounts();

        assertEquals("influence line is not correct!", expectInfluenceLine, actualInfluenceLine);
        assertEquals("incrementOptionCountByOid went wrong! Not the same data!", expectedCount, actualCount);
    }


    @Test
    public void testIncrementThemeCountByTid() {
        int tid = 1;
        int count = 8;
        int expectCount = 2 * count;
        dao.incrementThemeCountByTid(tid, count);
        dao.incrementThemeCountByTid(tid, count);

        int actualCount = themeDAO.getThemeDetailByTid(tid).getCounts();

        assertEquals("incrementThemeCountByTid went wrong! can't increment count!", expectCount, actualCount);
    }

    @Test
    public void testSaveVoteRecord() {
        int tid = 1;
        int oid = 1;
        int oid2 = 2;
        String uid = "zeffee";

        Votes votes1 = new Votes(uid, oid, tid);
        Votes votes2 = new Votes(uid, oid2, tid);
        dao.saveVoteRecord(votes1);
        dao.saveVoteRecord(votes2);

        int expectCount = VOTE_SIZE + 2;
        int actualCount = dao.getSelfVoteRecordByTid(uid, tid);

        assertEquals("saveVoteRecord went wrong! can't save the vote record!", expectCount, actualCount);
    }


}
