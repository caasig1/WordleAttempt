import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawingCanvas extends JComponent { // important for setting shapes in terms of size of window and other stuff
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int attempts;
	private Word[] words;
	
	public DrawingCanvas(int w, int h, int a, JFrame f) {
		width = w;
		height = h;
		attempts = a;
		words = new Word[attempts];
		for (int i = 0; i < attempts; i++) {
			words[i] = new Word(i+1, width, height, attempts);
		}
	}
	
	@Override
	// none of this draws on the rectangle, will need to draw in the main class
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON); // theres a few key and values we can pass in
		g2d.setRenderingHints(rh);
		
		for (int i = 0; i < attempts; i++) {
			words[i].drawWord(g2d);
		}
		
		g2d.setColor(Color.black);
		Line2D.Double border = new Line2D.Double(0, height, width, height);
		g2d.draw(border);
	}
}
