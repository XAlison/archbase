package org.soho.utils.gendb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class HbmXmlDeal {
	
	
/*	public static void main(String[] args) throws IOException {
		File file = new File(Constants.GDAO);
		HbmXmlDeal deal = new HbmXmlDeal();
		deal.deal(file);
	}
	*/
	public void  deal(File file){
		File[] files = file.listFiles();
		for (File file1 : files) {
			System.out.println(file1.getAbsolutePath());
				String filename = file1.getName();
				if(filename.endsWith(".gdao")){
					System.out.println("deal....");
					try {
						dealXml(file1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}
	
	public  void  dealXml(File file) throws Exception{
		      //
			BufferedReader br = new BufferedReader(new FileReader(file));
			 String packageName =br.readLine();
			 String table =br.readLine();
			String className = (br.readLine());
			
		    System.out.println(className);
		    
		      
		      List<AttrColumn> pors = new  ArrayList<AttrColumn> ();
		      String line = br.readLine();
		      while(line!=null){
		    	  pors.add(stringToAc(line));
		    	  line = br.readLine();
		      }
		      
		      String pk = pors.get(0).getAttr();
		      String pkcolumn =pors.get(0).getColumn();
		      String pkType = pors.get(0).getType();
		      String idname = pors.get(0).getAttr();
		      String column = pors.get(0).getColumn();
		      String type =  pors.get(0).getType();
		      
			File df = new File(Constants.GDAOPATH + className + "Dao.java");
			df.createNewFile();

			System.out.println(df.getAbsolutePath());
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(df), "utf-8")));
			
			out.println("package "+packageName+";");
			
			//import 
			out.println("import java.sql.ResultSet;");
			out.println("import java.sql.SQLException;");
			out.println("import org.springframework.jdbc.core.SingleColumnRowMapper;");
			out.println("import com.ebwing.bean.*;");
			
			out.println("public class "+className +"Dao extends BaseDao<"+className+","+StringUtil.upperFirst(pkType)+">{");
		      	
		      	


		      	
			out.println("\tpublic "+className+"Dao(){");
			out.println(" 		TABLE_NAME=\""+table+"\";");
		      		
			out.println(" 		PK_COLUMN =\""+pkcolumn+"\";");

			out.println("  		QUERY_ALL = \"SELECT * FROM \"+TABLE_NAME;");

			out.println("  		QUERY_ALL_COUNT = \"SELECT COUNT(*) FROM \"+  TABLE_NAME;");
			
			StringBuffer sb =new  StringBuffer("\t\tINSERT_SQL = \"INSERT  INTO \"+TABLE_NAME+\" (");
			StringBuffer sbc=new  StringBuffer("");
			StringBuffer sba=new  StringBuffer("");
			

			for(int i=0; i<pors.size();i++){
				AttrColumn ac = pors.get(i);
				if(i!=0){
					sbc.append(",");
					sba.append(",");
				}
				sbc.append(ac.column);
				sba.append(":"+StringUtil.lowerFirst(ac.attr));
			
			}
			
			sb.append(sbc).append(")VALUES(").append(sba).append(")\";");
		      		
			out.println(sb.toString());
			sb=new  StringBuffer(" 		DELETE_SQL = \"DELETE FROM \"+").append("TABLE_NAME").append("+\" WHERE ").append(pk).append("=?\";");
			out.println(sb.toString());
		      		
			
			sb=new  StringBuffer(" 		UPDATE_SQL = \"UPDATE \"+ TABLE_NAME+\" SET ");
			
			boolean  firstColumn = true;
			for(int i=0; i<pors.size();i++){
				AttrColumn ac = pors.get(i);
				if(ac.attr.equals(pk)){
					continue;
				}
				if(firstColumn==false){
					sb.append(",");
				}else{
					firstColumn=false;
				}
				sb.append(ac.column).append("=:").append(StringUtil.lowerFirst(ac.attr));
			}
			sb.append(" where ");
			sb.append(pkcolumn).append("=:").append(StringUtil.lowerFirst(pk)).append("\";");

		      		
			out.println(sb.toString());

			
			sb =new  StringBuffer(" 		mapper = new SingleColumnRowMapper<").append(className).append(">() {\n");
			sb.append("\t\t\t\tpublic ").append(className).append("  mapRow(ResultSet rs, int rowNum) throws SQLException { \n");
			sb.append("\t\t\t\t\t").append(className).append(" ").append(StringUtil.lowerFirst(className)).append(" =  new ").append(className).append("();\n");
			String bean = StringUtil.lowerFirst(className);

			
			for( int i=0; i<pors.size();i++){
				AttrColumn ac = pors.get(i);
				column = ac.getColumn();
				type=ac.type;
				String attr = ac.attr;
				
				sb.append("\t\t\t\t\t").append(bean).append(".set").append(StringUtil.upperFirst(attr)).append("(rs.get");
				if(type.equalsIgnoreCase("integer")){
					sb.append(StringUtil.upperFirst("int"));
				}else{
					sb.append(StringUtil.upperFirst(type));
				}
				sb.append("(\"").append(column).append("\"));\n");
				
			}
			
			sb.append("\t\t\t\t\t").append("return ").append(bean).append(";\n");
			
			out.println(sb.toString());
			
			out.append("\t\t\t").println("}");
			out.append("\t\t").println("};");
			out.append("\t").println("}");
			out.append("").println("}");
			
			out.close();

 
		
	}
	

	public  AttrColumn stringToAc(String str){
		String[] acp = str.split(",");
		AttrColumn ac = new AttrColumn(acp[1],acp[0],acp[2]);
		return ac;
	}
	
	
	
	
	

}
