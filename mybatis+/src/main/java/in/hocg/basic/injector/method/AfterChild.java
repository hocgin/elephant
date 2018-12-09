package in.hocg.basic.injector.method;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AfterChild extends AbstractMethod {
    
    StringBuilder SQL = new StringBuilder("<script>")
            .append("SELECT @myLeft := lft FROM %s WHERE %s = #{id};")
            .append("UPDATE %s SET rgt = rgt + 2 WHERE rgt > @myLeft;")
            .append("UPDATE %s SET lft = lft + 2 WHERE lft > @myLeft;")
            .append("INSERT INTO %s %s VALUES %s;")
            .append("</script>");
    
    /**
     * @param mapperClass
     * @param modelClass
     * @param tableInfo
     * @return
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumn(false),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA)
                // fixed: mybatis plus bug
                // 添加前缀不生效。。
                .replaceAll("<if test=\"", "<if test=\"node.")
                .replaceAll(" != null and ", " != null and node.")
                // ---
                .replace("<if test=\"node.lft != null\">lft,</if>", "lft,")
                .replace("<if test=\"node.rgt != null\">rgt,</if>", "rgt,");
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlProperty(false, "node."),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA)
                // fixed: mybatis plus bug
                // 添加前缀不生效。。
                .replaceAll("<if test=\"", "<if test=\"node.")
                .replaceAll(" != null and ", " != null and node.")
                // ---
                .replace("<if test=\"node.lft != null\">#{node.lft},</if>", "@myLeft + 1,")
                .replace("<if test=\"node.rgt != null\">#{node.rgt},</if>", "@myLeft + 2,");
        String sql = String.format(SQL.toString(),
                tableInfo.getTableName(), tableInfo.getKeyColumn(),
                tableInfo.getTableName(),
                tableInfo.getTableName(),
                tableInfo.getTableName(), columnScript, valuesScript
        );
        
        String method = "appendChild";
        KeyGenerator keyGenerator = new NoKeyGenerator();
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /** 自增主键 */
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(tableInfo, builderAssistant, method, languageDriver);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, method, sqlSource, keyGenerator, keyProperty, keyColumn);
    }
}
