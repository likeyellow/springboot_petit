package kr.co.petit.persistence;

//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface TimeMapper {
	
	@Select("select now()")
	public String getTime();
	
	
	public String getTimeXML();
	
}


