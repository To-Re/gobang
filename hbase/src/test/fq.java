package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import database.ServerInfo;
import server.Commun;
import server.ServerThread;

public class fq {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("121.36.59.137", 6000);
			Commun.send(socket, "hanpi");
			String str = Commun.receive(socket);
			System.out.println(str);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
