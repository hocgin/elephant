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
public class QueryNodeAndChildren extends AbstractMethod {
    StringBuilder SQL = new StringBuilder("<script><![CDATA[")
            .append("SELECT :columns, (COUNT(parent.:id) - (sub_tree.depth + 1)) AS depth\n" +
                    "        FROM :table AS node,\n" +
                    "             :table AS parent,\n" +
                    "             :table AS sub_parent,\n" +
                    "             (SELECT node.:id, (COUNT(parent.:id) - 1) AS depth\n" +
                    "              FROM :table AS node,\n" +
                    "                   :table AS parent\n" +
                    "              WHERE node.lft BETWEEN parent.lft AND parent.rgt\n" +
                    "                AND node.:id = #{id}\n" +
                    "              GROUP BY node.:id\n" +
                    "              ORDER BY node.lft) AS sub_tree\n" +
                    "        WHERE node.lft BETWEEN parent.lft AND parent.rgt\n" +
                    "          AND node.lft BETWEEN sub_parent.lft AND sub_parent.rgt\n" +
                    "          AND sub_parent.:id = sub_tree.:id\n" +
                    "        GROUP BY node.:id\n" +
                    "        HAVING depth = 1\n" +
                    "        ORDER BY node.lft;")
            .append("]]></script>");
    
    private String getMethodName() {
        return "queryNodeAndChildren";
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
