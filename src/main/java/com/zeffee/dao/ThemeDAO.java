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

    public void deleteTheme(Theme theme) {
        getSession().delete(theme);
        getSession().flush();
    }

    public void updateTheme(Theme theme) {
        getSession().update(getSession().merge(theme));
        getSession().flush();
    }


    public List getMyThemeList(String uid) {
        return getSession()
                .createSQLQuery("select tid,title,start_time,end_time,photo from theme left join user on theme.uid=user.uid where tid in ( select tid from theme where uid=?) union select tid,title,start_time,end_time,photo from theme left join user on theme.uid=user.uid where tid in ( select tid from votes where uid=?)")
                .setParameter(0, uid)
                .setParameter(1, uid)
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    public List getMyThemeList(String uid, String searchContent) {
        return getSession()
                .createSQLQuery("select tid,title,start_time,end_time,photo from theme left join user on theme.uid=user.uid where tid in ( select tid from theme where uid=? union  select tid from votes where uid=?) and match(title, description) against(? IN NATURAL LANGUAGE MODE) ")
                .setParameter(0, uid)
                .setParameter(1, uid)
                .setParameter(2, searchContent)
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
        //select tid,title,start_time,end_time from theme where tid in ( select tid from theme where uid='zeffee' union  select tid from votes where uid='zeffee') and match(title, description) against('松下电子' IN NATURAL LANGUAGE MODE)
        //insert into theme(title) values('芜湖美的厨卫电气制造有限公司'),('北京凡客尚品电子商务有限公司'),('凡客诚品（北京）科技有限公司'),('瞬联讯通科技（北京）有限公司'),('北京畅捷通讯 有限公司'),('北京畅捷通支付技术有限公司'),('畅捷通信息技术股份有限公司'),('北京畅捷科技有限公司'),('中国航天工业科学技术咨询有限公司'),('北京·松下彩色显象管有限公司'),('北京·松下电子部品有限公司'),('北京松下照明光源有限公司'),('松下电气机气（北京）有限公司'),('中新航天科技有限公司'),(' 北京奔驰汽车有限公司'),('阿莫斯特环保科技（北京）有限公司'),('北京低碳清洁能源研究所'),('北京未来科技城开发建设有限 公司'),('北京诺华制药有限公司'),('北京信元电信维护有限责任公司');
        //select * from theme where match(title,description) against('松下电子' IN NATURAL LANGUAGE MODE);
    }


    public Theme getThemeDetailByTid(int tid) {
        return (Theme) getSession().get(Theme.class, tid);
    }

    public boolean isAnonymousThemeByOid(int oid) {
        Object result = getSession().createSQLQuery("select anonymous from options INNER JOIN theme on options.tid=theme.tid where options.oid=?")
                .setParameter(0, oid)
                .uniqueResult();
        if (result == null || (boolean) result == false) return false;
        return true;
    }


    public void deleteOptionsByTid(int tid) {
        getSession().createSQLQuery("delete from options where tid=?")
                .setParameter(0, tid)
                .executeUpdate();
    }

    public int getOptionsCountByTid(int tid) {
        return Integer.parseInt(
                getSession().createSQLQuery("select count(*) from options where tid=?")
                        .setParameter(0, tid)
                        .uniqueResult()
                        .toString()
        );
    }
}
