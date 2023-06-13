package com.medi.mediapi.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * <pre>
 *     Mysql medi DataSource
 *     Primary DataSource
 *     property : spring.datasource.hikari.medi-dev
 *     mapper : resources/mapper/store
 * </pre>
 */

@Configuration
public class MySQLDevDataSourceConfig {

    @Bean
    @Primary
    @Qualifier("mediDevHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.medi-dev")
    public HikariConfig mediDevHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    @Qualifier("mediDevDataSource")
    public DataSource mediDevDataSource() { return new HikariDataSource(mediDevHikariConfig()); }

    @Bean
    @Primary
    @Qualifier("mediDevSqlSessionFactory")
    public SqlSessionFactory mediDevSqlSessionFactory(@Qualifier("mediDevDataSource") DataSource dataSource,
                                                        ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/store/**/*.xml"));
        factoryBean.setVfs(SpringBootVFS.class);

        return factoryBean.getObject();
    }

    @Bean
    @Primary
    @Qualifier("mediDevSqlSessionTemplate")
    public SqlSessionTemplate mediDevSqlSessionTemplate(
            @Qualifier("mediDevSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
