package client;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Control extends JPanel{
	ArrayList<JButton> List = new ArrayList<JButton>();
	JTextField ta = new JTextField();
	private int hand;
	public Control(int wtf1) {
		hand = wtf1;
		this.setBackground(Color.white);
		this.setSize(650, 100);
		this.setLocation(0, 650);
		this.setLayout(null);
		String[] textArr = {"准备","悔棋","认输","离开","导出棋谱"};
		for(int i=0;i<textArr.length;i++){
			//创建按钮
			JButton btn = new JButton(textArr[i]);
			btn.setSize(100,50);
			btn.setLocation(105*i+2, 15);
			//添加按钮
			this.add(btn);
			List.add(btn);
		}
		if(hand == 1) ta.setText("你先行");
		else ta.setText("你后行");
		ta.setSize(100, 50);
		ta.setLocation(105*5+5, 15);
		ta.setEditable(false);
//		ta.sets
//		ta.setWrapStyleWord (true);
		this.add(ta);
		this.setVisible(true);
	}
}
