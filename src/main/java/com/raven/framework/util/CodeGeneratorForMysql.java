package com.raven.framework.util;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * mysql数据库代码生成器
 *
 * @author Raven
 * @since 2024-01-15
 */
public class CodeGeneratorForMysql {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.hasLength(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String annotation = scanner("注释");
        String tableName = scanner("表名");
        String packageName = scanner("包名(package)");
        String address = scanner("生成地址(E:/XXX/XXX)");
        String author = scanner("作者");

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig
                .Builder("jdbc:mysql://127.0.0.1:3306/db_mca?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "AFFAIRS_SYNC", "ourcode0471")
                .schema("NMG_LAW")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(address)//指定生成文件的根目录
                .author(author)             //Author设置作者
                .disableOpenDir()           //禁止打开输出目录,禁止打开输出目录
                .dateType(DateType.ONLY_DATE)//设置时间类型
                .commentDate("yyyy-MM-dd") //注释日期
                .build();

        //包相关的配置项
        PackageConfig packageConfig = new PackageConfig
                .Builder()
                .parent("com.ceeye")  // 父包名
                .moduleName(packageName)      //父包模块名
                .entity("model")             //实体类 Entity 包名,默认值：entity
//                .service("service")          //Service 包名,默认值：entity
//                .serviceImpl("service.impl") //实现类 Service Impl 包名	默认值：service.impl
//                .mapper("mapper")            //Mapper 包名	默认值：mapper
//                .xml("mapper.xml")           //Mapper XML 包名	默认值：mapper.xml
//                .controller("controller")    //Controller 包名	默认值：controller
//                .other("other")              //自定义文件包名	可使用"other"，生产一个other文件目录
//                .pathInfo(pathInfo)          //路径配置信息
                .build();

        //模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();

        //注入配置（自定义内容）
        Map<String, Object> map = new HashMap<>(3);
        String controllerPath = "/" + tableName.replaceAll("_", "/").toLowerCase();
        map.put("controllerName", annotation);
        map.put("packageName", packageName);
        map.put("controllerPath", controllerPath);
        InjectionConfig injectionConfig = new InjectionConfig.Builder()
                .customMap(map)
                .build();


        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .addTablePrefix(packageConfig.getModuleName() + "_")
                .addInclude(tableName)
                .entityBuilder()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .superClass("com.basic.system.entity.basic.WebBasic")
                .enableLombok()
//                .addSuperEntityColumns("description", "create_time", "create_by", "delete_time", "delete_by", "is_del", "update_time", "update_by")
                .enableFileOverride()
                .controllerBuilder()
                .enableRestStyle()
                .enableHyphenStyle()
                .enableFileOverride()
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableBaseResultMap()
                .enableBaseColumnList()
                .enableFileOverride()
                .serviceBuilder()
                .enableFileOverride()
                .build();


        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig);
        autoGenerator.packageInfo(packageConfig);
        autoGenerator.template(templateConfig);
        autoGenerator.injection(injectionConfig);
        autoGenerator.strategy(strategyConfig);
        autoGenerator.execute(new FreemarkerTemplateEngine());
    }
}