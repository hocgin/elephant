package in.hocg.mybatis.basic.injector.node.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 *```sql
 * LOCK TABLE nested_category WRITE;
 *
 * SELECT @myLeft := lft, @myRight := rgt, @myWidth := rgt - lft + 1
 * FROM nested_category
 * WHERE name = 'PORTABLE ELECTRONICS';
 *
 * DELETE FROM nested_category WHERE lft = @myLeft;
 *
 * UPDATE nested_category SET rgt = rgt - 1, lft = lft - 1 WHERE lft BETWEEN @myLeft AND @myRight;
 * UPDATE nested_category SET rgt = rgt - 2 WHERE rgt > @myRight;
 * UPDATE nested_category SET lft = lft - 2 WHERE lft > @myRight;
 *
 * UNLOCK TABLES;
 *```
 * 删除父节点，且子节点会升为父节点级别
 * @author hocgin
 */
public class DeleteOneNode extends AbstractMethod {
    
    private static StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT @myLeft := lft, @myRight := rgt, @myWidth := rgt - lft + 1 FROM :table WHERE :id = #{id};")
            .append("DELETE FROM :table WHERE lft = @myLeft;")
            .append("UPDATE :table SET rgt = rgt - 1, lft = lft - 1 WHERE lft BETWEEN @myLeft AND @myRight;")
            .append("UPDATE :table SET rgt = rgt - 2 WHERE rgt > @myRight;")
            .append("UPDATE :table SET lft = lft - 2 WHERE lft > @myRight;")
            .append("</script>");
    
    private String getMethodName() {
        return "deleteOneNode";
    }
    
    /**
     * @param mapperClass
     * @param modelClass
     * @param tableInfo
     * @return
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = SQL.toString().replaceAll(":table", tableInfo.getTableName())
                .replaceAll(":id", tableInfo.getKeyColumn())
                .replaceAll(":columns", sqlSelectColumns(tableInfo, true));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, getMethodName(), sqlSource);
    }
}
