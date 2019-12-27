package hbase;

import java.io.IOException;
import database.Mycon;
/*
 * open
 * close
 * createTable
 * deleteTable
 * listTables
 * 
 * insertRow
 * deleteRow
 * getData
 * 
 * */
public class test{
	public static void main(String[] args) throws IOException {
		Mycon.open();
//		Mycon.createTable("student", new String[]{"name","age"});
//		Mycon.createTable("test", new String[]{"name","age"});
//		Mycon.listTables();
		Mycon.deleteTable("student");
//		Mycon.deleteTable("fq");
		Mycon.listTables();
//		Mycon.insertRow("student", "2", "name", "", "wzy");
//		Mycon.insertRow("student", "1", "name", "", "fq");
//		System.out.println(Mycon.getData("student", "1", "name", ""));
//		System.out.println(Mycon.getData("student", "2", "name", ""));
//		Mycon.deleteRow("student", "2", "name", "");
//		System.out.println(Mycon.getData("student", "2", "name", ""));
		Mycon.close();
	}
}