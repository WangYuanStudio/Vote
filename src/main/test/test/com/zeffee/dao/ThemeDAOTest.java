package test.com.zeffee.dao;

import com.zeffee.dao.ThemeDAO;
import com.zeffee.entity.Theme;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zeffee on 2017/7/13.
 */
public class ThemeDAOTest extends DaoBaseTest {
    @Autowired
    private ThemeDAO dao;

    @Test
    public void testGetThemeByTid() throws IOException, DatabaseUnitException, SQLException {
        int tid = 1;

        Theme theme = dao.getThemeByTid(tid);
        assertEquals("theme data do not equals between xml and actual", "title123", theme.getTitle());
    }

    @Test
    public void testAddTheme() {
        String title = "test for now";
        Theme theme = new Theme(title, new Date(), new Date(), 5);
        int id = dao.addTheme(theme);
        assertTrue(id > 0);

        assertEquals("That is not the same data between added and actual", title, dao.getThemeByTid(id).getTitle());
    }


    @Test
    public void testDeleteTheme() {
        int tid = 1;
        dao.deleteTheme(tid);
        assertNull("An error occurred when deleting theme", dao.getThemeByTid(tid));
    }


    @Test
    public void testGetMyThemeList() throws DataSetException {
        String uid = "zeffee";
        QueryDataSet queryDataSet = new QueryDataSet(conn);
        queryDataSet.addTable("theme", "select * from theme where uid='" + uid + "'");
        ITable expected = queryDataSet.getTable("theme");

        List actual = dao.getMyThemeList(uid);

        assertEquals("Theme Data List is not the same between expected and actual", expected.getRowCount(), actual.size());
    }

}
