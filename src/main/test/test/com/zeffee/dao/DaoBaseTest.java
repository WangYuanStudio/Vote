package test.com.zeffee.dao;

import com.zeffee.config.DBConfig;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.datasource.DataSourceUtils;
import test.com.zeffee.BaseTest;

import java.io.*;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Zeffee on 2017/7/14.
 */
public class DaoBaseTest extends BaseTest {
    protected static IDatabaseConnection conn;

    @BeforeClass
    public static void setup() throws DatabaseUnitException, ClassNotFoundException, SQLException, IOException {
        conn = new DatabaseConnection(DataSourceUtils.getConnection(new DBConfig().dataSource()));

        // take a bak
        IDataSet ds = conn.createDataSet();
        FlatXmlDataSet.write(ds, new FileWriter(new File("bak.xml")));
    }

    @AfterClass
    public static void closeDatabase() throws SQLException, IOException, DatabaseUnitException {
        if (conn != null) {
            // rollback
            IDataSet ds = new FlatXmlDataSet(new InputStreamReader(new FileInputStream("bak.xml")));
            DatabaseOperation.CLEAN_INSERT.execute(conn, ds);

            conn.close();
            conn = null;
        }
    }


    @Before
    public void init() throws IOException, DatabaseUnitException, SQLException {
        IDataSet xmlDataSet = getXmlDataSet("theme.xml");
        DatabaseOperation.CLEAN_INSERT.execute(conn, xmlDataSet);
    }


    protected IDataSet getXmlDataSet(String name) throws IOException, DataSetException {
        InputStream inputStream = BaseTest.class.getResourceAsStream(name);
        assertNotNull("file " + name + " not found in classpath", inputStream);

        return new FlatXmlDataSetBuilder()
                .setColumnSensing(true)
                .build(inputStream);
    }
}
