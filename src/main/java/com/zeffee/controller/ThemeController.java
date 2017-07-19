package com.zeffee.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zeffee.dao.ThemeDAO;
import com.zeffee.entity.Options;
import com.zeffee.entity.Theme;
import com.zeffee.exception.InvalidStatusException;
import com.zeffee.lib.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    private HttpSession session;

    @ApiOperation(value = "创建投票", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addTheme", method = RequestMethod.POST)
    public Map<String, Object> addTheme(@ApiParam(name = "theme", value = "test", required = true) @RequestBody @Valid Theme theme, BindingResult result) {
        if (result.hasErrors()) {
            return Common.getResponseMap(500, result.getFieldError().getDefaultMessage());
        }

        if (theme.getEnd_time().before(theme.getStart_time())) {
            return Common.getResponseMap(500, "EndTime needs after StartTime!");
        }


        theme.setUid(session.getAttribute("openid").toString());
        escapeHtmlContentToRemoveXSS(theme);
        setThemeOnOption(theme);
        themeDAO.addTheme(theme);

        updateThemeOidList(theme);

        return Common.getResponseMap(200, theme.getTid());
    }

    @ApiOperation(value = "删除投票")
    @RequestMapping(value = "/deleteTheme/{tid}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTheme(@ApiParam(value = "项目的id", defaultValue = "31") @PathVariable(value = "tid") int tid) {
        Theme theme = checkSelfThemeAndZeroCount_IfNotThrowException(tid, session);

        themeDAO.deleteTheme(theme);

        return Common.getResponseMap(200);
    }

    @ApiOperation(value = "修改投票", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateTheme", method = RequestMethod.PUT)
    public Map<String, Object> updateTheme(@RequestBody @Valid Theme theme, BindingResult result) {
        if (result.hasErrors()) {
            return Common.getResponseMap(500, result.getFieldError().getDefaultMessage());
        }

        checkSelfThemeAndZeroCount_IfNotThrowException(theme.getTid(), session);

        themeDAO.deleteOptionsByTid(theme.getTid());

        escapeHtmlContentToRemoveXSS(theme);
        setThemeOnOption(theme);
        themeDAO.updateTheme(theme);

        theme.setOptions(null);
        updateThemeOidList(theme);

        return Common.getResponseMap(200);
    }

    @ApiOperation(value = "获取我的所有投票记录")
    @RequestMapping(value = "/getMyThemeList", method = RequestMethod.GET)
    public Map<String, Object> getMyThemeList() {
        String uid = session.getAttribute("openid").toString();
        List votesList = themeDAO.getMyThemeList(uid);
        return Common.getResponseMap(200, votesList);
    }

    @ApiOperation(value = "搜索-获取我的所有投票记录")
    @RequestMapping(value = "/getMyThemeList.search", method = RequestMethod.GET)
    public Map<String, Object> getMyThemeList(@ApiParam(value = "搜索的内容", defaultValue = "有限公司") @RequestParam(value = "content") String searchContent) throws UnsupportedEncodingException {
        String uid = session.getAttribute("openid").toString();
        List votesList = themeDAO.getMyThemeList(uid, searchContent);
        return Common.getResponseMap(200, votesList);
    }

    @ApiOperation(value = "获取某个投票的详细信息（包括选项）")
    @RequestMapping(value = "/getThemeDetail/{tid}", method = RequestMethod.GET)
    public Map<String, Object> getThemeDetail(@ApiParam(value = "项目的id", defaultValue = "30") @PathVariable(value = "tid") int tid) {
        Theme theme = themeDAO.getThemeDetailByTid(tid);

        return Common.getResponseMap(200, theme);
    }


//    @RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
//    public Map<String, Object> verify(HttpSession session) {
//        return Common.getResponseMap(200, session.getAttribute("openid"));
//    }


    private Theme checkSelfThemeAndZeroCount_IfNotThrowException(int tid, HttpSession session) {
        Theme oldTheme = themeDAO.getThemeDetailByTid(tid);

        if (oldTheme == null) throw new InvalidStatusException("Invaild Tid!");

        if (!oldTheme.getUid().equals(session.getAttribute("openid"))
                || oldTheme.getCounts() > 0) {
            throw new InvalidStatusException("Invaild Operation!");
        }

        return oldTheme;
    }


    private void setThemeOnOption(Theme theme) {
        for (Options options : theme.getOptions()) {
            options.setTheme(theme);
        }
    }

    private void updateThemeOidList(Theme theme) {
        List oidList = themeDAO.getOidListByTid(theme.getTid());
        theme.setOid_list(Common.toJsonFromList(oidList));

        themeDAO.updateTheme(theme);
    }

    private void escapeHtmlContentToRemoveXSS(Theme theme) {
        theme.setTitle(HtmlUtils.htmlEscape(theme.getTitle()));
        theme.setDescription(HtmlUtils.htmlEscape(theme.getDescription()));
        for (Options options : theme.getOptions()) {
            options.setContent(HtmlUtils.htmlEscape(options.getContent()));
        }
    }
}
