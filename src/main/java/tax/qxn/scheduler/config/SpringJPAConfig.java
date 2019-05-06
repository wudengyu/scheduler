package tax.qxn.scheduler.config;

import javax.sql.DataSource;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class SpringJPAConfig{
    @Bean
    public DataSource dataSource(){
        JndiObjectFactoryBean jndiObjectFactoryBean=new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/scheduler");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return (DataSource) jndiObjectFactoryBean;
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }
    @Bean
    public Properties hibernateProperties(){
        Properties hibernateProp=new Properties();
        hibernateProp.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        hibernateProp.put("hibernate.format_sql",true);
        hibernateProp.put("hibernate.use_sql_comments",true);
        hibernateProp.put("hibernate.show_sql",true);
        hibernateProp.put("hibernate.max_fetch_depth",3);
        hibernateProp.put("hibernate.jdbc.batch_size",10);
        hibernateProp.put("hibernate.jdbc.fetch_size",50);
        return hibernateProp;
        
    }
    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("tax.qxn.scheduler");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}
