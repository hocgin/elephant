package in.hocg.sample;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.model.DefaultModel;
import in.hocg.mybatis.basic.model.DeletedModel;
import in.hocg.mybatis.basic.model.SuperModel;
import in.hocg.scaffold.support.basis.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 * 代码生成器
 *
 * @author hocgin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Application {
    @Autowired
    private DataSourceProperties properties;
    
    private static String OUTPUT_DIR;
    private static String PACKAGE_NAME = "in.hocg.manager";
    private static List TABLES = Arrays.asList("access_log");
    
    @Test
    public void main() {
        OUTPUT_DIR = Paths.get(System.getProperty("user.dir"),
                "src/main/java").toString();
        generateByTables(PACKAGE_NAME, (String[]) TABLES.toArray());
    }
    
    
    /**
     * 配置
     *
     * @param packageName
     * @param tableNames
     */
    private void generateByTables(String packageName, String... tableNames) {
        
        String superEntityClassName = DeletedModel.class.getName();
        String superServiceImplClassName = BaseService.class.getName();
        
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MARIADB)
                .setUrl(properties.getUrl())
                .setUsername(properties.getUsername())
                .setPassword(properties.getPassword())
                .setDriverName(properties.getDriverClassName());
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .entityTableFieldAnnotationEnable(true)
                .setCapitalMode(true)
                .setEntityColumnConstant(true)
                .setEntityLombokModel(true)
                .setSuperEntityColumns(SuperModel.ID,
                        DefaultModel.CREATED_AT,
                        DefaultModel.CREATOR,
                        DefaultModel.UPDATED_AT,
                        DefaultModel.UPDATER,
                        DeletedModel.DELETED_AT,
                        DeletedModel.DELETER,
                        DeletedModel.DELETED
                )
                .setControllerMappingHyphenStyle(true)
                
                .setSuperEntityClass(superEntityClassName)
                .setSuperControllerClass(BaseController.class.getName())
                .setSuperServiceImplClass(superServiceImplClassName)
//                .setSuperServiceClass()
//                .setSuperMapperClass(BaseMapper)
                
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setOpen(false)
                .setAuthor(System.getProperty("user.name"))
                .setOutputDir(OUTPUT_DIR)
                .setFileOverride(true)
                .setServiceName("%sService");
        
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(new TemplateConfig().setEntity("/template/entity.java"))
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}
