package game;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.*;


/**
 * @author Sergio
 *
 *  11.05.2016
 */
public class Pyatnashki extends JFrame {
	
	private JPanel panel = new JPanel(new GridLayout(4,4,2,2));
	private int [][] numbers = new int[4][4];

	
	public Pyatnashki(){
		
		super("Pyatnashki by Serhio ");
		setBounds(200,200,300,300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		createMenu();
		
		Container container = getContentPane();
		panel.setDoubleBuffered(true);
		container.add(panel);
		
		generate();
		repaintField();
	}
	
	
	
	public void generate(){
		Random generator = new Random();
		int[] invariants = new int[16];
		
		do{
			for(int i=0;i<4; i++){
				for(int j=0; j<4; j++){
					numbers[i][j] = 0;
					invariants[i*4+j]=0;
					
				}
			}
			
			for(int i=1; i<16;i++){
				int k,l;
				do{
					k=generator.nextInt(4);
					l=generator.nextInt(4);
					
				}while(numbers[k][l] !=0);
				numbers[k][l] =i;
				invariants[k*4+1]=i;
			}
		}while(!canBeSolved(invariants));
	}
	
	private boolean canBeSolved(int [] invariants){
		
		int sum =0;
		for(int i=0; i<16; i++){
			if(invariants[i] ==0){
				sum += i/4;
				continue;
			}
			for(int j=i+1;j<16;j++){
				if(invariants[j] < invariants[i])
					sum++;
				
			}
		}
		System.out.println(sum%2 ==0);
		return sum%2 ==0;
	}
	
	public void repaintField(){
		panel.removeAll();
		for(int i=0;i<4;i++){
			for(int j =0;j<4;j++){
				JButton button = new JButton(Integer.toString(numbers[i][j]));
				button.setFocusable(false);
				panel.add(button);
				if(numbers[i][j]==0){
					button.setVisible(false);
				}else button.addActionListener(new ClickListener(this));
			}
		}
		panel.validate();
		panel.repaint();
	}
	
	public boolean checkWin(){
		
		boolean status = true;
		for(int i=0; i<4;i++){
			for(int j=0;j<4;j++){
				if(i==3 && j>2) break;
				if(numbers[i][j] !=i*4+j+1){
					status = false;
				}
			
			}
		}
			return status;
	}
	
	
	public void createMenu(){
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		for (String fileItem : new String[] {"New", "Exit"}){
			JMenuItem item = new JMenuItem(fileItem);
			item.setActionCommand(fileItem.toLowerCase());
			item.addActionListener(new NewMenuListener(this));
			fileMenu.add(item);
			
		}
		fileMenu.insertSeparator(1);
		
		menu.add(fileMenu);
		setJMenuBar(menu);
	}
	
	 public void change(int num) {
	        int i = 0, j = 0;
	        for (int k = 0; k < 4; k++) {
	            for (int l = 0; l < 4; l++) {
	                if (numbers[k][l] == num) {
	                    i = k;
	                    j = l;
	                }
	            }
	        }
	        if (i > 0) {
	            if (numbers[i - 1][j] == 0) {
	                numbers[i - 1][j] = num;
	                numbers[i][j] = 0;
	            }
	        }
	        if (i < 3) {
	            if (numbers[i + 1][j] == 0) {
	                numbers[i + 1][j] = num;
	                numbers[i][j] = 0;
	            }
	        }
	        if (j > 0) {
	            if (numbers[i][j - 1] == 0) {
	                numbers[i][j - 1] = num;
	                numbers[i][j] = 0;
	            }
	        }
	        if (j < 3) {
	            if (numbers[i][j + 1] == 0) {
	                numbers[i][j + 1] = num;
	                numbers[i][j] = 0;
	            }
	        }

	        repaintField();

	        if (checkWin()) {
	            JOptionPane.showMessageDialog(null, "YOU WIN!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
	            generate();
	            repaintField();
	        }
	    }
	
}
