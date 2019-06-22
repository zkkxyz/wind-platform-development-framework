package com.wind.common.utils.export.csv;



import com.wind.common.utils.export.DataField;
import com.wind.common.utils.export.ExportDataSource;
import com.wind.common.utils.export.txt.TxtDataExportor;

import java.io.OutputStream;


/**
 * 描述: csv格式数据导出工具
 * @author linxiaoqing
 *
 */
public class CsvDataExportor<T> extends TxtDataExportor<T> {
	public CsvDataExportor(DataField[] fields, ExportDataSource<T> dataSource, OutputStream os) {
		super(fields, dataSource, os,",");
	}
}
