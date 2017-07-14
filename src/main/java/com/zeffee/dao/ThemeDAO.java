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
public class ThemeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public ThemeDAO(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public int addTheme(Theme theme) {
        Serializable id = getSession().save(theme);
        getSession().flush();
        return (int) id;
    }

    public int deleteTheme(int tid) {
        return getSession().createQuery("delete from Theme where tid=?").setParameter(0, tid).executeUpdate();
    }

    public List getMyThemeList(String uid) {
        return getSession()
                .createSQLQuery("select tid,title,start_time,end_time from Theme where uid=?")
                .setParameter(0, uid)
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    public Theme getThemeByTid(int tid) {
        return (Theme) getSession().get(Theme.class, tid);
    }
}
