package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Commun {
	/**
     * socket交互
     * @param tableName 表名
     * @throws IOException
     */
    public static void send(Socket wtf1, String msg) throws IOException {
    	OutputStream outputStream = null;
        PrintWriter printWriter = null;
    	outputStream = wtf1.getOutputStream();
        printWriter = new PrintWriter(outputStream);
        printWriter.println(msg);
        printWriter.flush();
    }
    public static String receive(Socket wtf1) throws IOException {
    	String str;
    	InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        
    	inputStream = wtf1.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        if ((str = bufferedReader.readLine()) != null) return str;
        return null;
    }
}


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class Commun {
//	/**
//     * socket交互
//     * @param tableName 表名
//     * @throws IOException
//     */
//    public static void send(Socket wtf1, String msg) throws IOException {
//    	OutputStream outputStream = null;
//        PrintWriter printWriter = null;
//    	outputStream = wtf1.getOutputStream();
//        printWriter = new PrintWriter(outputStream);
//        printWriter.println(msg);
//        printWriter.flush();
////        wtf1.shutdownInput();
//        wtf1.shutdownOutput();
//    }
//    public static String receive(Socket wtf1) throws IOException {
//    	String str;
//    	InputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader bufferedReader = null;
//        
//    	inputStream = wtf1.getInputStream();
//        inputStreamReader = new InputStreamReader(inputStream);
//        bufferedReader = new BufferedReader(inputStreamReader);
//        
//        if ((str = bufferedReader.readLine()) != null) {
//        	wtf1.shutdownInput();
////            wtf1.shutdownOutput();
//        	return str;
//        }
//        wtf1.shutdownInput();
////        wtf1.shutdownOutput();
//        System.out.println("未接收");
//        return null;
//    }
//}
