// Mybatis에서 사용하던 객체들을 스프링 컨테이너에 등록해두고 받아서 사용
package kr.co.petit.dbconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(basePackages="kr.co.petit.persistence")
public class DatabaseConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") // mybatis 설정 빈 등록
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.jpa")// jpa 설정 빈 등록
	public Properties hibernateConfig() {
		return new Properties();
	}

	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration") // application.properties의 설정 중 mybatis관련 설정 가져옮
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration(); // 가져온 mybatis 설정을 자바 클래스로 만들어 반환
		 
	}
	 
	
	
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 마이바티스가 사용한 DataSource를 등록
		sqlSessionFactoryBean.setDataSource(dataSource); 
		// 마이바티스 설정파일 위치 설정

		//sqlSessionFactoryBean.setMapperLocations(ApplicationContext.getResources("classpath:/mappers/**/*.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:/mappers/**/*.xml"));
		
		sqlSessionFactoryBean.setConfiguration(mybatisConfig()); // 세션팩토리에 mybatis 설정 파일 추가
		
		
		return sqlSessionFactoryBean.getObject();	
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}


