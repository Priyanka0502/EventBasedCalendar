package com.DBConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//it helps in Spring annotation based configuration.
//@Configuration annotation indicates that a class declares one or more @Bean methods 
//and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.daos"})
public class DBconfig {
DriverManagerDataSource dataSource=new DriverManagerDataSource();
	//Bean tells that a method produces a bean to be managed by the Spring container. It is a method-level annotation.
	@Bean(name="dataSource")
	public DataSource getDataSource()   //java.sql
	{
		System.out.println("I m Data source");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("hr");
		dataSource.setPassword("hr");
		return dataSource;
	}
	
	@Bean(name="sf")  //java.util
	public SessionFactory getSessionFactory()
	{
		System.out.println("I m getSessionFactory");
		Properties p=new Properties();
		p.setProperty("hibernate.dialect","org.hibernate.dialect.OracleDialect");
		p.setProperty("hibernate.hbm2ddl.auto","update");
		p.setProperty("hibernate.show_sql", "true");
//Spring's LocalSessionFactoryBuilder class is just the extension of Hibernate's Configuration class.		
		LocalSessionFactoryBuilder sb=new LocalSessionFactoryBuilder(getDataSource());
		sb.addProperties(p);
		sb.scanPackages("com.entities");
		
		return sb.buildSessionFactory();	
		
	}

	@Bean(name="HibernateTranscation")
	@Autowired
	public HibernateTransactionManager getHibernateTranscationManager(SessionFactory sessionFactory)
	{
		System.out.println("transaction");
		HibernateTransactionManager txManager=new HibernateTransactionManager(sessionFactory);
		return txManager;
	}	
	

}
