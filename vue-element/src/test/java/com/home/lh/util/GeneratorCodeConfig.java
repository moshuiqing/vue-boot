package com.home.lh.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 自动生成mybatisplus的相关代码
 */
public class GeneratorCodeConfig {

	public static String scanner(String tip) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	static Boolean bsf = true;
	static String tableName = "";

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("刘浩");
		gc.setOpen(false);
		// 实体属性 Swagger2 注解
		gc.setSwagger2(true);
		gc.setFileOverride(true);
		gc.setIdType(IdType.ASSIGN_UUID);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setActiveRecord(true);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
		pc.setParent("com.home.lh");
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setModuleName("system");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {

			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/java/com/home/lh/system/mapper/" + tableInfo.getEntityName() + "Mapper"
						+ StringPool.DOT_XML;
			}
		});

		//// 自定义注解输出配置
		try {
			// String templatePathEntity
			// =ResourceUtils.getURL("src\\main\\resources\\templates\\entity.java.ftl").getPath();
			// File file = ResourceUtils.getFile("classpath:templates/entity.java.ftl");
			String templatePathEntity = "/templates/entity.java.ftl";
			focList.add(new FileOutConfig(templatePathEntity) {
				@Override
				public String outputFile(TableInfo tableInfo) {
					// String tabName = tableInfo.getName();
					/* <#if field.propertyName =='createTime'> */
					List<TableField> feFields = tableInfo.getFields();

					for (TableField f : feFields) {

						switch (f.getPropertyName()) {
						case "createTime":
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("one", true);
							f.setCustomMap(map1);
							break;
						case "updateTime":
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("one", true);
							f.setCustomMap(map2);
							break;

						default:
							Map<String, Object> map3 = new HashMap<String, Object>();
							map3.put("one", false);
							f.setCustomMap(map3);

							break;
						}

					}

					String path = projectPath + "/src/main/java/com/home/lh/system/entity/" + tableInfo.getEntityName()
							+ ".java";

					return path;
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}

		// 自定义生成api.js
		focList.add(new FileOutConfig("/templates/api.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/src/main/resources/vue/api/" + tableInfo.getEntityName().toLowerCase() + ".js";
			}
		});

		// 自定义生成modules.js

		focList.add(new FileOutConfig("/templates/modules.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String a = tableInfo.getEntityName();
				if (!a.equals(tableName)) {
					tableName = tableInfo.getEntityName();
					new Thread(new Runnable() {

						@Override
						public void run() {

							IndexUtil.set(tableInfo.getEntityName().toLowerCase(),
									projectPath + "/src/main/resources/static/index.js");
						}
					}).start();

				}
				return projectPath + "/src/main/resources/vue/modules/" + tableInfo.getEntityName().toLowerCase()
						+ ".js";
			}
		});

		// 自定义生成api.js
		String vue = "";
		int k = 1;
		switch (k) {
		case 1:
			vue="/templates/vue/vue1.js.ftl";
			break;

		default:
			break;
		}
		focList.add(new FileOutConfig(vue) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/src/main/resources/vue/vue/"+new Date().getTime()+"index.vue";
			}
		});
		
		

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController("/templates/controller.java");

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(Model.class);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		strategy.setEntityTableFieldAnnotationEnable(true);
		strategy.setLogicDeleteFieldName("isdel");// 设置逻辑属性删除

		strategy.setEntityLombokModel(true);
		// 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		// 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		// 表的自动填充字段
		List<TableFill> tableFills = new ArrayList<>();
		TableFill updateTime = new TableFill("create_time", FieldFill.INSERT);
		TableFill updateName = new TableFill("update_name", FieldFill.INSERT_UPDATE);
		tableFills.add(updateTime);
		tableFills.add(updateName);
		strategy.setTableFillList(tableFills);

		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		mpg.execute();
	}
}
