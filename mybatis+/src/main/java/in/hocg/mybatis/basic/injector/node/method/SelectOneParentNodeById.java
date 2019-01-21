package in.hocg.mybatis.basic.injector.node.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查找直属父节点
 *
 * @author hocgin
 */
public class SelectOneParentNodeById extends AbstractMethod {
    private static StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT :columns\n" +
                    "        FROM :table AS node,\n" +
                    "             :table AS parent\n" +
                    "        WHERE node.lft BETWEEN parent.lft AND parent.rgt\n" +
                    "          AND node.:id = #{id}\n" +
                    "        ORDER BY parent.lft DESC" +
                    "        LIMIT 1, 1;")
            .append("</script>");
    
    private String getMethodName() {
        return "selectOneParentNodeById";
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
                .replaceAll(":columns", "parent.*");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatement(mapperClass, getMethodName(), sqlSource, modelClass, tableInfo);
    }
    
}
