package client;

import database.*;
import server.Commun;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Interface extends JPanel {
	private String user_name;
	public Socket socket = null;
	private Manual manual = null;
	private int hand = 1;
	private Bd bd = null;
	private Control co = null;
	public int flag = 0;
	public void UI() {
		try {
			socket = new Socket(ServerInfo.Server_ip, ServerInfo.Server_port);
			Commun.send(socket, user_name);
			String str = Commun.receive(socket);
//			System.out.println(str + "!   " + (str == "1"));
			if(str.equals("1")) {
				hand = 1;
			}
			else hand = 2;
//			System.out.println(hand + "!!!!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		manual = new Manual(socket);
		this.setLayout(null);
		this.setSize(650, 750);
		this.setLocation(0, 0);
		bd = new Bd(manual, hand);
		this.add(bd, BorderLayout.CENTER);
		co = new Control(hand);
		this.add(co, BorderLayout.SOUTH);
		Chess_listener cl = new Chess_listener(bd,co,this,manual,hand);
		this.addMouseListener(cl);
	}
	private void begin() {
		while(true) {
			try {
				String str = Commun.receive(socket);
				System.out.println("下棋开局 "+str);
//				co.ta.revalidate();
				if(str.equals("1")) {
					// 请求落子
					bd.repaint();
					System.out.println("等待落子");
					co.ta.setText("请落子");
					co.ta.updateUI();
					flag = 1;

					String wtf = Commun.receive(socket);
					System.out.println("已落子 等待回复");
					if(wtf.equals("4")) {
						str = Commun.receive(socket);
						int x = str.charAt(0)-'a';
						int y = str.charAt(1)-'a';
						int c = str.charAt(2);
						System.out.println("服务端落子 "+x + " " + y + " "+c);
						manual.exist[x][y] = c;
						bd.repaint();
						flag = 0;
					}
					else if(wtf.equals("3")) {
						System.out.println("主动投降");
						bd.repaint();
						co.ta.setText("你输了");
						co.ta.updateUI();
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 你胜利了
						break;
					}
					else continue;
					co.ta.setText("等待");
					co.ta.updateUI();
				}
				else if(str.equals("2")) {
					bd.repaint();
					co.ta.setText("你赢了");
					co.ta.updateUI();
					// 你胜利了
					break;
				}
				else if(str.equals("3")) {
					bd.repaint();
					co.ta.setText("你输了");
					co.ta.updateUI();
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 你输了
					break;
				}
				else if(str.equals("4")) {
					str = Commun.receive(socket);
					int x = str.charAt(0)-'a';
					int y = str.charAt(1)-'a';
					int c = str.charAt(2);
					System.out.println("对方已落子" + x + " " + y);
					manual.exist[x][y] = c;
					bd.repaint();
					// 对手落子
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
     * 初始化
     * @param username 用户名
     */
	public Interface(String username) {
		user_name = username;
		System.out.println(user_name);
	}
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();

		frame.setLayout(null);
		frame.setTitle("五子棋");
		frame.setSize(650,750);		//设置大小
		frame.setResizable(false);	//大小不可变
		frame.setLocationRelativeTo(null);	//窗体居中
		frame.setDefaultCloseOperation(3);	//退出时关闭进程
		
//		Interface hello = new Interface("wzy");
//		Interface hello = new Interface("wy");
//		Interface hello = new Interface("id1");
//		Interface hello = new Interface("id2");
//		Interface hello = new Interface("id3");
		Interface hello = new Interface("id4");
		frame.add(hello);
		hello.UI();
		frame.setVisible(true);
		hello.begin();
	}
}
