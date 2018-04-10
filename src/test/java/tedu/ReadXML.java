package tedu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXML {
 
    public static Object[][] getXMLData(String methodName,String filePath,String fileName){
    	//Parse XML file
    	File inputXml = new File(filePath+"/"+fileName);  
        List list=new ArrayList<HashMap>();                  
        int count = 1;  
        SAXReader saxReader = new SAXReader();  
        try {  
            Document document = saxReader.read(inputXml);  
            Element employees = document.getRootElement();  
            for (Iterator i = employees.elementIterator(); i.hasNext();) {
                Element employee = (Element) i.next();  
                Map map = new HashMap();  
                Map tempMap = new HashMap();  
                for (Iterator j = employee.elementIterator(); j.hasNext();) {  
                    Element node = (Element) j.next();                      
                    tempMap.put(node.getName(), node.getText());                      
                }  
                map.put(employee.getName(), tempMap);  
                list.add(map);  
            }  
        } catch (DocumentException e) {  
            System.out.println(e.getMessage());  
        }
        //Create Object[][] to return
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();          
        for (int i = 0; i < list.size(); i++) {
            Map m = (Map) list.get(i);
            if(m.containsKey(methodName)){                              
                Map<String, String> dm = (Map<String, String>) m.get(methodName);  
                result.add(dm);
            }  
        }    
        Object[][] files = new Object[result.size()][];  
        for(int i=0; i<result.size(); i++){  
            files[i] = new Object[]{result.get(i)};  
        }          
        return files;
    }
}
