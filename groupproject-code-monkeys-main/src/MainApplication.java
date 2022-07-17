import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import acm.program.GraphicsProgram;

public class MainApplication extends GraphicsProgram {
	
	public static final int WINDOW_WIDTH = 1600;
	public static final int WINDOW_HEIGHT = 800;
	public static final String MUSIC_FOLDER = "sounds";
	
	public int easyScore = 0;
	public int mediumScore = 0;
	public int hardScore = 0;
	public int levelsBeat = 0;
	public int numLives = 3;

	private GraphicsPane curScreen;
	private CharacterSelectPane characterSelectPane;
	private LevelSelectPane levelSelectPane;
	private GraphicsGame graphicsGame;
	private MenuPane menu;
	private LeaderboardPane leadPane;
	//test
	
	/* Method: setupInteractions
	 * -------------------------
	 * must be called before switching to another
	 * pane to make sure that interactivity
	 * is setup and ready to go.
	 */
	protected void setupInteractions() {
		requestFocus();
		addKeyListeners();
		addMouseListeners();
	}
	
	/* switchToScreen(newGraphicsPane)
	 * -------------------------------
	 * will simply switch from making one pane that was currently
	 * active, to making another one that is the active class.
	 */
	protected void switchToScreen(GraphicsPane newScreen) {
		if(curScreen != null) {
			curScreen.hideContents();
		}
		newScreen.showContents();
		curScreen = newScreen;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mousePressed(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mouseReleased(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mouseDragged(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mouseMoved(e);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(curScreen != null) {
			curScreen.keyPressed(e);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(curScreen != null) {
			curScreen.keyReleased(e);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(curScreen != null) {
			curScreen.keyTyped(e);
		}
	}
	

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Let's make something awesome!");
		characterSelectPane = new CharacterSelectPane(this);
		levelSelectPane = new LevelSelectPane(this, 0);
		graphicsGame = new GraphicsGame(this, "easy");
		menu = new MenuPane(this);
		leadPane = new LeaderboardPane(this);
		setupInteractions();
		switchToMenu();
	}
	
	public int getScore() {
		return (easyScore + mediumScore + hardScore);
	}
	
	public void setEasyScore(int score) {
		easyScore = score;
	}
	
	public int getEasyScore() {
		return easyScore;
	}
	
	public void setMediumScore(int score) {
		mediumScore = score;
	}
	
	public int getMediumScore() {
		return mediumScore;
	}
	
	public void setHardScore(int score) {
		hardScore = score;
	}
	
	public int getHardScore() {
		return hardScore;
	}
	
	public void addLevelsBeat() {
		++levelsBeat;
	}
	
	public void removeLevelsBeat() {
		levelsBeat = 0;
	}
	
	public int getLevelsBeat() {
		return levelsBeat;
	}
	
	public void removeLife() {
		--numLives;
	}
	
	public void restoreLives() {
		numLives = 3;
	}
	
	public int getLives() {
		return numLives;
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}

	public void switchToCharSelect() {
		switchToScreen(characterSelectPane);
	}
	
	public void switchToLevelSelect(LevelSelectPane levelSelect) {
		switchToScreen(levelSelect);
	}
	
	public void switchToGame(GraphicsGame g) {
		switchToScreen(g);
	}
	
	public void switchToLead() {
		switchToScreen(leadPane);
	}
	
	public void setLeaderBoard(String username, String size, int score) {
		leadPane.setLeaderBoard(username, size, score);
	}

	public static void main(String[] args) {
		new MainApplication().start();
	}
}