package com.github.bluelink.restapi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.github.bluelink.restapi.repository", sqlSessionTemplateRef = "MysqlSessionTemplate")
public class MysqlDatasource {

  @Bean(name = "MysqlDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.primary")
  @Primary
  public DataSource MysqlDataSource(){
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "MysqlSessionFactory")
  @Primary
  public SqlSessionFactory MysqlSessionFactory(@Qualifier("MysqlDataSource") DataSource dataSource) throws Exception{
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    return bean.getObject();
  }

  @Bean(name = "MysqlTransactionManager")
  @Primary
  public DataSourceTransactionManager MysqlTransactionManager(@Qualifier("MysqlDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "MysqlSessionTemplate")
  @Primary
  public SqlSessionTemplate MysqlSessionTemplate(@Qualifier("MysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}
