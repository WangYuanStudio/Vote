package com.zeffee.controller;

import com.zeffee.dao.ThemeDAO;
import com.zeffee.entity.Theme;
import com.zeffee.exception.InvalidStatusException;
import com.zeffee.lib.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by zeffee on 2017/6/2.
 */
@RestController
@Transactional
public class ThemeController {
    @Autowired
    private ThemeDAO themeDAO;

    @RequestMapping(value = "/addTheme", method = RequestMethod.POST)
    public Map<String, Object> addTheme(@RequestBody @Valid Theme theme, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return Common.getResponseMap(500, result.getFieldError().getDefaultMessage());
        }

        if (theme.getEnd_time().before(theme.getStart_time())) {
            return Common.getResponseMap(500, "EndTime needs after StartTime!");
        }

        theme.setUid(session.getAttribute("openid").toString());
        themeDAO.addTheme(theme);

        List oidList = themeDAO.getOidListByTid(theme.getTid());
        theme.setOid_list(Common.toJsonFromList(oidList));
        themeDAO.updateTheme(theme);

        return Common.getResponseMap(200, theme.getTid());
    }

    @RequestMapping(value = "/deleteTheme/{tid}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTheme(@PathVariable(value = "tid") int tid, HttpSession session) {
        //TODO:: 事务处理
        checkSelfThemeAndZeroCount_IfNotThrowException(tid, session);

        int status = themeDAO.deleteTheme(tid) == 1 ? 200 : 500;

        return Common.getResponseMap(status);
    }

    @RequestMapping(value = "/updateTheme", method = RequestMethod.PUT)
    public Map<String, Object> updateTheme(@RequestBody @Valid Theme theme, BindingResult result, HttpSession session) {
        //TODO:: 事务处理
        if (result.hasErrors()) {
            return Common.getResponseMap(500, result.getFieldError().getDefaultMessage());
        }

        checkSelfThemeAndZeroCount_IfNotThrowException(theme.getTid(), session);

        themeDAO.updateTheme(theme);

        return Common.getResponseMap(200);
    }

    @RequestMapping(value = "/getMyThemeList", method = RequestMethod.GET)
    public Map<String, Object> getMyThemeList(HttpSession session) {
        String uid = session.getAttribute("openid").toString();
        List votesList = themeDAO.getMyThemeList(uid);
        return Common.getResponseMap(200, votesList);
    }

    @RequestMapping(value = "/getThemeDetail/{tid}", method = RequestMethod.GET)
    public Map<String, Object> getThemeDetail(@PathVariable(value = "tid") int tid) {
        Theme theme = themeDAO.getThemeDetailByTid(tid);

        return Common.getResponseMap(200, theme);
    }


//    @RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
//    public Map<String, Object> verify(HttpSession session) {
//        return Common.getResponseMap(200, session.getAttribute("openid"));
//    }


    private void checkSelfThemeAndZeroCount_IfNotThrowException(int tid, HttpSession session) {
        Theme oldTheme = themeDAO.getThemeDetailByTid(tid);

        if (oldTheme == null) throw new InvalidStatusException("Invaild Tid!");

        if (!oldTheme.getUid().equals(session.getAttribute("openid"))
                || oldTheme.getCounts() > 0) {
            throw new InvalidStatusException("Invaild Operation!");
        }
    }
}
