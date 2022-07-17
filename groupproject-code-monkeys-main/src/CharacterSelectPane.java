import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Vector;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class CharacterSelectPane extends GraphicsPane {
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
	private GLabel orangLabel;
	private GLabel gorillaLabel;
	private GLabel monkeyLabel;
	private GButton charSelect = new GButton("SELECTED", 0, 0, 0, 0);
	private GButton next = new GButton("NEXT", 1400, 700, 60, 60);
	private GImage charOrang;
	private GImage charGorilla;
	private GImage charMonkey;
	private GParagraph para;
	private GButton rect;
	private boolean monkey,orang,gorilla = false;
	private static final String LABEL_FONT = "Arial-Bold-32";
	
	private int choice = 0;

	public CharacterSelectPane(MainApplication app) {
		this.program = app;
		para = new GParagraph("Character Select", app.getWidth() / 2.3, app.getHeight() / 10);
		para.setFont("Arial-26");
		
		rect = new GButton("Back", 25, 25, 60, 60);
		rect.setFillColor(Color.RED);
		
		charMonkey = new GImage("Chimp_Cartoon.jpg", 250, 200);
		charGorilla = new GImage("Gorilla_Cartoon.jpg", 650, 200);
		charOrang = new GImage ("Orangutan_Cartoon.jpg", 950, 200);

		monkeyLabel = new GLabel("Monkey", 375, 525);
		gorillaLabel = new GLabel("Gorilla", 700, 525);
		orangLabel =  new GLabel("Orangutan", 1025, 525);
		
		monkeyLabel.setFont(LABEL_FONT);
		gorillaLabel.setFont(LABEL_FONT);
		orangLabel.setFont(LABEL_FONT);
		
		monkeyLabel.setColor(Color.DARK_GRAY);
		gorillaLabel.setColor(Color.LIGHT_GRAY);
		orangLabel.setColor(Color.ORANGE);
	}
	
	public char getChar()
	{
		if(monkey)
		{
			return 'm';
		}
		else if(gorilla)
		{
			return 'g';
		}
		else if(orang)
		{
			return 'o';
		}
		return ' ';
	}

	@Override
	public void showContents() {
		program.add(para);
		program.add(rect);
		program.add(charOrang);
		program.add(charGorilla);
		program.add(charMonkey);
		program.add(gorillaLabel);
		program.add(monkeyLabel);
		program.add(orangLabel);
	}

	@Override
	public void hideContents() {
		program.remove(para);
		program.remove(rect);
		program.remove(gorillaLabel);
		program.remove(monkeyLabel);
		program.remove(orangLabel);
		program.remove(charOrang);
		program.remove(charGorilla);
		program.remove(charMonkey);
		program.remove(charSelect);
		program.remove(next);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (charSelect.isVisible()) {
			program.remove(charSelect);
			program.remove(next);
		}
		if (obj == charOrang || obj == orangLabel) {
			charSelect = new GButton("SELECTED", 1054, 575, 100, 50);
			orang = true;
			choice = 3;
		}
		if (obj == charGorilla || obj == gorillaLabel) {
			charSelect = new GButton("SELECTED", 703, 575, 100, 50);
			gorilla = true;
			choice = 2;
		}
		if (obj == charMonkey || obj == monkeyLabel) {
			charSelect = new GButton("SELECTED", 384, 575, 100, 50);
			monkey = true;
			choice = 1;
		}
		charSelect.setFillColor(Color.RED);
		next.setFillColor(Color.RED);
		program.add(charSelect);
		program.add(next);
		
		if (charSelect.isVisible()) {
			program.add(next);
		}
		if (obj == next) {
			LevelSelectPane levelSelect = new LevelSelectPane(program, choice);
			program.switchToLevelSelect(levelSelect);
		}
		if (obj == rect) {
			program.switchToMenu();
		}
		
	}
}
