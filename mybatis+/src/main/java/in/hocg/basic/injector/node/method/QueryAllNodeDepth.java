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
 * 查询所有节点及其深度
 */
public class QueryAllNodeDepth extends AbstractMethod {
    StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT :columns, (COUNT(parent.:id) - 1) AS depth\n" +
                    "        FROM :table AS node,\n" +
                    "             :table AS parent\n" +
                    "        WHERE node.lft BETWEEN parent.lft AND parent.rgt\n" +
                    "        GROUP BY node.:id\n" +
                    "        ORDER BY node.lft;")
            .append("</script>");
    
    private String getMethodName() {
        return "queryAllNodeDepth";
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
