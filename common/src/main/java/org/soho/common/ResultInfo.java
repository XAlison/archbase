package org.soho.common;

public class ResultInfo {
	private boolean flag;
	private String message;
	private String id;  //返回ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		if (this.message != null) {
			this.message = this.message.replaceAll("'", " ");
			this.message = this.message.replaceAll("\n", " ");
			this.message = this.message.replaceAll("\r", " ");
		}
	}
	
	public String getMessageByFlag(){
		String message="";
		if(getFlag()){
			message = "{'success':'true','id':'"+getId()+"','message':'"+getSuccessMsg()+"'}";
		} else {
			message = "{'success':'false','id':'"+""+"','message':'"+getErrorMsg(getMessage())+"'}";
		}
		return message;
	}
	
	
	public static String getSuccessMsg(){
		String message = "<br><br>恭喜你：</b>数据操作成功。没有出现错误<br><br><br>点击确定按纽将返回数据列表页面";
		return message;
	}
	
	public static String getErrorMsg(String errorMessage){
		String message = "<br><br>对不起：</b>数据操作失败.<br><br>" + errorMessage + "";
		return message;
	}
}
