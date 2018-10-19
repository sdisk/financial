package com.hq.financial;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * @program: financial
 * @description: 代码生成器
 * @author: Mr.Huang
 * @create: 2018-10-18 10:25
 **/
public class MysqlGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F:/gen/");
        gc.setFileOverride(true); //是否覆盖已有文件
        gc.setActiveRecord(true);
        gc.setEnableCache(false);//xml 二级缓存
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor("qiang.H");

        mpg.setGlobalConfig(gc);

        //数据源设置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
           public DbColumnType processTypeConvert(String filedType){
               return super.processTypeConvert(filedType);
           }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/finan?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //strategyConfig.setCapitalMode(true); //全局大写命名
        //strategyConfig.setDbColumnUnderline(false);//全局下划线命名
        strategyConfig.setTablePrefix(new String[]{"h_"});
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名生成策略
        mpg.setStrategy(strategyConfig);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hq.financial.web");
        //pc.setModuleName("");
        // pc.setModuleName("test");

        pc.setController("controller");// 这里是控制器包名，默认 web
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setXml("dao.mapping");
        pc.setService("service");
        pc.setServiceImpl("service.impl");

        mpg.setPackageInfo(pc);

        mpg.execute();



    }
}
