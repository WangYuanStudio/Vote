package com.zeffee.dao;

import com.zeffee.entity.Theme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zeffee on 2017/6/2.
 */
@Repository
public class ThemeDAO extends BaseDAO {

    public int addTheme(Theme theme) {
        Serializable id = getSession().save(theme);
        getSession().flush();
        return (int) id;
    }

    public List getOidListByTid(int tid) {
        return getSession().createSQLQuery("select oid from theme inner join options on theme.tid=options.tid where theme.tid=?")
                .setParameter(0, tid)
                .list();
    }

    public int deleteTheme(int tid) {
        return getSession().createQuery("delete from Theme where tid=?").setParameter(0, tid).executeUpdate();
    }


    public void updateTheme(Theme theme) {
        getSession().update(getSession().merge(theme));
    }


    public List getMyThemeList(String uid) {
        return getSession()
                .createSQLQuery("select tid,title,start_time,end_time from theme where tid in ( select tid from theme where uid=? union  select tid from votes where uid=?)")
                .setParameter(0, uid)
                .setParameter(1, uid)
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    public Theme getThemeDetailByTid(int tid) {
        return (Theme) getSession().get(Theme.class, tid);
    }

    public boolean isAnonymousThemeByOid(int oid) {
        return (boolean) getSession().createSQLQuery("select anonymous from options INNER JOIN theme on options.tid=theme.tid where options.oid=?")
                .setParameter(0, oid)
                .uniqueResult();
    }
}
