package client;

import java.awt.*;
import javax.swing.*;

public class Bd extends JPanel{
	int x, y;
	public Manual manual;
	int hand;
	Graphics gr;
	public Bd(Manual wtf1, int wtf2) {
		this.setBackground(Color.orange);
		this.setSize(650, 650);
		this.setLocation(0, 0);
		manual = wtf1;
		hand = wtf2;
	}
	
	// 画棋盘
	public void paint(Graphics g) {
		super.paint(g);
		chesspaint(g);
		reDrawChess(g);
	}
	public void chesspaint(Graphics g) {
		g.setColor(Color.black);
		for(int r=0;r<Config.ROWS;r++){
			g.drawLine(Config.Y0, Config.X0+r*Config.SIZE,
						Config.Y0+(Config.COLUMNS-1)*Config.SIZE, Config.X0+r*Config.SIZE);
		}
		for(int c=0;c<Config.COLUMNS;c++){
			g.drawLine(Config.Y0+Config.SIZE*c,Config.X0,
					Config.Y0+Config.SIZE*c, Config.X0+(Config.ROWS-1)*Config.SIZE);
		}
		g.fillOval(Config.Y0+3*Config.SIZE-Config.SIZE/8, Config.X0+3*Config.SIZE-Config.SIZE/8, Config.SIZE/4, Config.SIZE/4); // 左上
		g.fillOval(Config.Y0+11*Config.SIZE-Config.SIZE/8, Config.X0+3*Config.SIZE-Config.SIZE/8, Config.SIZE/4, Config.SIZE/4); // 右上
		g.fillOval(Config.Y0+3*Config.SIZE-Config.SIZE/8, Config.X0+11*Config.SIZE-Config.SIZE/8, Config.SIZE/4, Config.SIZE/4); // 左下
		g.fillOval(Config.Y0+11*Config.SIZE-Config.SIZE/8, Config.X0+11*Config.SIZE-Config.SIZE/8, Config.SIZE/4, Config.SIZE/4); // 右下
		g.fillOval(Config.Y0+7*Config.SIZE-Config.SIZE/8, Config.X0+7*Config.SIZE-Config.SIZE/8, Config.SIZE/4, Config.SIZE/4); // 中
	}
	
	public void reDrawChess(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int  r=0;r<Config.ROWS;r++){
			for(int c=0;c<Config.COLUMNS;c++){
				if(manual.exist[r][c]!=0){
					if(manual.exist[r][c]==Config.FIR){
						g2d.setColor(Color.BLACK);
						g2d.fillOval(Config.Y0+c*Config.SIZE-Config.CHESS_SIZE/2,Config.X0+r*Config.SIZE-Config.CHESS_SIZE/2 , Config.CHESS_SIZE, Config.CHESS_SIZE);
					}else if(manual.exist[r][c]==Config.SEC){
						g2d.setColor(Color.WHITE);
						g2d.fillOval(Config.Y0+c*Config.SIZE-Config.CHESS_SIZE/2,Config.X0+r*Config.SIZE-Config.CHESS_SIZE/2 , Config.CHESS_SIZE, Config.CHESS_SIZE);
					}
				}
			}
		}
	}
}
