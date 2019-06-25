package kr.co.petit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.petit.persistence.TimeMapper;
import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class Petit2019ApplicationTests {

	@Autowired
	TimeMapper timeMapper;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test1() {
		
		log.info("-------------------------");
		log.info("TIME 1: " + timeMapper.getTime());
	}
	
	
	 @Test 
	 public void test2() { 
		 
		 log.info("--------------------------");
		 log.info("TIME 2: " + timeMapper.getTimeXML()); 
	 }
	 
	
	
}
