/**
 * 
 */
package com.sy.ShowSy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.alibaba.druid.util.StringUtils;

/** 
* @ClassName: Db2Entity 
* @Description: 数据表生成实体，驼峰命名
* @author albert
* @date 2017年2月27日 下午4:19:14 
*  
*/
public class Db2EntityUtil {
	
	/**
	 * mysql com.mysql.jdbc.Driver
	 * oracle oracle.jdbc.driver.OracleDriver
	 * sqlserver com.microsoft.sqlserver.jdbc.SQLServerDriver
	 * jdbc:sqlserver://192.168.1.200:1433;DatabaseName=middle_db
	 * jdbc:oracle:thin:@61.174.50.10:1521:orcl
	 */
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3307/portals?useUnicode=true&amp;characterEncoding=UTF-8";
	private static String user="root";
	private static String password="123456";
	/**包名*/
	private static String packageName = "com.sy.ShowSy.domain.";
	/**生成的java文件的路径*/
	private static String javaFilePath = "/Users/albert/Documents/workspace/Portals/src/main/java/com/sy/ShowSy/domain";
	/**以下面这些单词结尾的首字母大写*/
	private static String[] regs ={"id","status","amount","company","date","address","count","type","price","qty"}; 
	/**
	 * 
	 * @param tableName 表名
	 * @param entityName 生成的实体名，若为空；则根据表名生成
	 * @throws Exception
	 */
	public static String genEntity(String tableName,String packageName,String entityName) throws Exception{
		Connection conn = getConnection();
		ResultSet tableRet = conn.createStatement().executeQuery("select * from "+tableName);
		StringBuffer javaTxt = new StringBuffer();
		javaTxt.append("package "+ packageName +";");
		StringBuffer getSetter = new StringBuffer();
		javaTxt.append("\nimport java.util.Date;\nimport javax.persistence.Entity;\nimport javax.persistence.Table;\nimport com.sy.domain.EntityBase;");
		javaTxt.append("\n/**"+
					"\n * <p>Title: "+(entityName!=null?entityName:genJavaName(tableName))+"</p>"+
					"\n * <p>Description: auto generated </p>"+
					"\n * <p>Company: 湖州双翼信息技术有限公司</p>"+
					"\n * @author AlbertZhang"+
					"\n * @date	"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
					"\n * @version 1.0"+
					"\n */");
		javaTxt.append("\n@Entity");
		javaTxt.append("\n@Table(name=\""+tableName+"\")");
		javaTxt.append("\npublic class "+(entityName!=null?entityName:genJavaName(tableName))+" implements EntityBase {");
		javaTxt.append("\n\tprivate static final long serialVersionUID = "+new Random(Long.MAX_VALUE).nextLong()+"L;");
		ResultSetMetaData meta = tableRet.getMetaData();
		for(int i=1;i<=meta.getColumnCount();i++){
			String attrName = camelCase(meta.getColumnName(i));
			javaTxt.append("\n\t/**"+
							"\n\t * "+
							"\n\t */");
			javaTxt.append("\n\t"+dbType2JavaType(meta.getColumnTypeName(i)+(meta.getScale(i)>0?"(":""))+attrName+";");
			getSetter.append(genGetSetter(dbType2JavaType(meta.getColumnTypeName(i)+(meta.getScale(i)>0?"(":"")).replace("private", "").trim(), attrName));
		}
		return javaTxt.toString()+getSetter.toString()+"\n}";
	}
	
	public static Connection getConnection() throws Exception{
		Class.forName(driverName).newInstance(); 
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
	/**
	 * 驼峰命名
	 * @param col
	 * @return
	 */
	public static String camelCase(String col) {
		String s = col.toLowerCase();
		for(String reg : regs){
			reg = reg.toLowerCase();
			if(s.endsWith(reg) && !reg.equals(s)){
				s = s.replace(reg, "") + toUpperFirstChar(reg);
				break;
			}
		}
		return s;
	}
	/**database column type转换成java 类型*/
	public static String dbType2JavaType(String type) {
		String str = null;
		type = type.toLowerCase();
		if(type.contains("char")){
			str = "private String ";
		}else if(type.equals("number") || type.contains("long") || type.contains("int")){
			str = "private Long ";
		}else if(type.contains("date") || type.contains("time")){
			str = "private Date ";
		}else if(type.contains("number(") || type.contains("decimal") || type.contains("numeric")){
			str = "private Double ";
		}else{
			str = "private String ";
		}
		return str;
	}
	/**
	 * 由表名生成类名
	 * @param tableName
	 * @return
	 */
	public static String genJavaName(String tableName){
		String name = tableName.toLowerCase();
		if(name.endsWith("_t")){
			name = name.substring(0, name.length()-2);
		}
		if(name.contains("_")){
			int index = name.indexOf("_");
			name = name.replaceFirst("_", "");
			name = name.substring(0,index)+toUpperFirstChar(name.substring(index));
		}
		return toUpperFirstChar(name);
	}
	/**
	 * 首字母大写
	 * @param param
	 * @return
	 */
	public static String toUpperFirstChar(String param){
		return param.substring(0, 1).toUpperCase()+param.substring(1);
	}
	/**
	 * 生成getter setter
	 * @param className
	 * @param attr
	 * @return
	 */
	public static String genGetSetter(String className,String attr){
		StringBuffer sb = new StringBuffer("\n\tpublic void set"+toUpperFirstChar(attr)+"("+className+" "+attr+"){");
		sb.append("\n\t\tthis."+attr+" = "+attr+";");
		sb.append("\n\t}");
		
		sb.append("\n\tpublic "+className+" get"+toUpperFirstChar(attr)+"(){");
		sb.append("\n\t\treturn "+ attr +";");
		sb.append("\n\t}");
		return sb.toString();
	}
	
	public static void genJavaFile(String javaTxt,String className) throws Exception{
		File file = new File(javaFilePath);
		if(!file.exists() || !file.isDirectory()) throw new Exception(javaFilePath+"路径不存在");
		file = new File(javaFilePath+"/"+className+".java");
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8")); 
		fw.append(javaTxt);
		fw.flush();
		fw.close();
	}
	/**
	 * start method
	 * @throws Exception 
	 */
	public static void generate() throws Exception{
		String tname;
		String entityName;
		System.out.println("输入表名并回车：");
		Scanner sc = new Scanner(System.in);   
		tname = sc.nextLine();
		System.out.println("输入实体名并回车(可不输入，按照表名自动生成)：");
		Scanner sc2 = new Scanner(System.in);
		entityName = sc2.nextLine();
		System.out.println("正在生成，请稍后...");
		if(StringUtils.isEmpty(entityName)) entityName = null;
		genJavaFile(genEntity(tname,packageName,entityName), entityName!=null?entityName:genJavaName(tname));
		System.out.println("java对象成功生成");
	}
	public static void main(String[] args) {
		try {
			generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
