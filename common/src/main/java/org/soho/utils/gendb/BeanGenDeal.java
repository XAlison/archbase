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

public class BeanGenDeal {

	public static void main(String[] args) throws IOException {
		File file = new File(Constants.GDAO);
		BeanGenDeal gen = new BeanGenDeal();
		gen.deal(file);
	}

	public void deal(File file) {
		File[] files = file.listFiles();
		for (File file1 : files) {
			System.out.println(file1.getAbsolutePath());
			String filename = file1.getName();
			if (filename.endsWith(".gdao")) {
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

	public void dealXml(File file) throws Exception {
		//
		BufferedReader br = new BufferedReader(new FileReader(file));
		String packageName = br.readLine();
		String className = (br.readLine());

		System.out.println(className);

		List<AttrColumn> pors = new ArrayList<AttrColumn>();
		String line = br.readLine();
		while (line != null) {
			pors.add(stringToAc(line));
			line = br.readLine();
		}
		BeanElement bean = new BeanElement();
		bean.setClassName(className);
		bean.setPackageName(packageName);
		bean.setAttrs(pors);
		genBean(bean);

	}

	public void genBean(BeanElement bean) throws IOException {
		String packageName = bean.getPackageName();
		String className = bean.getClassName();
		List<AttrColumn> pors = bean.getAttrs();
		File df = new File(Constants.GDAO
				+ className + ".java");
		df.createNewFile();

		System.out.println(df.getAbsolutePath());
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(df), "utf-8")));

		out.println("package " + packageName + ";");
		out.println("import java.util.*;");

		out.println("public class " + className + "{");

		for (int i = 0; i < pors.size(); i++) {
			AttrColumn ac = pors.get(i);
			out.println("\tprivate " + ac.getType() + " "
					+ StringUtil.lowerFirst(ac.attr) + ";");
		}

		out.println("");

		for (int i = 0; i < pors.size(); i++) {
			AttrColumn ac = pors.get(i);
			// get
			StringBuffer getb = new StringBuffer("\tpublic ");

			getb.append(ac.type).append(" ").append("get")
					.append(StringUtil.upperFirst(ac.attr)).append("(")
					.append(")").append("{").append("\r\n");
			;
			getb.append("\t\treturn ").append(" this.")
					.append(StringUtil.lowerFirst(ac.attr)).append(";\r\n");
			getb.append("\t}").append("\r\n");
			out.println(getb.toString());
			// set
			StringBuffer setb = new StringBuffer("\tpublic void set");
			setb.append(StringUtil.upperFirst(ac.attr)).append("(")
					.append(ac.type).append(" ")
					.append(StringUtil.lowerFirst(ac.attr)).append(")")
					.append("{").append("\r\n");
			setb.append("\t\tthis.").append(StringUtil.lowerFirst(ac.attr))
					.append("=").append(StringUtil.lowerFirst(ac.attr))
					.append(";\r\n");
			setb.append("\t}").append("\r\n");

			out.println(setb.toString());
		}
		out.println("}");

		out.close();

	}

	public static AttrColumn stringToAc(String str) {
		String[] acp = str.split(",");
		AttrColumn ac = new AttrColumn(acp[1], acp[0], acp[2]);
		return ac;
	}

}
