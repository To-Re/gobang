package client;

import java.io.IOException;
import java.net.Socket;

public class Manual {
	public int[][] exist = new int[Config.ROWS][Config.COLUMNS];
	public String order;
	public int tot;
	private Socket socket = null;
	/**
     * 初始化棋盘
     */
	public void init() {
		order = "";
		tot = 0;
		for(int i = 0; i < Config.ROWS; ++i) {
			for(int j = 0; j < Config.COLUMNS; ++j) exist[i][j] = 0;
		}
	}
	public Manual(Socket wtf) {
		socket = wtf;
	}
	/**
     * 落子，从0开始记行列
     * 跳过落子 传入 Config.ROWS， Config.COLUMNS
     * @param x 第几行
     * @param y 第几列
     * @param who 谁下棋
     * @returns 落子成功返回1，失败返回0
     */
	public int move(int x, int y, int who) {
		try {
			String ax = String.valueOf((char)('a'+x));
			String ay = String.valueOf((char)('a'+y));
			System.out.println("1"+ax+ay);
			System.out.println("客户端发送下棋:"+"1"+ax+ay);
			Commun.send(socket, "1"+ax+ay);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if(x == Config.ROWS && y == Config.COLUMNS) {
//			order += 'a'+x;
//			order += 'a'+y;
//			return 1;
//		}
//		if(x < 0 || x >= Config.ROWS || y < 0 || y >= Config.COLUMNS || exist[x][y] != 0) return 0;
//		exist[x][y] = who;
//		System.out.println(x+" "+y+" "+who + " " + exist[x][y]);
//		order += "a"+x;
//		order += "a"+y;
		return 1;
	}
}
