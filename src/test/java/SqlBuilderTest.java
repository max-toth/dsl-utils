import org.junit.Assert;
import org.junit.Test;
import ru.taskdata.sql.Field;
import ru.taskdata.sql.SQL;
import ru.taskdata.sql.Table;

import static ru.taskdata.sql.SQL.*;
import static ru.taskdata.sql.SQLConstants.*;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class SqlBuilderTest {

    @Test
    public void testForBuildSimpleSelect() {
        String sql = select(field("id"))
                .from(table("bo_table"))
                .build();

        Assert.assertEquals("SELECT id FROM bo_table", sql);
    }

    @Test
    public void testForBuildSimpleSelectWithConditions() {
        String sql = select(field("id"))
                .from(table("bo_table"))
                .where(field("some_fkey").eq("?"))
                .and(field("some_column").lt("?"))
                .build();

        Assert.assertEquals("SELECT id FROM bo_table WHERE some_fkey = ? AND some_column < ?", sql);
    }

    @Test
    public void testForBuildSimpleSelectWithFields() {
        Table table = table("bo_table").as("tbl");
        Table sc_table = table("bo_second_table").as("sc_tbl");

        Field some_fkey = field(table, "some_fkey");
        Field id = field(sc_table, "id");
        Field some_column = field(table, "some_column");

        String sql = select(field(table, "id"))
                .from(table)
                .innerJoin(sc_table, on(some_fkey, EQ, id))
                .where(some_fkey.eq("?"))
                .and(some_column.lt("?"))
                .and(id.neq("?"))
                .build();

        Assert.assertEquals("SELECT tbl.id FROM bo_table tbl INNER JOIN bo_second_table sc_tbl ON tbl.some_fkey = sc_tbl.id WHERE tbl.some_fkey = ? AND tbl.some_column < ? AND sc_tbl.id <> ?", sql);
    }
}
