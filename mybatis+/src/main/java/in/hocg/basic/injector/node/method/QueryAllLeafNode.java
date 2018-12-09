package in.hocg.basic.injector.node.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class QueryAllLeafNode extends AbstractMethod {
    StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT :columns\n" +
                    "        FROM :table AS node\n" +
                    "        WHERE rgt = lft + 1;")
            .append("</script>");
    
    private String getMethodName() {
        return "queryAllLeafNode";
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
                .replaceAll(":columns", "node.*");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatement(mapperClass, getMethodName(), sqlSource, modelClass, tableInfo);
    }
    
}
