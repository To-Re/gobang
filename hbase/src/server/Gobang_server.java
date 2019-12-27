package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import database.*;

public class Gobang_server {
	private static Socket socket = null;
    private static Map<String, Socket> map = new HashMap<String, Socket>();
    private static Map<String, String> oppo = new HashMap<String, String>();
    private static Stack<String> stack = new Stack<String>();
    static int count = 0;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(ServerInfo.Server_port);
		try {
			System.out.println("开始服务");
            // 处理socket请求
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, map, oppo, stack, count);
                serverThread.start();
                count++;
                System.out.println("now client count is: " + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	serverSocket.close();
        }
	}
}
