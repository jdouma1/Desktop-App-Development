import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import java.util.Vector;

public class MenuPane extends GraphicsPane {
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
	private LeaderboardPane lp;
	
	private GButton rect;
	private GButton exit;
	private GButton lead;
	private GImage img;
	private final int BUTTON_SIZE = 60;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Play Game", app.getWidth()/2-BUTTON_SIZE*2, app.getHeight()/2+BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		rect.setFillColor(Color.RED);
		exit = new GButton("Exit Game", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2+BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		exit.setFillColor(Color.RED);
		lead = new GButton("Leaderboard", app.getWidth() - app.getWidth() / 4, app.getHeight() - app.getHeight() / 4, BUTTON_SIZE, BUTTON_SIZE);
		lead.setFillColor(Color.RED);
		img = new GImage("codemonjey.png", app.getWidth() / 2.5, app.getHeight() / 3.5);
	}

	@Override
	public void showContents() {
		program.add(rect);
		program.add(exit);
		program.add(img);
		program.add(lead);
	}

	@Override
	public void hideContents() {
		program.remove(rect);
		program.remove(exit);
		program.remove(img);
		program.remove(lead);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
				program.switchToCharSelect();
		}
		else if(obj == exit)
		{
			System.exit(0);
		}
		else if(obj==lead)
		{
			program.switchToLead();
		}
	}
}
