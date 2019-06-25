package kr.co.petit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;


@SpringBootApplication
//@MapperScan("kr.co.persistence") 
// 스프링부트는 @SpringBootApplication 어노테이션이 정의되어 있는 클래스를 기준으로
// 컴포넌트를 스캔함
public class Petit2019Application {

	public static void main(String[] args) {
		SpringApplication.run(Petit2019Application.class, args);
	}

}
