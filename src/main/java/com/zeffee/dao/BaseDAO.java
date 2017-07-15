package com.zeffee.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Zeffee on 2017/7/15.
 */
@Repository
public class BaseDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    public BaseDAO() {
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
