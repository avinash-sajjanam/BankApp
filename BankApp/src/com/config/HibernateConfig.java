package com.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.model.Account;
import com.model.User;

public class HibernateConfig {
	
private static SessionFactory sessionFactory;
	
	static{
		try{
			Configuration configuration = new Configuration();

			configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/hibernate");
			configuration.setProperty("hibernate.connection.username","root");
			configuration.setProperty("hibernate.connection.password","root");
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			configuration.setProperty("hibernate.show_sql", "false");
			configuration.setProperty("hibernate.connection.autocommit", "true");
			configuration.setProperty("hibernate.id.new_generator_mappings", "false");

			StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

				sessionFactory = configuration.addPackage("com.model.*").addAnnotatedClass(User.class).addAnnotatedClass(Account.class).buildSessionFactory(builder.build());
		}catch(Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static Session getSessionFactory() throws HibernateException{
		return sessionFactory.openSession();  
	}
}
