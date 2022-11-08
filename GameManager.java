import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameManager {
	private int curr_attempt;
	private int tot_attempt;
	private int curr_letter;
	private JLabel[][] all_words;
	private Dictionary dict;
	
	public JButton[] curr_word;
	
	public Board b;
	
	public GameManager(int a, Board b, Dictionary d) {
	
		this.curr_attempt = 0;
		this.curr_letter = 0;
		this.tot_attempt = a;
		this.all_words = b.all_words;
		this.b = b;
		this.dict = d;
		this.curr_word = new JButton[5];
		makeResetListener();
	}
	
	private void makeResetListener() {
		// TODO Auto-generated method stub
		
	}

	public void updateWord(JButton letter) {
		if (curr_letter < 5) {
			all_words[curr_attempt][curr_letter].setText(letter.getText());
			curr_word[curr_letter] = letter;
			curr_letter++;
		} else {
			all_words[curr_attempt][curr_letter-1].setText(letter.getText());
			curr_word[curr_letter-1] = letter;
		}
	}
	
	public void updateDelete() {
		all_words[curr_attempt][0].setForeground(Color.black);
		all_words[curr_attempt][1].setForeground(Color.black);
		all_words[curr_attempt][2].setForeground(Color.black);
		all_words[curr_attempt][3].setForeground(Color.black);
		all_words[curr_attempt][4].setForeground(Color.black);
		if (curr_letter > 0) {
			all_words[curr_attempt][curr_letter-1].setText("");
			if (curr_letter == 5) {
				curr_word[curr_letter-1] = null;
			} else {
				curr_word[curr_letter] = null;
			}
			curr_letter--;
		}
	}
	
	public int[] updateEnter() {
		if (curr_letter == 5) {
			String word = all_words[curr_attempt][0].getText() + 
					all_words[curr_attempt][1].getText() + 
					all_words[curr_attempt][2].getText() +
					all_words[curr_attempt][3].getText() + 
					all_words[curr_attempt][4].getText();
			word = word.toLowerCase();
			int[] cols = checkWord(word);
			return cols;
		}
		return null;
	}

	private int[] checkWord(String word) {
		boolean check = false;
		for (int i = 0; i < dict.fulldictionary.length; i++) {
			if (dict.fulldictionary[i].equals(word)) {
				check = true;
			}
		}
		
		String sol = dict.solution;
		int[] sol_checked = new int[5];
		int[] word_checked = new int[5];
		
		if (check) {
			boolean allGreen = true;
			
			// run thru it a few times, first for greens, then yellows, then double check
			for (int i=0; i<5; i++) {
				if (word.charAt(i) == sol.charAt(i)) {
					// green
					all_words[curr_attempt][i].setForeground(new Color(0, 153, 0));
					//remove those letters so that i can check for doubles
					sol_checked[i] = 1;
					word_checked[i] = 1;
				} else {
					allGreen = false;
					sol_checked[i] = 0;
					word_checked[i] = 0;
				}
			}
			
			// now check yellows if not all green
			if (!allGreen) {
				for (int i=0; i<5; i++) {
					if (word_checked[i] == 0) {
						Character attCheck = word.charAt(i);
						for (int j=0; j<5; j++) { 
							if (sol_checked[j] == 0) {
								Character solCheck = sol.charAt(j);
								if (attCheck == solCheck) {
									word_checked[i] = 2;
									sol_checked[j] =2;
									all_words[curr_attempt][i].setForeground(new Color(204, 204, 0));
								}
							}
						}
					}
				}
			}
			
			if (!allGreen) {
				for (int i=0; i<5; i++) {
					if (word_checked[i] == 0) {
						all_words[curr_attempt][i].setForeground(Color.gray);
					}
				}
			}
			
			
			
			curr_attempt++;
			curr_letter = 0;
			// if allGreen then end game
			if (allGreen || curr_attempt == tot_attempt) {
				updateScore(allGreen, false);
				int[] done = {3,3,3,3,3};
				return done;
			}
			return word_checked;
			
		} else {
			all_words[curr_attempt][0].setForeground(Color.red);
			all_words[curr_attempt][1].setForeground(Color.red);
			all_words[curr_attempt][2].setForeground(Color.red);
			all_words[curr_attempt][3].setForeground(Color.red);
			all_words[curr_attempt][4].setForeground(Color.red);
			return null;
		}
	}

	private void updateScore(boolean correct, boolean startup) {
		if (correct) {
			b.totalWins++;
			b.consWins++;
			if (b.consWins > b.recordWins) {
				b.recordWins++;
			}
			
			b.endLabel.setText("WINNER POV! Congrats :) the word was " + dict.solution);
		} else {
			b.consWins = 0;
			b.endLabel.setText("BOOOO U SUCK D:< the word was " + dict.solution);
		}
		
		b.reset.setVisible(true);
		b.endLabel.setVisible(true);
		b.totalWinLabel.setVisible(false);
		b.totalConsLabel.setVisible(false);
		b.totalRecordLabel.setVisible(false);
		curr_attempt = 0;
		curr_letter = 0;
		dict.resetWord();
	}
}
