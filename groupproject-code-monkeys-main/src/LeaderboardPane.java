import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class LeaderboardPane extends GraphicsPane {
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
	private LeaderBoard l = new LeaderBoard("","easy",-1);

	private GParagraph para;
	private GParagraph score;
	private GButton rect;
	

	public LeaderboardPane(MainApplication app) {
		this.program = app;
		para = new GParagraph("Leaderboard", app.getWidth()/2-150, 50);
		para.setFont("Arial-35");
		
		rect = new GButton("Back", 25, 25, 60, 60);
		rect.setFillColor(Color.RED);
		
		score = new GParagraph(l.getValues(),40,250);
		score.setFont("Arial-35");
	}
	
	public void setLeaderBoard(String username, String size, int score ) {
		l.add(username, size, score);
		this.score = new GParagraph(l.getValues(), 40, 250);
		this.score.setFont("Arial-35");
	}

	@Override
	public void showContents() {
		program.add(para);
		program.add(rect);
		program.add(score);
	}

	@Override
	public void hideContents() {
		program.remove(para);
		program.remove(rect);
		program.remove(score);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToMenu();
		}
		
	}
}

