package tedu;  
  
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
  
public class TestDataProvider{  
  
    @Test(dataProvider="dp")
    public void testLogin(Map<?, ?> param){
        System.out.println("method testLogin received:"+param.get("username")); 
        System.out.println("method testLogin received:"+param.get("password")); 
    }
       
    @Test(dataProvider="dp")
    public void testSearch(Map<?, ?> param){
        System.out.println("method testSearch received:"+param.get("keyword")); 
        System.out.println("method testSearch received:"+param.get("result")); 
    }
       
    @Test(dataProvider="dp")
    public void testStudent(Map<?, ?> param){
        System.out.println("method testStudent received:"+param.get("studentID")); 
        System.out.println("method testStudent received:"+param.get("studentName")); 
        System.out.println("method testStudent received:"+param.get("classNo"));
    }  
      
    @Test(dataProvider="dp")
    public void testTeacher(Map<?, ?> param){
        System.out.println("method testTeacher received:"+param.get("teacherID"));  
        System.out.println("method testTeacher received:"+param.get("teacherName")); 
        System.out.println("method testTeacher received:"+param.get("course")); 
    }
    
    @DataProvider  
    public Object[][] dp(Method method){
    	return ReadXML.getXMLData(method.getName(), "src/test/java/tedu/", "TestData.xml");
    }
  
}  