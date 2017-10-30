package bearsCream;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bear {
	public Image bear = new ImageIcon(this.getClass().getResource("/play_Bear_normal.png")).getImage();
	
	public int look=0;
	
	public void BearDraw(Graphics g){
		if(look>=1) look=1;
		else if(look<=-2) look=-2;
		if(look==0){
			bear = new ImageIcon(this.getClass().getResource("/play_Bear_normal.png")).getImage();
		}else if(look==1){
			bear = new ImageIcon(this.getClass().getResource("/play_Bear_happy.png")).getImage();
		}else if(look==-1){
			bear = new ImageIcon(this.getClass().getResource("/play_Bear_sad.png")).getImage();
		}else if(look==-2){
			bear = new ImageIcon(this.getClass().getResource("/play_Bear_tears.png")).getImage();//울기로 바꾸기
		}
		g.drawImage(bear,650,350,232,290,null);
	}
}
