package com.example.securityserver.common.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义字段数据
 */
public class MyFieldPlugin implements CommentGenerator {

    public static List<String> NOT_SHOW = new ArrayList<String>(){{add("create_user_id");
                                                                    add("update_user_id");
                                                                    add("create_time");
                                                                    add("update_time");
                                                                    add("delete_status");
                                                                    }};

    @Override
    public void addConfigurationProperties(Properties properties) {

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        remarks = Arrays.stream(remarks.split("\\\n")).collect(Collectors.joining());
        field.addAnnotation("@ApiModelProperty(\""+remarks+"\")");
        if (MyFieldPlugin.NOT_SHOW.contains(introspectedColumn.getActualColumnName())){
            field.addJavaDocLine("@ApiParam(hidden = true)");
        }
        if ("id".equals(introspectedColumn.getActualColumnName())){
            field.addJavaDocLine("@NotBlank(message = \"请输入"+(StringUtils.isEmpty(remarks) ? "主键" : remarks) +"!\")");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.*");
        topLevelClass.addImportedType("io.swagger.annotations.*");
        topLevelClass.addImportedType("javax.validation.constraints.NotBlank");
        topLevelClass.addImportedType("com.fasterxml.jackson.annotation.JsonIgnoreProperties");

        //添加domain的注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@ApiModel(description = \""+introspectedTable.getRemarks()+"\")");

        StringBuilder stringBuilder = new StringBuilder();

        NOT_SHOW.stream().forEach(s -> {
            stringBuilder.append("\"" + s + "\",");
        });

        topLevelClass.addAnnotation("@JsonIgnoreProperties(value = {"+stringBuilder.toString()+"})");

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* @date: Created by Mybatis Generator on " + LocalDateTime.now());
        topLevelClass.addJavaDocLine("* @author: " + System.getProperty("user.name"));
        topLevelClass.addJavaDocLine("*/");

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement xmlElement) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }
}
