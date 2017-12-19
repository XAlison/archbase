package org.soho.utils.gendb;

public class StringUtil {
	public static String lowerFirst(String str){
		str=str.substring(0,1).toLowerCase()+str.substring(1);
		return str;
	}
	
	public static String upperFirst(String str){
		str=str.substring(0,1).toUpperCase()+str.substring(1);
		return str;
	}
	
	public static String getCamcelFieldName(String str, boolean firstUpper){
		StringBuilder sb = new StringBuilder();
		int in = str.indexOf("_", 0);
		int pre = 0;
		while(in!=-1){
			sb.append(upperFirst(str.substring(pre,in).toLowerCase()));
			pre = in+1;
			in = str.indexOf("_", in+1);
		}
		sb.append(upperFirst(str.substring(pre).toLowerCase()));
		if(firstUpper){
			return sb.toString();
		}else{
			return lowerFirst(sb.toString());
		}
	}
/*
	public static void  main(String args[]){
		System.out.println(lowerFirst("upsdf"));
		System.out.println(lowerFirst("UPSd"));
		
		System.out.println(upperFirst("upsdf"));
		System.out.println(upperFirst("UPSd"));
		System.out.println(getCamcelFieldName("UP_Sd",true));
	}
*/

}
