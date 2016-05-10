package com.goldcow.sframe.util;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Boolean;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 在jxl.jar包基础上封装的操作Excel工具类
 * 
 * @author chengrongchang
 * 
 */
public class JExcelUtils {
	// Workbook对象，wbook可写，rbook只读
	private WritableWorkbook wbook = null;
	private Workbook rbook = null;
	// Excel文件
	private File file = null;
	// 是否可写
	private boolean writable = false;
	// 单元格格式
	private CellFormat cellFormat = null;
	private Point _cellFormat = null;

	/**
	 * 构造方法
	 * 
	 * @param xlsFile Excel文件对象
	 * @param writable 是否可修改
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BiffException
	 */
	public JExcelUtils(File xlsFile, boolean writable) throws FileNotFoundException, IOException, BiffException {
		if (!xlsFile.exists()) {
			if (writable) {
				if (!xlsFile.getParentFile().exists()) {
					xlsFile.getParentFile().mkdirs();
				}
				xlsFile.createNewFile();
			} else {
				throw new FileNotFoundException();
			}
		}

		this.file = xlsFile;
		this.writable = writable;
		if (writable) {
			wbook = Workbook.createWorkbook(xlsFile);
		} else {
			rbook = Workbook.getWorkbook(xlsFile);
		}
	}

	/**
	 * 设置模板（只在可修改时有效）
	 * 
	 * @param template 模板Excel文件
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BiffException
	 */
	public void setTemplate(File template) throws FileNotFoundException, IOException, BiffException {
		if (this.writable) {
			if (this.wbook != null) {
				this.wbook.close();
			}
			if (this.rbook != null) {
				this.rbook.close();
			}
			this.rbook = Workbook.getWorkbook(template);
			this.wbook = Workbook.createWorkbook(this.file, this.rbook);
		}
	}

	/**
	 * 设置单元格格式，对write方法中的数据有效
	 * 
	 * @param cellFormat 单元格格式
	 */
	public void setCellFormat(CellFormat cellFormat) {
		if (this.writable) {
			this.cellFormat = cellFormat;
		}
	}

	/**
	 * 设置单元格格式，对write方法中的数据有效 以某一页的指定单元格为依据 若col或row为负，则列或行索引为动态，自动读取模板中的单元格格式。
	 * 
	 * @param sheetIndex 页号
	 * @param col 列
	 * @param row 行
	 */
	public void setCellFormat(int sheetIndex, int col, int row) {
		if (col >= 0 && row >= 0) {
			Sheet sheet = this.getSheet(sheetIndex);
			this.cellFormat = sheet.getCell(col, row).getCellFormat();
			this._cellFormat = null;
		} else {
			this.cellFormat = null;
			this._cellFormat = new Point(col, row);
		}
	}

	/**
	 * 获得页数
	 * 
	 * @return
	 */
	public int getSheetCount() {
		return this.writable ? this.wbook.getNumberOfSheets() : this.rbook.getNumberOfSheets();
	}

	/**
	 * 创建新页
	 * 
	 * @param sheetTitle 页标题
	 * @param sheetIndex 页索引
	 */
	public void createSheet(String sheetTitle, int sheetIndex) {
		if (this.writable) {
			this.wbook.createSheet(sheetTitle, sheetIndex);
		}
	}

	/**
	 * 读取指定页的全部数据
	 * 
	 * @param sheetIndex 页索引
	 * @return
	 */
	public List<Object[]> read(int sheetIndex) {
		Sheet sheet = this.getSheet(sheetIndex);
		List<Object[]> data = new ArrayList<Object[]>();

		for (int i = 0; i < sheet.getRows(); i++) {
			Cell[] row = sheet.getRow(i);
			Object[] rowData = new Object[row.length];
			for (int j = 0; j < rowData.length; j++) {
				if (row[j].getType() == CellType.NUMBER) {
					rowData[j] = new Number((NumberCell) row[j]).getValue();
				} else if (row[j].getType() == CellType.DATE) {
					rowData[j] = new DateTime((DateCell) row[j]).getDate();
				} else if (row[j].getType() == CellType.BOOLEAN) {
					rowData[j] = new Boolean((BooleanCell) row[j]).getValue();
				} else {
					rowData[j] = row[j].getContents();
				}
			}

			data.add(rowData);
		}

		return data;
	}
	
	/**
	 * 读取指定页的全部数据
	 * 
	 * @param sheetIndex 页索引
	 * @param col 起始列
	 * @param row 起始行
	 * @return
	 */
	public List<Object[]> read(int sheetIndex, int col, int row) {
		Sheet sheet = this.getSheet(sheetIndex);
		List<Object[]> data = new ArrayList<Object[]>();

		for (int i = row; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);
			Object[] rowData = new Object[cells.length];
			for (int j = col; j < rowData.length; j++) {
				if (cells[j].getType() == CellType.NUMBER) {
					rowData[j] = new Number((NumberCell) cells[j]).getValue();
				} else if (cells[j].getType() == CellType.DATE) {
					rowData[j] = new DateTime((DateCell) cells[j]).getDate();
				} else if (cells[j].getType() == CellType.BOOLEAN) {
					rowData[j] = new Boolean((BooleanCell) cells[j]).getValue();
				} else {
					rowData[j] = cells[j].getContents();
				}
			}

			data.add(rowData);
		}

		return data;
	}

	/**
	 * 读取某一单元格数据
	 * 
	 * @param sheetIndex 页索引
	 * @param column 列
	 * @param row 行
	 * @return
	 */
	public Object readCell(int sheetIndex, int column, int row) {
		Sheet sheet = this.getSheet(sheetIndex);
		Cell cell = sheet.getCell(column, row);
		return cell.getContents();
	}

	/**
	 * 读取某一行数据
	 * 
	 * @param sheetIndex 页索引
	 * @param row 行
	 * @return
	 */
	public Object[] readRow(int sheetIndex, int row) {
		Sheet sheet = this.getSheet(sheetIndex);
		Cell[] cells = sheet.getRow(row);
		Object[] rowData = new Object[cells.length];

		for (int i = 0; i < cells.length; i++) {
			rowData[i] = cells[i].getContents();
		}

		return rowData;
	}

	/**
	 * 读取某一列数据
	 * 
	 * @param sheetIndex 页索引
	 * @param row 行
	 * @return
	 */
	public Object[] readCol(int sheetIndex, int col) {
		Sheet sheet = this.getSheet(sheetIndex);
		Cell[] cells = sheet.getColumn(col);
		Object[] colData = new Object[cells.length];

		for (int i = 0; i < cells.length; i++) {
			colData[i] = cells[i].getContents();
		}

		return colData;
	}

	/**
	 * 将数据写入指定页
	 * 
	 * @param sheetIndex 页索引
	 * @param data 数据
	 * @throws Exception
	 */
	public void write(int sheetIndex, List<Object[]> data) throws Exception {
		write(sheetIndex, data, 0, 0);
	}

	/**
	 * 将数据写入指定页
	 * 
	 * @param sheetIndex 页索引
	 * @param data 数据
	 * @param col 起始列
	 * @param row 起始行
	 * @throws Exception
	 */
	public void write(int sheetIndex, List<Object[]> data, int col, int row) throws Exception {
		if (!this.writable) {
			return;
		}

		WritableSheet sheet = (WritableSheet) this.getSheet(sheetIndex);

		clearData(sheet, col, row);

		for (int i = 0; i < data.size(); i++) {
			writeRow(sheet, i + row, col, data.get(i));
		}
	}

	/**
	 * 将数据写入指定页
	 * 
	 * @param sheetIndex 页索引
	 * @param list 查询结果
	 * @param col 起始列
	 * @param row 起始行
	 * @throws Exception
	 */
	public void writeFromSQLResult(int sheetIndex, List<Map<String, Object>> list, int col, int row) throws Exception {
		if (!this.writable) {
			return;
		}

		WritableSheet sheet = (WritableSheet) this.getSheet(sheetIndex);

		clearData(sheet, col, row);

		for (int i = 0; i < list.size(); i++) {
			Collection<Object> collection = list.get(i).values();
			writeRow(sheet, i + row, col, collection.toArray(new Object[collection.size()]));
		}
	}

	/**
	 * 将数据写入指定页
	 * 
	 * @param sheetIndex 页索引
	 * @param rs ResultSet
	 * @param col 起始列
	 * @param row 起始行
	 * @throws Exception
	 */
	public void writeFromResultSet(int sheetIndex, ResultSet rs, int col, int row) throws Exception {
		if (!this.writable) {
			return;
		}

		WritableSheet sheet = (WritableSheet) this.getSheet(sheetIndex);

		clearData(sheet, col, row);

		int index = 0;
		while (rs.next()) {
			Object[] data = new Object[rs.getMetaData().getColumnCount()];
			for (int i = 0; i < data.length; i++) {
				data[i] = rs.getObject(i + 1);
			}
			writeRow(sheet, index + row, col, data);
			index++;
		}
	}

	/**
	 * 关闭流，在修改Excel时，如果不调用close方法，则不会将数据写入文件。
	 */
	public void close() {
		if (this.writable) {
			try {
				this.wbook.write();
				this.wbook.close();
			} catch (Exception e) {
			}
		} else {
			this.rbook.close();
		}
	}

	/*
	 * 获得页对象
	 * 
	 * @param sheetIndex 页号
	 * @return
	 */
	public Sheet getSheet(int sheetIndex) {
		return this.writable ? this.wbook.getSheet(sheetIndex) : this.rbook.getSheet(sheetIndex);
	}

	/*
	 * 清除数据，在write方法中调用
	 * @param sheetIndex 页号
	 * @param col 起始列
	 * @param row 起始行
	 * @throws Exception
	 */
	private void clearData(WritableSheet sheet, int col, int row) throws Exception {
		for (int i = row; i < sheet.getRows(); i++) {
			int column = sheet.getRow(i).length;
			for (int j = col; j < column; j++) {
				sheet.addCell(createCell(sheet, null, j, i));
			}
		}
	}

	/*
	 * 写入一行数据
	 * 
	 * @param sheet 页对象
	 * @param row 起始列
	 * @param col 起始行
	 * @param data 行数据
	 * @throws Exception
	 */
	private void writeRow(WritableSheet sheet, int row, int col, Object[] data) throws Exception {
		for (int j = 0; j < data.length; j++) {
			sheet.addCell(createCell(sheet, data[j], j + col, row));
		}
	}

	/*
	 * 创建可写的Cell对象
	 * 
	 * @param sheet 页对象
	 * @param value 值
	 * @param col 列
	 * @param row 行
	 * @return
	 */
	private WritableCell createCell(WritableSheet sheet, Object value, int col, int row) {
		WritableCell cell = null;
		if (value instanceof java.lang.Number) {
			cell = new Number(col, row, Double.valueOf(value.toString()));
		} else if (value instanceof Date) {
			cell = new DateTime(col, row, (Date) value);
		} else {
			if (value != null) {
				cell = new Label(col, row, String.valueOf(value));
			} else {
				cell = new Label(col, row, "");
			}
		}

		setCellStyle(sheet, cell, col, row);
		return cell;
	}

	/*
	 * 设置单元格格式
	 * 
	 * @param sheet 页对象
	 * @param value 值
	 * @param col 列
	 * @param row 行
	 */
	private void setCellStyle(WritableSheet sheet, WritableCell cell, int col, int row) {
		if (this.cellFormat != null) {
			cell.setCellFormat(this.cellFormat);
		} else if (this._cellFormat != null) {
			Cell template = sheet.getCell(_cellFormat.x < 0 ? col : _cellFormat.x, _cellFormat.y < 0 ? row : _cellFormat.y);
			if (template.getCellFormat() != null) {
				cell.setCellFormat(template.getCellFormat());
			}
		}
	}
}