package pl.wszib.kolo.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@ComponentScan({//"pl.wszib.kolo.controller",
"pl.wszib.kolo.dao", "pl.wszib.kolo.service" })
public class SpringConfiguration {
	@Value("${db.driver.class}")
	private String dbDriverClass;
	@Value("${db.jdbc.url}")
	private String dbJdbcUrl;
	@Value("${db.user}")
	private String dbUser;
	@Value("${db.password}")
	private String dbPassword;

	/**
	 * Properties configuration. The properties can later be accessed from beans
	 * by using the <code>@Value</code> annotation (e.g.
	 * <code>@Value("property.name") String property;</code>).
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer properies = new PropertySourcesPlaceholderConfigurer();
		Resource[] resourceLocations = new Resource[] {
				new ClassPathResource("contacts.properties"),
				new ClassPathResource("contacts-override.properties") };
		properies.setLocations(resourceLocations);
		properies.setIgnoreResourceNotFound(true);

		return properies;
	}

	@Bean
	public FactoryBean<SessionFactory> createSessionFactory() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(dbDriverClass);
		dataSource.setJdbcUrl(dbJdbcUrl);
		dataSource.setUser(dbUser);
		dataSource.setPassword(dbPassword);

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory
				.setPackagesToScan(new String[] { "pl.wszib.kolo.model" });
		sessionFactory
				.setHibernateProperties(hibernateProperties().getObject());

		return sessionFactory;
	}

	@Bean
	public FactoryBean<Properties> hibernateProperties() {
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		pfb.setLocations(new Resource[] {
				new ClassPathResource("hibernate.properties"),
				new ClassPathResource("hibernate-override.properties") });
		pfb.setIgnoreResourceNotFound(true);

		return pfb;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws Exception {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(createSessionFactory().getObject());

		return txManager;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
}
