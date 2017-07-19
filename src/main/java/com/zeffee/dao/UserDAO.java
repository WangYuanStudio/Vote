package com.zeffee.dao;

import com.zeffee.entity.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
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

    public void updateUserInfo(User user) {
        getSession().saveOrUpdate(user);
        getSession().flush();
    }
}
