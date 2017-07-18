package com.zeffee.dao;

import com.zeffee.entity.Options;
import com.zeffee.entity.Votes;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Zeffee on 2017/7/15.
 */
@Repository
public class VoteDAO extends BaseDAO {

    public Map<String, Object> getPerVoteAndOidListByTid(int tid) {
        return (Map<String, Object>) getSession().createSQLQuery("select votes_per_user,oid_list from theme where tid=?")
                .setParameter(0, tid)
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .uniqueResult();
    }

    public int getSelfVoteRecordByTid(String uid, int tid) {
        return Integer.parseInt(
                getSession().createSQLQuery("select count(*) from votes where uid=? AND tid=?")
                        .setParameter(0, uid)
                        .setParameter(1, tid)
                        .uniqueResult()
                        .toString()
        );
    }


    public void saveVoteRecord(Votes votes){
        getSession().save(votes);
    }

    public int incrementOptionCountByOid(int oid) {
        return getSession().createSQLQuery("update options set counts = counts + 1 where oid=?")
                .setParameter(0, oid)
                .executeUpdate();
    }

    public int incrementThemeCountByTid(int tid,int count){
        return getSession().createSQLQuery("update theme set counts = counts + ? where tid=?")
                .setParameter(0, count)
                .setParameter(1, tid)
                .executeUpdate();
    }

    public Options getOptionsByOid(int oid) {
        return (Options) getSession().get(Options.class, oid);
    }

}
