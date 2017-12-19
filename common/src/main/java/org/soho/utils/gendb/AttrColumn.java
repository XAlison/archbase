package org.soho.utils.gendb;
public class AttrColumn {
	public String attr;
	public String column;
	public String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AttrColumn(String attr, String column, String type){
		this.attr=attr;
		this.column=column;
		this.type = type;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	

}
