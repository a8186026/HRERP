package com.goldcow.sframe.util.dateDML;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldcow.emanage.system.service.impl.SysMenuServiceImpl;

/**
 * 读取Excel文件，并将Excel中的数据封装为对象数组
 * 
 * @author Yaotaihang
 * @version v1.0
 * @since 2015-1-14
 */
public class ExcelDataReader {
	private static Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

	/**
	 * 将Excel表中的第一列表头与需要填入的表头顺序进行对应，取出列以及列的顺序
	 * 
	 * @param parmsName 要进行封装的列名以及各列对应属性的顺序
	 * @param rowHead 待封装的Excel中的第一列表头
	 * @return 需要取出的列的序号及顺序
	 * */
	private static List<Integer> getParmsNum(String[] parmsName, HSSFRow rowHead) {
		// 该表的总列数
		int colNum = rowHead.getPhysicalNumberOfCells();
		// 初始化代填装的数组
		List<Integer> parmsNum = new ArrayList<Integer>();
		// 根据比较对数组进行填装
		for (int i = 0; i < parmsName.length; i++) {
			for (int j = 0; j < colNum; j++) {
				// 判断当前列是不是要被寻找的列，如果是记录当前列的列数
				if (parmsName[i].equals(rowHead.getCell(j).toString())) {
					parmsNum.add(new Integer(j));
				}
			}
		}
		return parmsNum;
	}

	/**
	 * 根据与要封装的列对应的bean的属性名，取出Set方法的顺序（反射得到的方法顺序与编写顺序不同）
	 * 
	 * @param object 要封装成的对象
	 * @param parmsBeanName 与要封装的列对应的bean的属性名
	 * @return 需要的列所对应的bean属性的方法的序号及顺序
	 * */
	private static <T> List<Integer> getParmsBeanNum(T object, String[] parmsBeanName) {
		// 得到对象的类
		Class beanClass = object.getClass();
		// 初始化所有属性对应的set方法的序号
		List<Integer> parmsBeanNum = new ArrayList<Integer>();
		// 得到类中的所有的方法
		Method[] methods = beanClass.getMethods();
		for (int i = 0; i < parmsBeanName.length; i++) {
			// 得到该属性对应的set方法应有的名字，set+属性首字母大写即为对应的方法
			String methodName = "set" + parmsBeanName[i].substring(0, 1).toUpperCase() + parmsBeanName[i].substring(1);
			// 找到目标方法的序号
			for (int j = 0; j < methods.length; j++) {
				if (methods[j].getName().equals(methodName)) {
					// 存储该序号
					parmsBeanNum.add(new Integer(j));
				}
			}
		}
		return parmsBeanNum;
	}

	/**
	 * 将Excel表中的一列数据按照列的取出选择及取出顺序进行封装成bean
	 * 
	 * @param object 要封装成的对象
	 * @param row 待封装的Excel中的一列
	 * @param parmsNum 需要取出列的全部序号及这些列的保存顺序
	 * @param parmsBeanNum 需要取出列所对应bean属性的全部序号及顺序
	 * @param colNum 表的总列数
	 * @return 封装后的对象
	 * */
	private static <T> T createBean(T object, HSSFRow row, List<Integer> parmsNum, List<Integer> parmsBeanNum, int colNum) {
		// 得到对象的类
		Class beanClass = object.getClass();
		// 通过class类反射一个对象实体
		Object obj = null;
		try {
			obj = beanClass.newInstance();
		} catch (Exception e) {
			log.debug("创建bean的反射实例对象失败");
		}
		T bean = (T) obj;
		// 得到类中的所有的方法
		Method[] methods = beanClass.getMethods();

		// 遍历所有需要封装的列
		for (int i = 0; i < parmsNum.size(); i++) {
			// 根据rowHeader得到需要插入的列的单元格
			HSSFCell cell = row.getCell(parmsNum.get(i));
			// 得到该cell所对应的bean属性的set方法
			Method method = methods[parmsBeanNum.get(i)];
			System.out.println("方法名：" + method.getName());
			// 查询该方法需要用的参数
			Class<?> parameterType = method.getParameterTypes()[0];
			try {
				// 参数类型为String，单元格为字符串
				if (parameterType == String.class && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					method.invoke(bean, cell.getStringCellValue());
				}
				// 参数类型为Date，单元格为字符串
				else if (parameterType == Date.class && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					method.invoke(bean, new Date(cell.getStringCellValue()));
				}
				// 参数类型为int，单元格为数字
				else if (parameterType == int.class && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					method.invoke(bean, (int) cell.getNumericCellValue());
				}
				// 参数类型为Integer，单元格为数字
				else if (parameterType == Integer.class && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					method.invoke(bean, new Integer((int) cell.getNumericCellValue()));
				}
				// 参数类型为Double， 单元格为数字
				else if (parameterType == Double.class && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					method.invoke(bean, new Double(cell.getStringCellValue()));
				}
				// 参数类型为boolean，单元格为布尔型
				else if (parameterType == boolean.class && cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					method.invoke(bean, (boolean) cell.getBooleanCellValue());
				}
				// 参数类型为Boolean，单元格为布尔型
				else if (parameterType == Boolean.class && cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					method.invoke(bean, new Boolean(cell.getStringCellValue()));
				}
				// 参数类型为任意类型，单元格为空
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					// 目前不做处理
					System.out.println("参数类型为任意类型");
				}
				// 其他类型
				else {
					System.out.println("其他类型");
				}
			} catch (Exception e) {
				log.debug("单元格内容数据类型处理失败");
			}
		}
		return bean;
	}

	/**
	 * 将Excel表中的数据按照需要导入的列名及顺序封装成bean的List
	 * 
	 * @param object 要封装成的对象
	 * @param parmsName 要进行封装的列名以及各列对应属性的顺序
	 * @param parmsBeanName 与要封装的列对应的bean的属性名
	 * @param excelFileName 待插入的Excel文件路径
	 * @param sheetNumber 目标数据所在的标签页序号
	 * @return 封装后对象的数组
	 * */
	public static <T> List<T> getBeanListFromExcelDate(T object, String[] parmsName, String[] parmsBeanName, String excelFileName ,Integer sheetNumber) {
		try {
			// 从目标文件中读取数据
			InputStream inputStream = new FileInputStream(excelFileName);
			// 将目标文件装载至文件读取器
			POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
			// 解析得到Excel文件
			HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
			// 得到Excel文件的第一个标签页
			HSSFSheet sheet = workBook.getSheetAt(sheetNumber);
			// 得到该表的总行数
			int rowNum = sheet.getLastRowNum();
			// 得到该表的第一行，并通过第一行得到总列数
			int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
			// 得到需要保存的列的序号以及顺序
			List<Integer> parmsNum = getParmsNum(parmsName, sheet.getRow(0));
			// 得到需要保存的列所对应的属性的set方法的序号及数顺序
			List<Integer> parmsBeanNum = getParmsBeanNum(object, parmsBeanName);
			// 用来保存所有bean的List数组
			List<T> beanList = new ArrayList<T>();
			// 正文内容应该从第二行开始，第一行是表头
			for (int i = 1; i <= rowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				T bean = createBean(object, row, parmsNum, parmsBeanNum, colNum);
				beanList.add(bean);
			}
			return beanList;

		} catch (Exception e) {
			log.debug("Excel文件解析失败！");
		}
		return null;
	}
}
