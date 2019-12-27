package test;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NWDrawUI extends JFrame	{
	DListener lis1 = new DListener();
	public static void main(String[] args){
		NWDrawUI nw = new NWDrawUI();
		nw.showUI();
	}
	public void showUI(){
		this.setSize(1000,700);
		//创建窗体位置居中
		this.setLocationRelativeTo(null);
		//创建窗体关闭时的操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//创建窗体的标题
		this.setTitle("画图板界面-3.0");
		//创建布局
		this.setLayout(new FlowLayout());
		String[] textArr = {"画线","直线","矩形边","椭圆边","矩形填充","椭圆填充","橡皮擦","颜色选择器"};
		for(int i=0;i<textArr.length;i++){
			//创建按钮
			JButton btn = new JButton(textArr[i]);
			btn.setSize(50,50);
			//添加按钮
			this.add(btn);
			//添加监听器
			btn.addActionListener(lis1);
		}
		this.setVisible(true);
		Graphics g = this.getGraphics();
		//给监听器的画布对象赋值
	      lis1.g = g;
	      this.addMouseListener(lis1);
	}
}
