package org.soho.utils.gendb;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbMetaGenDao {

	private Connection conn;
	
	public void init() throws SQLException{
		JdbcTemplateUtil tmpUtil =  new JdbcTemplateUtil();
		JdbcTemplate tmpl = tmpUtil.getJdbcTemplate();
		conn = tmpl.getDataSource().getConnection();
	}
	
/*	public static void main(String[] args) throws SQLException, IOException {
		DbMetaGenDao gen = new DbMetaGenDao();
		gen.init();
		gen.getTables();
	}*/
	
	public void getTables() throws SQLException, IOException{
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet set  =dbMeta.getTables(conn.getCatalog(), null, null, null);
		int colCnt = set.getMetaData().getColumnCount();
		while(set.next()){
			String tableName = null;
			for(int i=1; i <= colCnt; ++i){
				Object value = set.getObject(i);
				System.out.println(value);
				if(i==3){
					tableName = (String) value;
				}
			}
			dealTable(tableName);
		}
	}
	
	public  void dealTable(String tableName) throws SQLException, IOException{
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet set  = dbMeta.getColumns(conn.getCatalog(), null, tableName, null);
		int colCnt = set.getMetaData().getColumnCount();
		String className = StringUtil.getCamcelFieldName(tableName, true);
		BeanElement bean = new BeanElement();
		bean.setPackageName(GdaoConfig.getDaoPkg());
		bean.setTable(tableName);
		bean.setClassName(className);
		List<AttrColumn> pors = new ArrayList<AttrColumn>();
		while(set.next()){
			String colName = set.getString("COLUMN_NAME");
			String dataType = set.getString("TYPE_NAME");
			dataType = ColumnMetaMap.getJavaType(dataType);
			System.out.println(colName +"--"+dataType);
			pors.add(new AttrColumn(StringUtil.getCamcelFieldName(colName, false),colName,dataType));
		}
		bean.setAttrs(pors);
		GdGenDaoDeal genDao = new  GdGenDaoDeal();
		BeanGenDeal genBean  = new BeanGenDeal();
		genDao.genDao(bean);
		genBean.genBean(bean);
	}
}
