package tedu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;

/**
 * tedu.cn
 * @author zhengxj
 *
 */
public class ReadFile {

	  /*
	   * ʹ��POI��������ļ��Ĳ�����ݣ���װΪ@DataProvider��Ҫ������Object[][]
	   * filePath:�ļ�·��
	   * fileName:�ļ����
	   * sheetName:�?���
	   */
		public static Object[][] getTestDataFromExcel(String filePath, String fileName,
				String sheetName) {
			try{
			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook Workbook = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) {
				Workbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				Workbook = new HSSFWorkbook(inputStream);
			}
			Sheet Sheet = Workbook.getSheet(sheetName);
			int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
			int colCount = Sheet.getRow(0).getLastCellNum();
			List<Object[]> records = new ArrayList<Object[]>();
			for (int i = 1; i < rowCount + 1; i++) {
				Row row = Sheet.getRow(i);
				String fields[] = new String[colCount];
				for (int j = 0; j < colCount; j++) {
					Cell cell=row.getCell(j);
					if (j>=row.getLastCellNum() || cell==null){
						fields[j] = "";
					}else{
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fields[j] = cell.getStringCellValue();
					}
				}
				records.add(fields);
			}
			Log.info("Row Count:"+records.size());
			Object[][] results = new Object[records.size()][];
			for (int i = 0; i < records.size(); i++) {
				Log.info("Current row��"+(i+1));
				results[i] = records.get(i);
				System.out.println(results[i]);
			}
			return results;
			}catch(IOException e){
				e.printStackTrace();
				Log.error(e.getMessage());
				return null;
			}
		}

	  /*
	   * ���CSV�ļ��Ĳ�����ݣ���װΪ@DataProvider��Ҫ������Object[][]
	   * filePath:�ļ�·��
	   * fileName:�ļ����
	   */
		public static Object[][] getTestDataFromCSVFile(String filePath, String fileName){
			CSVReader csvReader;
			List<String[]> records =new ArrayList<String[]>();	
			try {
				csvReader = new CSVReader(new FileReader(filePath + fileName));
				records = csvReader.readAll();
				csvReader.close();
			} catch (IOException e) {
				Log.error("Fail to read from data file. ");
				e.printStackTrace();
			}
			Log.info("Row Count:"+(records.size()-1));
			Object[][] results = new Object[records.size()-1][];
			//�ӵ�2�У�i=1����ʼʹ�����
			for (int i = 1; i < records.size(); i++) {
				Log.info("Current row��"+i);
				results[i-1] = records.get(i);
				System.out.println(results[i-1].toString());
			}
			return results;
		}
}
