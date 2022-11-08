import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class Board {
	public JLabel[][] all_words;
	private int tot_attempt;
	private JFrame frame;
	private int swid;
	private int shei;
	private int fsh;
	
	public int totalWins;
	public int consWins;
	public int recordWins;
	
	public JLabel totalWinLabel;
	public JLabel totalConsLabel;
	public JLabel totalRecordLabel;
	
	public JLabel endLabel;
	public JButton reset;
	
	public Board(int a, JFrame f, int swid, int screenHeight, int fullScreenHeight, int[] score) {
		this.tot_attempt = a;
		this.frame = f;
		this.swid = swid;
		this.shei = screenHeight;
		this.fsh = fullScreenHeight;
		
		this.totalWins = score[0];
		this.consWins = score[1];
		this.recordWins = score[2];
		
		this.totalWinLabel = new JLabel();
		this.totalConsLabel = new JLabel();
		this.totalRecordLabel = new JLabel();
		
		this.all_words = new JLabel[a][5];
		
		this.endLabel = new JLabel("");
		this.reset = new JButton("New Game?");
		
		drawEmptyWords();
		drawScore();
		addResetEnd();
	}
	
	private void addResetEnd() {
		// TODO Auto-generated method stub
		reset.setLocation(swid/2 - 50, shei+30);
		reset.setSize(100, 60);
		frame.add(reset);
		reset.setVisible(false);
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					updateScore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resetBoard();
				reset.setVisible(false);
				endLabel.setVisible(false);
				totalWinLabel.setVisible(true);
				totalConsLabel.setVisible(true);
				totalRecordLabel.setVisible(true);
			}
		});
		
		endLabel.setLocation(0, shei);
		endLabel.setSize(swid, fsh-shei);
		endLabel.setHorizontalAlignment(JTextField.CENTER);
		frame.add(endLabel);
		endLabel.setVisible(false);
		endLabel.setFont(new Font("Comic Sans", Font.BOLD, 24));
	}

	private void drawEmptyWords() {
		double gap = 10;
		double width = (swid - tot_attempt*gap)/tot_attempt;
		
		for (int row = 0; row < all_words.length; row++) {
			for (int j = 0; j < all_words[row].length; j++) {
				JLabel printLetter = new JLabel("");
				printLetter.setFont(new Font("Comic Sans", Font.BOLD, 24));
				printLetter.setBounds((int) (swid/2 + (j-2)*width + (j-3)*gap), (int) ((row+1)*gap + (row)*width), (int) width, (int) width);
				frame.add(printLetter);
				all_words[row][j] = printLetter;
			}
		}
	}
	
	public void drawScore() {
		Font font = new Font("Comic Sans", Font.BOLD, 24);
		int fm = font.getSize();
		
		totalWinLabel = new JLabel("Total Wins: " + totalWins);
		totalWinLabel.setFont(font);
		totalWinLabel.setBounds(10, shei + 10 + fm, swid, fm);
		totalWinLabel.setHorizontalAlignment(JTextField.CENTER);
		frame.add(totalWinLabel);
		
		totalConsLabel = new JLabel("Consecutive Wins: " + consWins);
		totalConsLabel.setFont(font);
		totalConsLabel.setBounds(10, shei + 20 + 2*fm, swid, fm);
		totalConsLabel.setHorizontalAlignment(JTextField.CENTER);
		frame.add(totalConsLabel);
		
		totalRecordLabel = new JLabel("Record Consecutive Wins: " + recordWins);
		totalRecordLabel.setFont(font);
		totalRecordLabel.setBounds(10, shei + 30 + 3*fm, swid, fm);
		totalRecordLabel.setHorizontalAlignment(JTextField.CENTER);
		frame.add(totalRecordLabel);
	}

	@SuppressWarnings("resource")
	public void updateScore() throws IOException {
		totalWinLabel.setText("Total Wins: " + totalWins);
		totalConsLabel.setText("Consecutive Wins: " + consWins);
		totalRecordLabel.setText("Record Consecutive Wins: " + recordWins);
		try
	    {
	        FileWriter fileWriter = new FileWriter("src/score");
	        fileWriter.write(String.format(totalWins + "%n" + consWins + "%n" + recordWins));
	        fileWriter.flush();
	        fileWriter.close();
	    }
	    catch(IOException ioException)
	    {
	        ioException.printStackTrace();
	    }
	}
	
	public void resetBoard() {
		for (int row = 0; row < all_words.length; row++) {
			for (int j = 0; j < all_words[row].length; j++) {
				all_words[row][j].setText("");
				all_words[row][j].setForeground(Color.black);
			}
		}
	}
}

