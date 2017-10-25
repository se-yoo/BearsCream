package bearsCream;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Cornet {
	public Image cornet = new ImageIcon(this.getClass().getResource("/cornet.png")).getImage();
	
	public int x;
	public int y;

	public Cornet(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void Left(){
		if(x>10){
			x=x-30;
		}
	}
	
	public void Right(){
		if(x<870){
			x=x+30;
		}
	}
}
