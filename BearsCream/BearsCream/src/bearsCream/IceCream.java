package bearsCream;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class IceCream extends Thread{

	int i_random=(int)(Math.random()*15)+1; //���̽�ũ�� �̹��� ����
	
	Image icecreamimg=new ImageIcon(this.getClass().getResource("/icecream"+i_random+".png")).getImage();
	
	int x1,y1=100,x2,y2;
	int x_distance; //�� ���̽�ũ���� x�Ÿ�
	int size;
	int speed;
	
	int b_x;//�̰� �� ���� x��ǥ
	
	private boolean proceeded=true; //���̽�ũ���� ���࿩��
	private boolean i_catch=false; //�޾Ҵ��� ���޾Ҵ���
	
	public void setX(int x1) {
		this.x1 = x1;
	}
	
	public void setY(int y2) {
		this.y2 = y2;
	}

	public boolean isProceeded(){
		return proceeded;
	}
	
	public boolean isCatch(int y1,int x1,int x2){
		int okay=this.size/2;
		if((this.y2<=y1&&this.y2>=(y1-10))){ // Ȯ�� ���� ����
			if((this.x2-x1)>=okay&&(x2-this.x1)>=okay){ //���̽�ũ���� ���������� ���������� �޾�����
				i_catch=true;
			}
//			i_catch=true;
		}
		return i_catch;
	}
	
	public void close(){
		proceeded=false;
	}
	
	public IceCream(int size, int speed){
		this.size=size;
		this.speed=speed;
		x1=(int)(Math.random()*880)+20;
		x2=x1+size;
	}
	
	public void iceCreamDraw(Graphics g){
		g.drawImage(icecreamimg,x1,y1,size,(size/5)*4,null);
	}
	
	public void drop(){
		y1+=10;
		y2=y1+(size/5*4);
		if(i_catch){
			close();
		}
	}
	
	public void run(){
		try{
			while(true){
				drop();
				if(proceeded){
					Thread.sleep(speed);
				}else{
					interrupt();
					break;
				}
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}