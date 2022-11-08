import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Dictionary {
	public String[] dictionary;
	public String[] fulldictionary;
	public String solution;
	
	public Dictionary() {
		this.dictionary = new String[2315];
		this.fulldictionary = new String[23145];
		
		Random rand = new Random();
		int intRandom = rand.nextInt(2315);
		
		initiateDict();
		inidiateFullDict();
		
		this.solution = dictionary[intRandom];
	}
	
	private void inidiateFullDict() {
		try {
		      File myObj = new File("src/fulldict");
		      
		      Scanner myReader = new Scanner(myObj);
		      
		      int count = 0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        data = data.replaceAll("[^a-zA-Z]", "");
		        
		        if (data.length() == 5) {
		        	data = data.toLowerCase();
		        	fulldictionary[count] = data;
		        	count++;
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } catch (@SuppressWarnings("hiding") IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
	}

	private void initiateDict() {
		try {
		      File myObj = new File("src/dictionary");
		      
		      Scanner myReader = new Scanner(myObj);
		      
		      int count = 0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        data = data.replaceAll("[^a-zA-Z]", "");
		        
		        if (data.length() == 5) {
		        	data = data.toLowerCase();
		        	dictionary[count] = data;
		        	count++;
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } catch (@SuppressWarnings("hiding") IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
	}
	
	public void resetWord() {
		Random rand = new Random();
		int intRandom = rand.nextInt(2315);
		this.solution = dictionary[intRandom];
	}
}
