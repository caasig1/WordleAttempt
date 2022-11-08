import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Keyboard implements KeyListener {
	
	private JFrame frame;
	private double swid;
	private double shei;
	private JButton[] row1s;
	private JButton[] row2s;
	private JButton[] row3s;
	private JButton del;
	private JButton ent;
	private GameManager gm;
	
	public Keyboard(JFrame f, int w, int h, int a, GameManager gm) {
		this.gm = gm;
		this.frame = f;
		this.swid = w;
		this.shei = h;		
		this.row1s = new JButton[10];
		this.row2s = new JButton[9];
		this.row3s = new JButton[7];
		this.del = new JButton("DELETE");
		this.ent = new JButton("ENTER");
		
		frame.addKeyListener(this);
		frame.setFocusable(true);
		drawKeyboard();
	}
	
	
	public void drawKeyboard() {
		double gap = swid/51;
		double width = gap*4;
		double height = (shei - swid - gap*4)/3;
		
		String[] row1 = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
		for	(int i = 0; i < row1.length; i++) {
			JButton letter = new JButton(row1[i]);
			int xcoord = (int) ((i+1)*gap + (i)*width);
			int ycoord = (int) (swid+gap);
			letter.setBounds(xcoord, ycoord, (int) width, (int) height);
			letter.addKeyListener(this);
			letter.setFocusable(true);
			letter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					gm.updateWord(letter);
				}
			});
			frame.add(letter);
			row1s[i] = letter;
		}
		
		String[] row2 = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
		for	(int i = 0; i < row2.length; i++) {
			JButton letter = new JButton(row2[i]);
			int xcoord = (int) ((i+1)*gap + (i)*width + width/2);
			int ycoord = (int) (swid+2*gap+height);
			letter.setBounds(xcoord, ycoord, (int) width, (int) height);
			letter.addKeyListener(this);
			letter.setFocusable(true);
			letter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					gm.updateWord(letter);
				}
			});
			frame.add(letter);
			row2s[i] = letter;
		}
		
		String[] row3 = {"Z", "X", "C", "V", "B", "N", "M"};
		for	(int i = 0; i < row3.length; i++) {
			JButton letter = new JButton(row3[i]);
			int xcoord = (int) ((i+1)*gap + (i)*width + 1.75*width);
			int ycoord = (int) (swid+3*gap+2*height);
			letter.setBounds(xcoord, ycoord, (int) width, (int) height);
			letter.addKeyListener(this);
			letter.setFocusable(true);
			letter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					gm.updateWord(letter);
				}
			});
			frame.add(letter);
			row3s[i] = letter;
		}
		
		// delete
		int ycoord = (int) (swid+3*gap+2*height);
		del.setBounds((int) gap, ycoord, (int) (1.5*width), (int) height);
		frame.add(del);
		del.addKeyListener(this);
		del.setFocusable(true);
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.updateDelete();
			}
		});
		
		// enter
		
		int xcoord = (int) ((6+1)*gap + (6)*width + 1.75*width + gap + width);
		ent.setBounds(xcoord, ycoord, (int) (1.75*width), (int) height);
		frame.add(ent);
		ent.addKeyListener(this);
		ent.setFocusable(true);
		ent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] cols = gm.updateEnter();
				helperColourCheck(cols);
			}
		});
	}
	
	private void helperColourCheck(int[] cols) {
		JButton[] word = gm.curr_word;
		
		Color green = new Color(0, 153, 0);
		Color yellow = new Color(204, 204, 0);
		
		if (cols != null) {
			for (int i=0; i<5; i++) {
				if (cols[i] == 0 && word[i].getForeground().equals(Color.black)) {
					word[i].setForeground(Color.red);
				} else if (cols[i] == 1) {
					word[i].setForeground(green);
				}else if (cols[i] == 2 && !word[i].getForeground().equals(green)) {
					word[i].setForeground(yellow);
				}
			}
			
			if (cols[0] == 3) {
				for (int i=0; i<row1s.length; i++) {
					row1s[i].setForeground(Color.black);
				}
				
				for (int i=0; i<row2s.length; i++) {
					row2s[i].setForeground(Color.black);
				}
			
				for (int i=0; i<row3s.length; i++) {
					row3s[i].setForeground(Color.black);
				}
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		Character letter = e.getKeyChar();
		// get the right letter
		
		JButton letterButton = null;
		
		for (int i=0; i<row1s.length; i++) {
			Character checkLetter = row1s[i].getText().toLowerCase().charAt(0);
			if (letter == checkLetter) {
				letterButton = row1s[i];
			}
		}
		
		if (letterButton == null) {
			for (int i=0; i<row2s.length; i++) {
				Character checkLetter = row2s[i].getText().toLowerCase().charAt(0);
				if (letter == checkLetter) {
					letterButton = row2s[i];
				}
			}
		}
		
		if (letterButton == null) {
			for (int i=0; i<row3s.length; i++) {
				Character checkLetter = row3s[i].getText().toLowerCase().charAt(0);
				if (letter == checkLetter) {
					letterButton = row3s[i];
				}
			}
		}
		
		if (letterButton != null) {
			gm.updateWord(letterButton);
		} else {
			if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
				gm.updateDelete();
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
				int[] cols = gm.updateEnter();
				helperColourCheck(cols);
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	

}
