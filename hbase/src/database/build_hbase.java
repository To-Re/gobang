package database;

import java.io.IOException;

public class build_hbase {
	public static void main(String[] args) throws IOException {
		Mycon.open();
		Mycon.createTable("Agai", new String[]{"user_info","chess_manual"});
		Mycon.listTables();
		Mycon.insertRow("Agai", "admin", "user_info", "Round_play", "10");
		Mycon.insertRow("Agai", "admin", "user_info", "win", "6");
		Mycon.insertRow("Agai", "admin", "user_info", "escape", "2");
		Mycon.close();
	}
}
