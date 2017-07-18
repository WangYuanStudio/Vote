package com.zeffee.controller;

import com.zeffee.dao.ThemeDAO;
import com.zeffee.dao.UserDAO;
import com.zeffee.lib.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Zeffee on 2017/7/16.
 */
@RestController
@Transactional
public class UserController {
    @Autowired
    private ThemeDAO themeDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/option/userList/{oid}", method = RequestMethod.GET)
    public Map<String, Object> getUserListByOid(@PathVariable(value = "oid") int oid) {
        if (themeDAO.isAnonymousThemeByOid(oid)) return Common.getResponseMap(500, "The theme is anonymous");

        List userList = userDAO.getUserNameListByOid(oid);

        return Common.getResponseMap(200, userList);
    }
}
