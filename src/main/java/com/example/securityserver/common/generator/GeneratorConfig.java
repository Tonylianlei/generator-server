package com.example.securityserver.common.generator;

import com.example.securityserver.util.FileUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorConfig {

    public void generator(String path) throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
        myBatisGenerator.generate(null);

    }
    public static void main(String[] args) throws Exception {
        FileUtil fileUtil = new FileUtil();
        String path = fileUtil.getResourceFilePathByName("generatorConfig.xml");
        try {
            GeneratorConfig generatorSqlmap = new GeneratorConfig();
            generatorSqlmap.generator(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}