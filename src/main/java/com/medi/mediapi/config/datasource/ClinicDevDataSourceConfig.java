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
 *     Mysql Clinic DataSource
 *     Primary DataSource
 *     property : spring.datasource.hikari.clinic-dev
 *     mapper : resources/mapper/clinic
 * </pre>
 */

@Configuration
public class ClinicDevDataSourceConfig {

    @Bean
    @Primary
    @Qualifier("clinicDevHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.clinic-dev")
    public HikariConfig clinicDevHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    @Qualifier("clinicDevDataSource")
    public DataSource clinicDevDataSource() { return new HikariDataSource(clinicDevHikariConfig()); }

    @Bean
    @Primary
    @Qualifier("clinicDevSqlSessionFactory")
    public SqlSessionFactory clinicDevSqlSessionFactory(@Qualifier("clinicDevDataSource") DataSource dataSource,
                                                        ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/clinic/**/*.xml"));
        factoryBean.setVfs(SpringBootVFS.class);

        return factoryBean.getObject();
    }

    @Bean
    @Primary
    @Qualifier("clinicDevSqlSessionTemplate")
    public SqlSessionTemplate clinicDevSqlSessionTemplate(
            @Qualifier("clinicDevSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
