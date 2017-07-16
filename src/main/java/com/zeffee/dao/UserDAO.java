package com.zeffee.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeffee on 2017/7/16.
 */
@Repository
public class UserDAO extends BaseDAO {
    public List getUserNameListByOid(int oid) {
        return getSession().createSQLQuery("select name from votes INNER join user on user.uid=votes.uid where oid=?")
                .setParameter(0, oid)
                .list();
    }
}
