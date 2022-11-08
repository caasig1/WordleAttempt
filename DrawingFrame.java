import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
 
public class DrawingFrame {
    public static void main(String[] args)
    {
        int w = 612;
        int sh = 1200;
        int h = 960;
        int a = 6;
        int[] score = new int[3];
        
        JFrame f = new JFrame();
        
        try {
            File myObj = new File("src/score");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
            	String data = myReader.nextLine();
            	score[count] = Integer.parseInt(data);
            	count++;
            }
            myReader.close();
        	} catch (FileNotFoundException e) {
        		System.out.println("An error occurred.");
        		e.printStackTrace();
        }
        
        Board b = new Board(a,f,w,h,sh, score);
        
        Dictionary d = new Dictionary();
        
        GameManager gm = new GameManager(a, b, d);
        
        new Keyboard(f,w,h,a,gm);
        
        DrawingCanvas dc = new DrawingCanvas(w,h,a,f);
        f.add(dc);
        
        f.setSize(w, sh);
        f.setTitle("Wordle Recreation");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }	
	
}