package client;

public interface Config {
	/**
     * X0 第0行位置
     * Y0 第0列位置
     * ROWS 行数
     * COLUMNS 列数
     * CHESS_SIZE 棋子大小
     * SIZE 棋盘间距
     * FIR 先手
     * SEC 后手
     */
	public static final int X0=45;
	public static final int Y0=45;  //棋盘起点坐标y0
	public static final int ROWS=15;  //行数
	public static final int COLUMNS =15;  //列数
	public static final int CHESS_SIZE=40;   //棋子的大小
	public static final int SIZE=40;      //棋盘行与行  列与列之间的距离
	public static final int FIR = 1;
	public static final int SEC = 2;
}
