package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;

public class Chess_listener extends MouseAdapter implements ActionListener{
	private Bd bd;
	private Control co;
	private Interface in;
	private Manual manual;
	private int x, y, hand = 1;
//	private Graphics g;
	private String Text;
	
	public Chess_listener(Bd wtf1, Control wtf2, Interface wtf3, Manual wtf4, int wtf5) {
		bd = wtf1;
		co = wtf2;
		in = wtf3;
		manual = wtf4;
		hand = wtf5;
//		g = bd.gr;
		for(JButton jb : co.List) jb.addActionListener(this);
	}
	/**
     * 得到鼠标位置
     */
	public void mouseClicked(MouseEvent e){			//鼠标点击事件的处理方法
		if(in.flag == 1) {
			x=e.getX();		//获取点击位置的x坐标
			y=e.getY();		//获取点击位置的y坐标
			System.out.println(x + "  " + y);
			pvp(y,x);
		}
	}
	public void pvp(int x,int y){
		if(manual.move((int)Math.round((1.0*x-Config.X0)/Config.SIZE), (int)Math.round((1.0*y-Config.Y0)/Config.SIZE), hand) == 0) {
			return;
		}
//		in.flag = 0;
//		bd.repaint();
	}
	/**
     * 监听按钮
     */
	public void actionPerformed(ActionEvent e) {
		Text = e.getActionCommand();
		System.out.println(Text);
		if(Text == "准备") {
			
		}
		else if(Text == "悔棋") {
			
		}
		else if(Text == "认输") {
			if(in.flag == 1) {
				try {
					Commun.send(in.socket, "2");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(Text == "离开") {
			
		}
		else if(Text == "导出棋谱") {
			
		}
    }
}
