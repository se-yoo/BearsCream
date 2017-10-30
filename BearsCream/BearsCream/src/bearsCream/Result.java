package bearsCream;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Result {

	ArrayList<RankPlayer> rank = new ArrayList<RankPlayer>(10); 
	int index=0;
	int playerscore=0;
	String playername=null;
	private ImageIcon inputButtonImg = new ImageIcon(this.getClass().getResource("/inputButtonImg.png"));
	
	public Result(int ps){
		this.playerscore=ps;
	}
	
	public void readFile(){
		String name;
		int score;
		try {
			String csvStr="";
			String tmpStr="";
			FileReader fr=new FileReader(new File("score.txt"));
			BufferedReader br=new BufferedReader(fr);
			do{
				tmpStr=br.readLine();
				if(tmpStr!=null){
					csvStr+=tmpStr+"         ";
				}
			}while(tmpStr!=null);
			
			StringTokenizer parse=new StringTokenizer(csvStr,"         ");
			for(int i=0;i<10;i++){
				name=parse.nextToken();
				score=Integer.valueOf(parse.nextToken());
				rank.add(new RankPlayer(name,score));
			}
		}catch(FileNotFoundException e){
			System.out.println("잘못된 파일 이름");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean compareRank(){
		for(int i=0;i<10;i++){
			if(rank.get(i).score<playerscore){
				index=i;
				return true;
			}
		}
		return false;
	}
	
	public void inputName(){
		JFrame jf=new JFrame();
		jf.setTitle("Input Name");
		jf.setSize(300, 130);
		JPanel jp=new JPanel(){
	           public void paintComponent(Graphics g) {
	               g.drawImage(new ImageIcon(this.getClass().getResource("/inputBackground.jpg")).getImage(), 0, 0, 300 ,100, null);
	               setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	               super.paintComponent(g);
	               this.repaint();
	           }
	     };
	     jp.setLayout(null);
		
		JLabel jl=new JLabel(String.valueOf(index+1));
		jl.setForeground(Color.WHITE);
		jl.setBounds(10,5,20,20);
		
		jp.add(jl);
		
		JTextField nameinput=new JTextField(10);
		nameinput.setBounds(10,30,200,34);
		
		jp.add(nameinput);
		
		JButton inputButton=new JButton(inputButtonImg);
		
		inputButton.setBounds(225,30,58,36);
		inputButton.setBorderPainted(false); // 버튼 테두리 없애기
		inputButton.setContentAreaFilled(false);// 버튼 배경없애기
		inputButton.setFocusPainted(false); // 버튼 안에 이미지 테두리 없애기
		inputButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				playername=nameinput.getText();
				jf.setVisible(false);
				insertInfo();
			}
		}); 
		
		jp.add(inputButton);

		jf.add(jp);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}
	
	public void insertInfo(){
		for(int i=9;i>index;i--){
			rank.get(i).name=rank.get(i-1).name;
			rank.get(i).score=rank.get(i-1).score;
		}
		rank.get(index).name=playername;
		rank.get(index).score=playerscore;
		writeFile();
	}
	
	public void writeFile(){
		FileWriter fw=null;
		BufferedWriter bw=null;
		try{
			fw=new FileWriter(new File("score.txt"));
			bw=new BufferedWriter(fw);
			
			for(int i=0;i<10;i++){
				bw.write(rank.get(i).name+"         "+rank.get(i).score);
				bw.newLine();
			}
			bw.close();
			fw.close();
		}catch(FileNotFoundException e){
			System.out.println("잘못된 파일 이름");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
