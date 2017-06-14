package com.zeffee.dao;

import com.zeffee.entity.Theme;
import com.zeffee.entity.Votes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zeffee on 2017/6/2.
 */
@Repository
public class ThemeDAO {
    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addTheme(Theme theme) {
        getSession().save(theme);
    }

    public int deleteTheme(int tid) {
        return getSession().createQuery("delete from Theme where tid=?").setParameter(0, tid).executeUpdate();
    }

    public List<Theme> getMyThemeList(String uid) {
        return (List<Theme>) getSession().createQuery("select tid,title,start_time,end_time from Theme where uid=?").setParameter(0,uid).list();
    }


}
