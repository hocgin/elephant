package in.hocg.basic.injector.node.method;

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
 * WHERE name = 'GAME CONSOLES';
 *
 * DELETE FROM nested_category WHERE lft BETWEEN @myLeft AND @myRight;
 *
 * UPDATE nested_category SET rgt = rgt - @myWidth WHERE rgt > @myRight;
 * UPDATE nested_category SET lft = lft - @myWidth WHERE lft > @myRight;
 *
 * UNLOCK TABLES;
 *```
 *
 * 删除该节点及其所有子节点
 *
 * @author hocgin
 */
public class EmptyNode extends AbstractMethod {
    
    StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT @myLeft := lft, @myRight := rgt, @myWidth := rgt - lft + 1 FROM :table WHERE :id = #{id};")
            .append("DELETE FROM :table WHERE lft BETWEEN @myLeft AND @myRight;")
            .append("UPDATE :table SET rgt = rgt - @myWidth WHERE rgt > @myRight;")
            .append("UPDATE :table SET lft = lft - @myWidth WHERE lft > @myRight;")
            .append("</script>");
    
    private String getMethodName() {
        return "emptyNode";
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
                .replaceAll(":id", tableInfo.getKeyColumn());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, getMethodName(), sqlSource);
    }
}
