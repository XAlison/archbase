package org.soho.utils.gendb;

import java.util.List;

public class BeanElement {
	private String packageName;
	private String className;
	private String table;
	private String idName;
	private List<AttrColumn> attrs;
	private String out;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public List<AttrColumn> getAttrs() {
		return attrs;
	}
	public void setAttrs(List<AttrColumn> attrs) {
		this.attrs = attrs;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	
	
}
