package com.br.unifacef.notification.configurations;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
						transactionManagerRef = "transactionManager",
						basePackages = {"com.br.unifacef.notification.domains.repositories.postgres"})
public class PgDBConfig {
	
	@Value("${spring.datasource.pg.url}")
    private String pgURL;

    @Value("${spring.datasource.pg.driverClassName}")
    private String pgDriver;

    @Value("${spring.datasource.pg.username}")
    private String pgUser;
    
    @Value("${spring.datasource.pg.password}")
    private String pgPass;
	
	
	  @Primary
	  @Bean(name = "dataSource")
	  public DataSource dataSource() {		  
		HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(pgDriver);
        dataSourceConfig.setJdbcUrl(pgURL);
        dataSourceConfig.setUsername(pgUser);
        dataSourceConfig.setPassword(pgPass);
 
        return new HikariDataSource(dataSourceConfig);
	  }
	
	  
	  @Primary
	  @Bean(name = "entityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource, Environment env) {
		  LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource);
	        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	        entityManagerFactoryBean.setPackagesToScan("com.br.unifacef.notification.domains.entities");
	 
	        Properties jpaProperties = new Properties();
	     
	        
	        jpaProperties.put("spring.jpa.database-platform", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
	        
	        jpaProperties.put("spring.jpa.properties.hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
	        
	        jpaProperties.put("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", true);
	        jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);
	        	        
	        
//	        jpaProperties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
//	        jpaProperties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
	        
	        jpaProperties.put("spring.jpa.hibernate.ddl-auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
	        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
	        
	        jpaProperties.put("spring.jpa.show-sql", env.getRequiredProperty("spring.jpa.show-sql"));
	        jpaProperties.put("hibernate.show_sql",env.getRequiredProperty("spring.jpa.show-sql"));
	        
	 
	        entityManagerFactoryBean.setJpaProperties(jpaProperties);
	 
	        return entityManagerFactoryBean;
	  }
	
	  @Primary
	  @Bean(name = "transactionManager")
	  public PlatformTransactionManager transactionManager(
	      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
	    return new JpaTransactionManager(entityManagerFactory);
	  }
}
