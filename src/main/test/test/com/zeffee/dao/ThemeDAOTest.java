package test.com.zeffee.dao;

import com.zeffee.dao.ThemeDAO;
import com.zeffee.entity.Options;
import com.zeffee.entity.Theme;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Zeffee on 2017/7/13.
 */
public class ThemeDAOTest extends DaoBaseTest {
    @Autowired
    private ThemeDAO dao;

    private final static int FIRST_THEME_OPTIONS_SIZE = 2;

    @Test
    public void testGetMyThemeList() throws DataSetException {
        String uid = "zeffee";
        QueryDataSet queryDataSet = new QueryDataSet(conn);
        queryDataSet.addTable("theme", "select tid,title,start_time,end_time from theme where tid in ( select tid from theme where uid='" + uid + "' union  select tid from votes where uid='" + uid + "')");
        ITable expected = queryDataSet.getTable("theme");

        List actual = dao.getMyThemeList(uid);

        assertEquals("Theme Data List is not the same between expected and actual", expected.getRowCount(), actual.size());
    }

    @Test
    public void testGetMyThemeListBySearchContent() throws DataSetException {
        String searchContent = "有限公司";
        String uid = "zeffee";
        int expectResultCount = 1;
        int actualCount = dao.getMyThemeList(uid, searchContent).size();

        assertEquals("Search Theme Data List is not the same between expected and actual", expectResultCount, actualCount);
    }

    @Test
    public void testGetThemeByTid() throws IOException, DatabaseUnitException, SQLException {
        int tid = 1;
        String firstThemeTitle = "title123";

        Theme theme = dao.getThemeDetailByTid(tid);
        assertEquals("theme data do not equals between xml and actual -- on the get theme by tid", firstThemeTitle, theme.getTitle());
        assertEquals("theme data do not equals between xml and actual -- on the get theme by tid", FIRST_THEME_OPTIONS_SIZE, theme.getOptions().size());
    }

    @Test
    public void testGetOidListByTid() {
        int tid = 1;

        List expectList = new ArrayList() {{
            add(1);
            add(2);
        }};

        List actualList = dao.getOidListByTid(tid);

        assertEquals("getOidListByTid is wrong!", expectList, actualList);
    }

    @Test
    public void testAddTheme() {
        String title = "这是中文";
        Theme theme = new Theme(title, new Date(), new Date(), 5);

        Options newOption = new Options(title + "test for options");
        newOption.setTheme(theme);
        Set<Options> set = new HashSet<Options>() {{
            add(newOption);
        }};
        theme.setOptions(set);
        int exceptOptionCount = 1;

        int id = dao.addTheme(theme);
        assertTrue(id > 0);
        assertEquals("That is not the same data between added and actual -- on the add theme", title, dao.getThemeDetailByTid(id).getTitle());
        assertEquals("That is not the same data between added and actual -- on the add theme", exceptOptionCount, dao.getOptionsCountByTid(id));

    }


    @Test
    public void testDeleteTheme() {
        int tid = 1;
        int expectCount = 0;

        dao.deleteTheme(dao.getThemeDetailByTid(tid));
        assertNull("An error occurred when deleting theme", dao.getThemeDetailByTid(tid));
        assertEquals("An error occurred when deleting theme", expectCount, dao.getOptionsCountByTid(tid));
    }

    @Test
    public void testUpdateTheme() {
        int tid = 1;
        String title = "oh" + new Date();
        String newOptionContent = "shit" + new Date();
        int newOptionsSize = FIRST_THEME_OPTIONS_SIZE + 1;

        Theme theme = dao.getThemeDetailByTid(tid);
        theme.setTitle(title);

        Set<Options> options = theme.getOptions();
        Options newOption = new Options(newOptionContent);
        newOption.setTheme(theme);
        options.add(newOption);

        dao.updateTheme(theme);

        Theme newTheme = dao.getThemeDetailByTid(tid);
        assertEquals("An error occurred when updating theme", title, newTheme.getTitle());
        assertEquals("An error occurred when updating theme", newOptionsSize, newTheme.getOptions().size());
    }


    @Test
    public void testGetAnonymousByOid() {
        int oid = 1;
        int expectAnonymous = 0;
        int actualAnonymous = dao.isAnonymousThemeByOid(oid) ? 1 : 0;

        assertEquals("getAnonymousByOid went wrong!", expectAnonymous, actualAnonymous);
    }

    @Test
    public void testDeleteOptionsByTid() {
        int tid = 1;
        int expectedCount = 0;
        dao.deleteOptionsByTid(tid);
        int actualCount = dao.getOptionsCountByTid(tid);
        assertEquals("deleteOptionsByTid went wrong!", expectedCount, actualCount);
    }
}
