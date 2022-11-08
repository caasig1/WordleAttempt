import java.awt.*;
import java.awt.geom.*;

public class Word {

	private double attempts;
	private double row;
	private double gap;
	private double width;
	private double arc;
	private double screenwid;
	
	public RoundRectangle2D.Double[] word;
	
	public Word(int x, int w, int h, int a) {
		this.attempts = a;
		this.row = x;
		this.screenwid = w;
		this.gap = 10;
		this.width = (this.screenwid - this.attempts*this.gap)/this.attempts;
		this.arc = 20;
		
		this.word = new RoundRectangle2D.Double[5];
	}
	
	public void drawWord(Graphics2D g2d) {
		RoundRectangle2D.Double e1 = new RoundRectangle2D.Double((screenwid/2) - 2.5*width - 2*gap, row*gap + (row-1)*width, width, width, arc, arc);
		RoundRectangle2D.Double e2 = new RoundRectangle2D.Double((screenwid/2) - 1.5*width - 1*gap, row*gap + (row-1)*width, width, width, arc, arc);
		RoundRectangle2D.Double e3 = new RoundRectangle2D.Double((screenwid/2) - 0.5*width - 0*gap, row*gap + (row-1)*width, width, width, arc, arc);
		RoundRectangle2D.Double e4 = new RoundRectangle2D.Double((screenwid/2) + 0.5*width + 1*gap, row*gap + (row-1)*width, width, width, arc, arc);
		RoundRectangle2D.Double e5 = new RoundRectangle2D.Double((screenwid/2) + 1.5*width + 2*gap, row*gap + (row-1)*width, width, width, arc, arc);
		word[0] = e1;
		word[1] = e2;
		word[2] = e3;
		word[3] = e4;
		word[4] = e5;
		g2d.setColor(Color.BLACK);
		g2d.draw(e1);
		g2d.draw(e2);
		g2d.draw(e3);
		g2d.draw(e4);
		g2d.draw(e5);
	}
}
