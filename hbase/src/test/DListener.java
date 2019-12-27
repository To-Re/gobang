package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JTextField;

public class DListener implements MouseListener, ActionListener, MouseMotionListener {
	// 记录坐标
	int x1,x2,y1,y2;
	Color color =Color.BLACK;
	// 画布
	public Graphics g;
	//保存图形信息
	String shapeText="画线";
	//存储图形的数组
	
	int count = 0;
	//获取按钮的信息
	public void actionPerformed(ActionEvent e) {	   
		shapeText = e.getActionCommand();
		System.out.println(shapeText);
	    if(shapeText.equals("颜色选择器")) {
        JColorChooser Jcolor = new JColorChooser();
        color = Jcolor.showDialog(Jcolor, null, color);
        g.setColor(color);
     }
	}
	//构造方法
	public void mousePressed(MouseEvent e) {
    // 获取按下的坐标
		 x1 = e.getX();
		 y1 = e.getY(); 		 
	}
	public void mouseDragged(MouseEvent e) {
	    	if (shapeText.equals("画笔")){
	    		System.out.println("已经点击了按钮画线");
	    		x2 = e.getX();
	    		y2 = e.getY();
	    		g.drawLine(x1, y1, x2, y2);
	    		System.out.println("x1: "+x1+"y1: "+y1);
	    		x1 = x2;
	    		y1 = y2;
	    	}
	 }
	public void mouseReleased(MouseEvent e) {
		// 获取松开的坐标
		 x2 = e.getX();
		 y2 = e.getY();
		
	     if(shapeText.equals("直线"))
		 {
	    	 System.out.println("直线");
	    	  g.drawLine(x1, y1, x2, y2);
		 }
		 else if(shapeText.equals("矩形边"))
		 {
	   		 g.drawRect(Math.min(x1, x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
		 }
		 else if(shapeText.equals("矩形填充"))
		 {
	   		 g.fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
		 }
		 else if(shapeText.equals("椭圆边"))
		 {
	   		 g.drawOval(Math.min(x1, x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
		 }
		 else if(shapeText.equals("椭圆填充"))
		 {
	   		 g.fillOval(Math.min(x1, x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
		 }
		 else if(shapeText.equals("橡皮擦")){
			 Color color = new Color(238,238,238);	
	   			g.setColor(color);
	   			g.fillOval(Math.min(x1, x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1)); 
		 }
	}
	
	public void mouseMoved(MouseEvent e) {};
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {};
	public void mouseExited(MouseEvent e) {};

}
