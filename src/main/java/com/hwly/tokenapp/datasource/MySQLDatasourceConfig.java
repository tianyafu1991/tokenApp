package com.hwly.tokenapp.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = MySQLDatasourceConfig.PACKAGE,
	sqlSessionFactoryRef = "gpSqlSessionFactory")
public class MySQLDatasourceConfig {
	
	// oracledao扫描路径
	static final String PACKAGE = "com.hwly.tokenapp.mapper";
	// mybatis mapper扫描路径
	static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
	
	@Bean(name = "gpdatasource")
	@ConfigurationProperties("spring.datasource.druid.mysql")
	public DataSource gpdatasource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(name = "gpTransactionManager")
    public DataSourceTransactionManager gpTransactionManager() {
        return new DataSourceTransactionManager(gpdatasource());
    }
 
    @Bean(name = "gpSqlSessionFactory")
    public SqlSessionFactory gpSqlSessionFactory(@Qualifier("gpdatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MySQLDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
