package in.hocg.mybatis.basic.injector.node;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import in.hocg.mybatis.basic.NodeMapper;
import in.hocg.mybatis.basic.injector.node.method.*;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class NodeSqlInjector extends DefaultSqlInjector {
    
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        if (Arrays.asList(mapperClass.getInterfaces()).contains(NodeMapper.class)) {
            String className = mapperClass.toString();
            Set<String> mapperRegistryCache = GlobalConfigUtils.getMapperRegistryCache(builderAssistant.getConfiguration());
            if (!mapperRegistryCache.contains(className)) {
                List<AbstractMethod> methodList = this.getMethodList();
                methodList.addAll(this.getNodeMethod());
                Assert.notEmpty(methodList, "No effective injection method was found.");
                // 循环注入自定义方法
                Class<?> modelClass = extractModelClass(mapperClass);
                for (AbstractMethod abstractMethod : methodList) {
                    TableInfo tableInfo = TableInfoHelper.initTableInfo(builderAssistant, modelClass);
                    abstractMethod.inject(builderAssistant, mapperClass, modelClass, tableInfo);
                }
                mapperRegistryCache.add(className);
                /*
                  初始化 AntiSQL 解析
                 */
                if (GlobalConfigUtils.getGlobalConfig(builderAssistant.getConfiguration()).isSqlParserCache()) {
                    SqlParserHelper.initSqlParserInfoCache(mapperClass);
                }
            }
        } else {
            super.inspectInject(builderAssistant, mapperClass);
        }
    }
    
    public List<AbstractMethod> getNodeMethod() {
        return Arrays.asList(
                new InsertOneChildNode(),
                new InsertOneSiblingNode(),
                new DeleteNodes(),
                new DeleteOneNode(),
                new SelectMultiFullTree(),
                new SelectAllChildren(),
                new SelectMultiTreePathByLeafId(),
                new SelectMultiTreeNodeHasDepth(),
                new SelectAllNodeHasDepth(),
                new SelectAllLeaf(),
                new SelectOneParentNodeById(),
                new Analysis()
        );
    }
}
