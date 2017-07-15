package com.zeffee.dao;

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
}
