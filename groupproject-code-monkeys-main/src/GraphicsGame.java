import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class GraphicsGame extends GraphicsPane implements ActionListener {
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program;
	private Level level = new Level();
	public static final int WINDOW_WIDTH = 1550;
	public static final int WINDOW_HEIGHT = 800;
	private static final int LIFE_WIDTH = 40;
	private static final int LIFE_HEIGHT = 40;
	
	private GLabel timerLabel = new GLabel("TIMER: ", 850, 25);
	private GLabel lives = new GLabel("LIVES: ", 10, 30);
	private GLabel score = new GLabel("SCORE: 0", 1250, 25);
	private GOval lifeOne = new GOval(100, 5, LIFE_WIDTH, LIFE_HEIGHT);
	private GOval lifeTwo = new GOval(150, 5, LIFE_WIDTH, LIFE_HEIGHT);
	private GOval lifeThree = new GOval(200, 5, LIFE_WIDTH, LIFE_HEIGHT);
	
	//used in keyEvents
	//row == y, column == x
	private double x;
	private double y;
	private int row;
	private int col;
	
	//character and map selections
	private int monkey = 0;
	private String s = "";
	private int time = 120;
	
	//Vectors to hold entity values
	//(row, column, type)
	Vector<Entity> barrels = new Vector<Entity>();
	Vector<Entity> walls = new Vector<Entity>();
	Vector<Entity> bananas = new Vector<Entity>();
	Vector<Entity> cherries = new Vector<Entity>();
	Vector<Entity> mangos = new Vector<Entity>();
	
	//vectors to store spot on screen for adding/removing
	private Vector<GImage> barrelImages = new Vector<GImage>();
	private Vector<GImage> bananaImages = new Vector<GImage>();
	private Vector<GImage> mangoImages = new Vector<GImage>();
	private Vector<GImage> cherryImages = new Vector<GImage>();
	private Vector<GRect> wallImages = new Vector<GRect>();
	private Vector<GLine> gridLines = new Vector<GLine>();
	private GImage character = null;
	private GImage entity = null;
	private GRect winSpace = null;
	private GRect wall = null;
	
	private Timer timer = new Timer(1000, this);
	Vector<Boolean> switcher = null;
	Vector<Boolean> vertic = null;

	
	public GraphicsGame(MainApplication app, String choice) {
		this.program = app;
		
		lives.setFont("Arial-26");
		score.setFont("Arial-26");
		lives.setColor(Color.RED);
		score.setColor(Color.RED);
		
		switcher = level.getSwitcher();
		vertic = level.getVertic();
		
		s = choice;
		
		level.createLevel(choice, app);
	}
	
	public void setMonkey(int monkey) {
		this.monkey = monkey;
	}
	
	public int getMonkey() {
		return monkey;
	}
	/*
	public void createLevel(String choice, MainApplication app) {
		level.createLevel(choice, app);
		s = choice;
	}
	/*
	public void setupEasy() {
		level.createLevel("small");
		s="easy";
	}
	
	public void setupMedium() {
		level.createLevel("medium");
		s="medium";
	}
	
	public void setupHard() {
		level.createLevel("large");
		s="hard";
	}
	*/
	
	public void resize()
	{
		if(s=="easy")
		{
			character.scale(.2);
			program.add(character);
			
			for (GImage temp:bananaImages) {
				temp.scale(1.7);
				program.add(temp);
			}
			for (GImage temp:cherryImages) {
				temp.scale(1.5);
				program.add(temp);
			}
			for (GImage temp:mangoImages) {
				temp.scale(1.5);
				program.add(temp);
			}
			for (GImage temp:barrelImages) {
				temp.scale(1.0);
				program.add(temp);
			}
		}
		else if(s=="medium")
		{
			character.scale(.15);
			program.add(character);
			
			for (GImage temp:bananaImages) {
				temp.scale(1.15);
				program.add(temp);
			}
			for (GImage temp:cherryImages) {
				temp.scale(1.0);
				program.add(temp);
			}
			for (GImage temp:mangoImages) {
				temp.scale(1.05);
				program.add(temp);
			}
			for (GImage temp:barrelImages) {
				temp.scale(.6);
				program.add(temp);
			}
		}
		else
		{
			character.scale(.11);
			program.add(character);
			
			for (GImage temp:bananaImages) {
				temp.scale(.7);
				program.add(temp);
			}
			for (GImage temp:cherryImages) {
				temp.scale(.6);
				program.add(temp);
			}
			for (GImage temp:mangoImages) {
				temp.scale(.6);
				program.add(temp);
			}
			for (GImage temp:barrelImages) {
				temp.scale(.37);
				program.add(temp);
			}
		}
			
	}
	
	private void drawEntities() {
		//entities will be reused in this method for barrels, and fruits
		
		walls = level.getWalls();
		barrels = level.getBarrels();
		bananas = level.getBananas();
		cherries = level.getCherries();
		mangos = level.getMangos();
		
		int startRow = level.map.getStartSpace().getRow();
		int startCol = level.map.getStartSpace().getCol();
		if(monkey == 1)
		{
			character = new GImage("Chimp_Cartoon.jpg", startCol * spaceWidth(), startRow * spaceHeight());
		}
		else if(monkey==2)
		{
			character = new GImage("Gorilla_Cartoon.jpg", startCol * spaceWidth(), startRow * spaceHeight());
		}
		else
		{
			character = new GImage("Orangutan_Cartoon.jpg", startCol * spaceWidth(), startRow * spaceHeight());
		}
		
		for (Entity temp:barrels) {
			entity = new GImage("barrel.png", temp.getCol() * spaceWidth(), temp.getRow() * spaceHeight());
			barrelImages.add(entity);
		}
		
		for (Entity temp:bananas) {
			entity = new GImage("Banana.png", temp.getCol() * spaceWidth(), temp.getRow() * spaceHeight());
			bananaImages.add(entity);
		}
		
		for (Entity temp:cherries) {
			entity = new GImage("Cherry.png", temp.getCol() * spaceWidth(), temp.getRow() * spaceHeight());
			cherryImages.add(entity);
		}
		
		for (Entity temp:mangos) {
			entity = new GImage("Mango.png", temp.getCol() * spaceWidth(), temp.getRow() * spaceHeight());
			mangoImages.add(entity);
		}
		
		resize();
		
		for (Entity temp:walls) {
			wall = new GRect(temp.getCol() * spaceWidth(), temp.getRow() * spaceHeight(), spaceWidth(), spaceHeight());
			wall.setFillColor(Color.BLACK);
			wall.setFilled(true);
			program.add(wall);
			wallImages.add(wall);
		}
		
		program.add(lives);
		program.add(score);
		drawGridLines(s);
		drawWinningSpace();
		drawLives();
	}
	
	private void drawLives() {
		lifeOne.setFilled(true);
		lifeTwo.setFilled(true);
		lifeThree.setFilled(true);
		
		lifeOne.setFillColor(Color.red);
		lifeTwo.setFillColor(Color.red);
		lifeThree.setFillColor(Color.red);
		
		program.add(lifeOne);
		
		if (program.getLives() >= 2) {
			program.add(lifeTwo);
		}
		if (program.getLives() >= 3) {
			program.add(lifeThree); 
		}
		
	}
	
	private void drawWinningSpace() {
		int winRow = level.getWin().getRow();
		int winCol = level.getWin().getCol();
		
		winSpace = new GRect(winCol * spaceWidth(), winRow * spaceHeight(), spaceWidth(), spaceHeight());
		winSpace.setFillColor(Color.RED);
		winSpace.setFilled(true);
		program.add(winSpace);
	}
	
	private void drawGridLines(String s) {
		int num = 0;
		if(s=="hard")
		{num = 25;}
		else if(s=="medium")
		{num = 15;}
		else
		{num=10;}
		for (int i = 0; i < num; ++i) {
			
			GLine gridLine = new GLine(0, spaceHeight()*i, WINDOW_WIDTH, spaceHeight()*i);
			gridLines.add(gridLine);
		}
		for (int i = 0; i < num; ++i) {
			
			GLine gridLine = new GLine(spaceWidth()*i, 0, spaceWidth()*i,WINDOW_HEIGHT );
			gridLines.add(gridLine);
		}
		for(GLine l:gridLines)
		{
			program.add(l);
		}
	}
	
	public void removeGridLines()
	{
		for(GLine l:gridLines)
		{
			program.remove(l);
		}
		
	}
	
	public void removeEntities()
	{
		program.remove(character);
		
		for(GRect l:wallImages)
		{
			program.remove(l);
		}
		for(GImage l:cherryImages)
		{
			program.remove(l);
		}
		for(GImage l:mangoImages)
		{
			program.remove(l);
		}
		for(GImage l:bananaImages)
		{
			program.remove(l);
		}
		for(GImage l:barrelImages)
		{
			program.remove(l);
		}
		program.remove(lives);
		program.remove(score);
		removeGridLines();
		program.remove(winSpace);
		program.remove(lifeOne);
		program.remove(lifeTwo);
		program.remove(lifeThree);
		
	}
	
	private double spaceWidth() {
		int num = 0;
		if(s=="hard")
		{num = 25;}
		else if(s=="medium")
		{num = 15;}
		else
		{num=10;}
		return WINDOW_WIDTH / num;
	}
	
	private double spaceHeight() {
		int num = 0;
		if(s=="hard")
		{num = 25;}
		else if(s=="medium")
		{num = 15;}
		else
		{num=10;}
		return WINDOW_HEIGHT / num;
	}

	@Override
	public void showContents() {
		drawEntities();
		timer.start();
	}

	@Override
	public void hideContents() {
		removeEntities();
		timer.stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		x = character.getLocation().getX();
		y = character.getLocation().getY();
		row = (int)(y / spaceHeight());
		col = (int)(x / spaceWidth());
		Space targetSpace = level.getCharSpace();
		
		if (e.getKeyChar() == 'w') {
			targetSpace = new Space(row-1,col);
			if (level.map.wallCollision(targetSpace)) {
				return;
			}
			
			else {
				Entity entityCollision = level.map.getEnt(targetSpace);
				
				if (entityCollision != null) {
					if (entityCollision.getType() == EntityType.BANANA) {
						for (int i = 0; i < bananas.size(); ++i) {
							if (bananas.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(bananaImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.CHERRY) {
						for (int i = 0; i < cherries.size(); ++i) {
							if (cherries.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(cherryImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.MANGO) {
						for (int i = 0; i < mangos.size(); ++i) {
							if (mangos.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(mangoImages.elementAt(i));
							}
						}
					}
					level.collision(targetSpace);
					if (entityCollision.getType() == EntityType.BARREL) {
						if (program.getLives() == 2) {
							program.remove(lifeThree);
						}
						else if (program.getLives() == 1) {
							program.remove(lifeThree);
							program.remove(lifeTwo);
						}
						else {
							program.remove(lifeThree);
							program.remove(lifeTwo);
							program.remove(lifeOne);
						}
						targetSpace = level.getCharSpace();
						character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
					}
				}
				level.setCharSpace(targetSpace.getRow(),targetSpace.getCol());	
				character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
			}
		}
		if (e.getKeyChar() == 'a') {
			targetSpace = new Space(row, col-1);
			
			if (level.map.wallCollision(targetSpace)) {
				return;
			}
			
			else {
				Entity entityCollision = level.map.getEnt(targetSpace);
				
				if (entityCollision != null) {
					if (entityCollision.getType() == EntityType.BANANA) {
						for (int i = 0; i < bananas.size(); ++i) {
							if (bananas.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(bananaImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.CHERRY) {
						for (int i = 0; i < cherries.size(); ++i) {
							if (cherries.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(cherryImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.MANGO) {
						for (int i = 0; i < mangos.size(); ++i) {
							if (mangos.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(mangoImages.elementAt(i));
							}
						}
					}
					level.collision(targetSpace);
					if (entityCollision.getType() == EntityType.BARREL) {
						if (program.getLives() == 2) {
							program.remove(lifeThree);
						}
						else if (program.getLives() == 1) {
							program.remove(lifeThree);
							program.remove(lifeTwo);
						}
						else {
							program.remove(lifeThree);
							program.remove(lifeTwo);
							program.remove(lifeOne);
						}
						targetSpace = level.getCharSpace();
						character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
					}
				}
				level.setCharSpace(targetSpace.getRow(),targetSpace.getCol());	
				character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
			}
		}
		if (e.getKeyChar() == 's') {
			targetSpace = new Space(row+1, col);
			
			if (level.map.wallCollision(targetSpace)) {
				return;
			}
			
			else {
				Entity entityCollision = level.map.getEnt(targetSpace);
				
				if (entityCollision != null) {
					if (entityCollision.getType() == EntityType.BANANA) {
						for (int i = 0; i < bananas.size(); ++i) {
							if (bananas.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(bananaImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.CHERRY) {
						for (int i = 0; i < cherries.size(); ++i) {
							if (cherries.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(cherryImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.MANGO) {
						for (int i = 0; i < mangos.size(); ++i) {
							if (mangos.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(mangoImages.elementAt(i));
							}
						}
					}
					level.collision(targetSpace);
					if (entityCollision.getType() == EntityType.BARREL) {
						if (program.getLives() == 2) {
							program.remove(lifeThree);
						}
						else if (program.getLives() == 1) {
							program.remove(lifeThree);
							program.remove(lifeTwo);
						}
						else {
							program.remove(lifeThree);
							program.remove(lifeTwo);
							program.remove(lifeOne);
						}
						targetSpace = level.getCharSpace();
						character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
					}
				}
				level.setCharSpace(targetSpace.getRow(),targetSpace.getCol());	
				character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
			}
		}
		if (e.getKeyChar() == 'd') {
			targetSpace = new Space(row, col+1);
			
			if (level.map.wallCollision(targetSpace)) {
				return;
			}
			
			else {
				Entity entityCollision = level.map.getEnt(targetSpace);
				
				if (entityCollision != null) {
					if (entityCollision.getType() == EntityType.BANANA) {
						for (int i = 0; i < bananas.size(); ++i) {
							if (bananas.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(bananaImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.CHERRY) {
						for (int i = 0; i < cherries.size(); ++i) {
							if (cherries.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(cherryImages.elementAt(i));
							}
						}
					}
					if (entityCollision.getType() == EntityType.MANGO) {
						for (int i = 0; i < mangos.size(); ++i) {
							if (mangos.elementAt(i).getSpace() == entityCollision.getSpace()) {
								program.remove(mangoImages.elementAt(i));
							}
						}
					}
					level.collision(targetSpace);
					if (entityCollision.getType() == EntityType.BARREL) {
						if (program.getLives() == 2) {
							program.remove(lifeThree);
						}
						else if (program.getLives() == 1) {
							program.remove(lifeThree);
							program.remove(lifeTwo);
						}
						else {
							program.remove(lifeThree);
							program.remove(lifeTwo);
							program.remove(lifeOne);
						}
						targetSpace = level.getCharSpace();
						character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
					}
				}
				level.setCharSpace(targetSpace.getRow(),targetSpace.getCol());	
				character.setLocation(targetSpace.getCol() * spaceWidth(), targetSpace.getRow() * spaceHeight());
			}
		}
		
		if (program.getLives() == 0) {
			program.remove(timerLabel);
			time = 120;
			program.removeLevelsBeat();
			program.restoreLives();
			hideContents();
			//GButton lose = new GButton("YOU LOSE", WINDOW_WIDTH/4, WINDOW_HEIGHT/4, WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
			//program.add(lose);
			
			LevelSelectPane levelSelect = new LevelSelectPane(program, monkey);
			program.pause(3000);
			//program.remove(lose);
			program.switchToLevelSelect(levelSelect);
		}
		
		if(character.getX() == winSpace.getX() && character.getY() == winSpace.getY()) {
			program.remove(timerLabel);
			time = 120;
			program.addLevelsBeat();
			hideContents();
			
			if (program.getLevelsBeat() == 3) {
				if (level.getScore() > program.getHardScore()) {
					program.setHardScore(level.getScore());
					program.setLeaderBoard("PLAYER", "hard", program.getHardScore());
					program.setLeaderBoard("PLAYER", "medium", program.getMediumScore());
					program.setLeaderBoard("PLAYER", "easy", program.getEasyScore());
				}
				//GParagraph win = new GParagraph("YOU WIN!", WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
				//program.add(win);
				
				System.out.println("\nWIN\n");			
				//LevelSelectPane levelSelect = new LevelSelectPane(program, monkey);
				program.removeLevelsBeat();
				program.pause(3000);
				//program.remove(win);
				program.switchToLead();
			}
			else {
				if (program.getLevelsBeat() == 2) {
					if (level.getScore() > program.getMediumScore()) {
						program.setMediumScore(level.getScore());
						//program.setLeaderBoard("PLAYER", s, program.getMediumScore());
					}
				}
				else {
					if (level.getScore() > program.getEasyScore()) {
						program.setEasyScore(level.getScore());
						//program.setLeaderBoard("PLAYER", s, program.getEasyScore());
					}
				}
				//GParagraph win = new GParagraph("YOU WIN! ONTO NEXT LEVEL!", WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
				//program.add(win);
				
				System.out.println("\nWIN\n");			
				LevelSelectPane levelSelect = new LevelSelectPane(program, monkey);
				program.pause(3000);
				//program.remove(win);
				program.switchToLevelSelect(levelSelect);
			}
		}
		score.setLabel("SCORE: " + level.getScore());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		program.add(character);
	}
	
	public void actionPerformed(ActionEvent e) {
		program.remove(timerLabel);
		timerLabel = new GLabel("TIMER: " + time, 850, 25);
		timerLabel.setColor(Color.RED);
		timerLabel.setFont("Arial-26");
		program.add(timerLabel);
		
		if (time == 0) {
			program.remove(timerLabel);
		}
		
		if (time == 0) {
			time = 120;
			LevelSelectPane levelSelect = new LevelSelectPane(program, monkey);
			program.switchToLevelSelect(levelSelect);
		}
		--time;
	}
}
