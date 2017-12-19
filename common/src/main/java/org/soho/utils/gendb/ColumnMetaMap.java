package org.soho.utils.gendb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnMetaMap {
	private static final Logger logger = LoggerFactory.getLogger(ColumnMetaMap.class);
	public static String getJavaType(String colTypeName) {
		colTypeName = colTypeName.toLowerCase();
		if (colTypeName.equalsIgnoreCase("char")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("varchar")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("varchar2")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("nvarchar")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("nvarchar2")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("tinytext")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("text")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("mediumtext")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("longtext")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("ntext")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("long")) {
			return "String";
		} else if (colTypeName.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (colTypeName.equalsIgnoreCase("number")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("numeric")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("decimal")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("money")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("real")) {
			return "Float";
		} else if (colTypeName.equalsIgnoreCase("Float")
				|| colTypeName.equalsIgnoreCase("Double")) {
			return "Double";
		} else if (colTypeName.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (colTypeName.equalsIgnoreCase("smallint")) {
			return "Integer";
		} else if (colTypeName.equalsIgnoreCase("tinyint")) {
			return "Integer";
		} else if (colTypeName.equalsIgnoreCase("date")) {
			return "Date";
		} else if (colTypeName.equalsIgnoreCase("smalldatetime")) {
			return "Date";
		} else if (colTypeName.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (colTypeName.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (colTypeName.equalsIgnoreCase("timestamp")
				|| colTypeName.startsWith("timestamp")) {
			return "Timestamp";
		} else if (colTypeName.equalsIgnoreCase("time")) {
			return "Time";
		} else if (colTypeName.equalsIgnoreCase("binary")
				|| colTypeName.equalsIgnoreCase("varbinary")) {
			return "Blob";
		}

		// 大字段
		else if (colTypeName.equalsIgnoreCase("tinyblob")
				|| colTypeName.equalsIgnoreCase("blob")
				|| colTypeName.equalsIgnoreCase("mediumblob")
				|| colTypeName.equalsIgnoreCase("longblob")) {
			return "Blob";
		} else if (colTypeName.equalsIgnoreCase("clob")) {
			return "Clob";
		} else {
			logger.info("DEV_LOG_____" + colTypeName + "找不到对应的JavaType");
		}
		return "String";
	}
}
