package com.zeffee.controller;

import com.zeffee.config.DBConfig;
import com.zeffee.lib.Common;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Zeffee on 2017/7/18.
 */
@RestController
@Transactional
public class DebugController {
    @RequestMapping(value = "/rollback", method = RequestMethod.GET)
    public Map<String, Object> rollbackDb() throws DatabaseUnitException, IOException, SQLException, URISyntaxException {
        IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(new DBConfig().dataSource()));

        IDataSet ds = new FlatXmlDataSet(new InputStreamReader(DebugController.class.getClassLoader().getResourceAsStream("bak_old.xml")));
        DatabaseOperation.CLEAN_INSERT.execute(conn, ds);

        conn.close();

        return Common.getResponseMap(200);
    }

}
