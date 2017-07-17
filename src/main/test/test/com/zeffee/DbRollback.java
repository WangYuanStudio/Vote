package test.com.zeffee;

import com.zeffee.config.DBConfig;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Created by Zeffee on 2017/7/17.
 */
public class DbRollback {
    public static void main(String[] args) throws DatabaseUnitException, SQLException, IOException {
        IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(new DBConfig().dataSource()));
        IDataSet ds = new FlatXmlDataSet(new InputStreamReader(new FileInputStream("bak_old.xml")));
        DatabaseOperation.CLEAN_INSERT.execute(conn, ds);

        conn.close();
    }
}
