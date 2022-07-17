import java.util.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import acm.program.*;

public class Level extends GraphicsProgram implements EventListener, ActionListener,KeyListener {
	String mapSize;
	Map map;
	int barrelMove = 0;
	int barrelAmount =0;
	int min = 0;
	int max = 0;
	//int timerCount = 0;
	Random randNum = new Random();
	Space winSpace;
	private int score = 0;
	Vector<LeaderBoard> lead = new Vector<LeaderBoard>();
	Timer barrel = new Timer(1000, this);
	Vector<Boolean> switcher = null;
	Vector<Boolean> vertic = null;
	Vector<Entity> walls = null;
	MainApplication program = null;
		
	

	public void run() {
		System.out.println("hey boyi i'm workng here");
		barrel.start();
		for (int i = 0; i < 10;i++) {
		setUpLevelEasy();
		}
	}
	
	
	public void init() {
		addKeyListeners();
		addMouseListeners();
		requestFocus();
	}
	
	public Level createLevel(String s, MainApplication app) {
		program = app;
		mapSize = s;
		//made created of small level as default for testing purposes "SO REMOVE THIS LATER ON WITH s= "small""
		if(s== "easy") {
			setUpLevelEasy();
		}else if(s == "medium") {
			setUpLevelMed();
		}else if(s == "hard"){
			setUpLevelHard();
		}else {
			map = new Map(0,0);
			winSpace = new Space(0,0);
		}
		
		return null;
	}
	public void setUpLevelEasy(){
		walls = new Vector<Entity>();
		switcher = new Vector<Boolean>();
		vertic = new Vector<Boolean>();
		map = new Map(10,10);
		winSpace = new Space(5, 9);
		map.setWinSpace(winSpace);
		barrelMove = 2;
		
		map.addEntity(EntityType.CHARACTER, 2, 0, false);
		//0 vertical
		map.addEntity(EntityType.WALL, 0, 0, false);
		walls.add(new Entity(EntityType.WALL, 0, 0, false));
		//1 vertical
		for (int i = 0; i < 10; i++) {
			if(i==0||i==2||i==3||i==4||i==6||i==7||i==8) {
				map.addEntity(EntityType.WALL, i,1, false);
				walls.add(new Entity(EntityType.WALL, i,1, false));
			}
		}
		//2 vertical
		for (int i = 0; i < 10; i++) {
			if(i==0/*||i==2||i==6*/) {
				map.addEntity(EntityType.WALL, i,2, false);
				walls.add(new Entity(EntityType.WALL, i,2, false));
			}	
		}
		//3 vertical
		for (int i = 0; i < 10; i++) {
			if(i==0||i==1||i==2||i==3||i==5||i==6||i==8) {
				map.addEntity(EntityType.WALL, i,3, false);
				walls.add(new Entity(EntityType.WALL, i,3, false));	
			}
		}
		//4 vertical
		//map.addEntity(EntityType.WALL, 5,4, false);
		map.addEntity(EntityType.WALL, 8,4, false);
		//walls.add(new Entity(EntityType.WALL, 5,4, false));
		walls.add( new Entity(EntityType.WALL, 8,4, false));
		//5 vertical
		for (int i = 0; i < 10; i++) {
			if(i==0||i==2||i==3||i==4||i==5||i==7||i==8||i==9) {
				map.addEntity(EntityType.WALL, i,5, false);
				walls.add(new Entity(EntityType.WALL, i,5, false));	
			}
		}
		//6 vertical
		map.addEntity(EntityType.WALL,  0,6, false);
		//map.addEntity(EntityType.WALL,  2,6, false);
		walls.add(new Entity(EntityType.WALL, 0,6, false));
		//walls.add(new Entity(EntityType.WALL, 2,6, false));
		//7 vertical
		for (int i = 0; i < 10; i++) {
			if(i==0||i==1||i==2||i==4||i==5||i==6||i==7||i==8) {
				map.addEntity(EntityType.WALL, i,7, false);
				walls.add(new Entity(EntityType.WALL, i,7, false));	
			}
		}
		//8 vertical
		//map.addEntity(EntityType.WALL,  4,8, false);
		map.addEntity(EntityType.WALL, 8, 8, false);
		//walls.add(new Entity(EntityType.WALL, 4,8, false));
		walls.add(new Entity(EntityType.WALL,  8, 8, false));
		// 9 vertical
		for (int i = 0; i < 10; i++) {
			if(i==1||i==2||i==3||i==4||i==6) {
				map.addEntity(EntityType.WALL, i,9, false);
				walls.add(new Entity(EntityType.WALL, i,9, false));
			}
		}
		
		//add barrel
		/*
		map.addEntity(EntityType.BARREL, 9,0,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 4, 4,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 9,6,true);
		vertic.add(false);
		switcher.add(false);
		barrel.start();
		*/
		map.addEntity(EntityType.BARREL, 2, 2, false);
		map.addEntity(EntityType.BARREL, 6, 2, false);
		map.addEntity(EntityType.BARREL, 5, 4, false);
		map.addEntity(EntityType.BARREL, 2, 6, false);
		map.addEntity(EntityType.BARREL, 4, 8, false);
		
		//add fruit
		map.addEntity(EntityType.CHERRY, 4,3, false);
		map.addEntity(EntityType.BANANA, 3, 2, false);
		map.addEntity(EntityType.MANGO, 9, 4, false);
		map.addEntity(EntityType.BANANA, 1, 6, false);
		map.addEntity(EntityType.MANGO, 0, 9, false);
		map.addEntity(EntityType.CHERRY, 3, 6, false);
		map.addEntity(EntityType.CHERRY, 7, 8, false);
		map.addEntity(EntityType.BANANA, 1, 2, false);
		map.addEntity(EntityType.MANGO, 9, 9, false);
	}

	public void setUpLevelMed(){
		map = new Map(15,15);
		winSpace = new Space(14,7);
		map.setWinSpace(winSpace);
		barrelMove = 4;
		walls = new Vector<Entity>();
		switcher = new Vector<Boolean>();
		vertic = new Vector<Boolean>();
		// character
		map.addEntity(EntityType.CHARACTER,  7,0, false);
		//0 vertical
		//map.addEntity(EntityType.WALL,  6,0, false);
		map.addEntity(EntityType.WALL,  10,0, false);
		//walls.add(new Entity(EntityType.WALL,6,0, false));
		walls.add(new Entity(EntityType.WALL, 10,0, false));
		//1 vertical
		for(int i = 0; i <15;i++) {
			if(i == 0 || i ==1|| i ==2|| i ==4|| i ==6|| i ==7|| i ==8|| i ==10|| i ==11|| i ==12|| i ==13) {
				map.addEntity(EntityType.WALL,  i,1, false);
				walls.add(new Entity(EntityType.WALL, i,1, false));
			}
		}
		//2 vertical
		for(int i = 0; i <15;i++) {
			if(i==4||i==8||i==10) {
				map.addEntity(EntityType.WALL,i,2, false);
				walls.add(new Entity(EntityType.WALL, i,2, false));
			}
		}
		//3 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==2||i==4||i==6||i==7||i==8||i==10||i==12||i==14) {
				map.addEntity(EntityType.WALL,i,3, false);
				walls.add(new Entity(EntityType.WALL, i,3, false));
			}
		}
		//4 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==2||i==4||i==10||i==12||i==14) {
				map.addEntity(EntityType.WALL,  i,4, false);
				walls.add(new Entity(EntityType.WALL, i,4, false));
			}
		}
		//5 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==2||i==4||i==5||i==6||i==8/*||i==9*/||i==10||i==12||i==13||i==14) {
				map.addEntity(EntityType.WALL,  i,5, false);
				walls.add(new Entity(EntityType.WALL, i,5, false));
			}
		}
		//6 vertical
		map.addEntity(EntityType.WALL, 6, 6, false);
		map.addEntity(EntityType.WALL,  8,6, false);
		walls.add(new Entity(EntityType.WALL, 6,6, false));
		walls.add(new Entity(EntityType.WALL, 8,6, false));
		//7 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==4||/*i==6||*/i==8||i==10||i==11||i==12||i==13) {
				map.addEntity(EntityType.WALL,  i,7, false);
				walls.add(new Entity(EntityType.WALL, i,7, false));
			}
		}
		//8 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==3||i==4||i==8||i==10||i==11) {
				map.addEntity(EntityType.WALL,  i,8, false);
				walls.add(new Entity(EntityType.WALL, i,8, false));
			}
		}
		//9 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==3||i==8||i==13/*||i==14*/) {
				map.addEntity(EntityType.WALL,  i,9, false);
				walls.add(new Entity(EntityType.WALL, i,9, false));
			}
		}
		//10 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9||i==11||i==13) {
				map.addEntity(EntityType.WALL, i,10, false);
				walls.add(new Entity(EntityType.WALL, i,10, false));
			}
		}
		//11 vertical
		map.addEntity(EntityType.WALL, 11, 11, false);
		//map.addEntity(EntityType.WALL, 13,11, false);
		walls.add(new Entity(EntityType.WALL, 11,11, false));
		//walls.add(new Entity(EntityType.WALL, 13,11, false));
		//12 horiical
		for(int i = 0; i <15;i++) {
			if(i== 1 ||i== 2 ||i==3 ||i== 5||i==7||i==8||i==9||i==10||i==11||i==13) {
				map.addEntity(EntityType.WALL,  i,12, false);
				walls.add(new Entity(EntityType.WALL,i,12, false));
			}
		}
		//13 vertical
		for(int i = 0; i <15;i++) {
			if(i==1||i==3||i==5||i==8||i==10||i==13) {
				map.addEntity(EntityType.WALL,  i,13, false);
				walls.add(new Entity(EntityType.WALL, i,13, false));
			}
		}
		//14 vertical
		for(int i = 0; i <15;i++) {
			if(i==3||i==5||i==6||i==8) {
				map.addEntity(EntityType.WALL,  i,14, false);
				walls.add(new Entity(EntityType.WALL,i,14, false));
			}
		}
		/*
		map.addEntity(EntityType.BARREL, 2,5,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 4, 7,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 6,11,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 3,13,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 6, 3,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 7,0,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 8,6,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 11, 6,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 11,12,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 14,11,true);
		switcher.add(false);
		vertic.add(true);
		*/
		map.addEntity(EntityType.BARREL, 6, 0, false);
		map.addEntity(EntityType.BARREL, 7, 6, false);
		map.addEntity(EntityType.BARREL, 1, 2, false);
		map.addEntity(EntityType.BARREL, 3, 7, false);
		map.addEntity(EntityType.BARREL, 5, 9, false);
		map.addEntity(EntityType.BARREL, 6, 7, false);
		map.addEntity(EntityType.BARREL, 7, 8, false);
		map.addEntity(EntityType.BARREL, 9, 5, false);
		map.addEntity(EntityType.BARREL, 12, 8, false);
		map.addEntity(EntityType.BARREL, 14, 6, false);
		map.addEntity(EntityType.BARREL, 13, 11, false);
		
		map.addEntity(EntityType.BANANA, 2, 0, false);
		map.addEntity(EntityType.BANANA, 11, 0, false);
		map.addEntity(EntityType.BANANA, 2, 9, false);
		map.addEntity(EntityType.BANANA, 9, 13, false);
		map.addEntity(EntityType.BANANA, 14, 10, false);
		map.addEntity(EntityType.BANANA, 7, 9, false);
		map.addEntity(EntityType.BANANA, 6, 9, false);
		map.addEntity(EntityType.BANANA, 6, 8, false);
		map.addEntity(EntityType.CHERRY, 0, 2, false);
		map.addEntity(EntityType.CHERRY, 2, 13, false);
		map.addEntity(EntityType.CHERRY, 11, 9, false);
		map.addEntity(EntityType.CHERRY, 7, 2, false);
		map.addEntity(EntityType.CHERRY, 7, 13, false);
		map.addEntity(EntityType.MANGO, 0, 5, false);
		map.addEntity(EntityType.MANGO, 7, 5, false);
		map.addEntity(EntityType.MANGO, 5, 6, false);
		map.addEntity(EntityType.MANGO, 13, 4, false);
		map.addEntity(EntityType.MANGO, 4, 14, false);
	}
	public void setUpLevelHard(){
		// to add
		map = new Map(25,25);
		winSpace = new Space(11,24);
		map.setWinSpace(winSpace);
		barrelMove = 6;
		walls = new Vector<Entity>();
		switcher = new Vector<Boolean>();
		vertic = new Vector<Boolean>();
		
		//player
		map.addEntity(EntityType.CHARACTER, 0, 0, false);
		
		//0 vertical
		for (int i = 0; i < 25; i++) {
			if(/*i==8||*/i==20||i==21||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,0, false);
				walls.add(new Entity(EntityType.WALL, i,0, false));	
			}
		}	
		//1 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==2||i==3||i==4||i==5||i==6||i==8||i==9||i==10||i==11||i==12||i==13||i==14||i==15||i==16||i==17||i==18) {
				map.addEntity(EntityType.WALL, i,1, false);
				walls.add(new Entity(EntityType.WALL, i,1, false));	
			}
		}
		//2 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==6||i==8||i==18||i==19||i==20||i==21||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,2, false);
				walls.add(new Entity(EntityType.WALL,i,2, false));	
			}
		}
		//3 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==1||i==2||i==3||i==4||i==6||i==7||i==8||i==10||i==11||i==12||i==13||i==14||i==16) {
				map.addEntity(EntityType.WALL,i,3, false);
				walls.add(new Entity(EntityType.WALL,i,3, false));	
			}
		}
		//4 vertical
		for (int i = 0; i < 25; i++) {
			if(/*i==4||*/i==14||i==16||i==18||i==19||i==20||i==21||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,4, false);
				walls.add(new Entity(EntityType.WALL,i,4, false));	
			}
		}
		//5 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==3||i==4||i==6||i==8||i==9||i==10||i==11||i==12||i==14||i==16||i==18) {
				map.addEntity(EntityType.WALL, i,5, false);
				walls.add(new Entity(EntityType.WALL, i,5, false));	
			}
		}
		//6 vertical
		for (int i = 0; i < 25; i++) {
			if(i==6||i==12||i==14||i==16||i==18||i==20||i==21||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,6, false);
				walls.add(new Entity(EntityType.WALL, i,6, false));	
			}
		}
		//7 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==1||i==2||i==3||i==4||i==6||i==7||i==8||i==9||i==10||i==11||i==12||i==14||i==16||i==17||i==18) {
				map.addEntity(EntityType.WALL, i,7, false);
				walls.add(new Entity(EntityType.WALL, i,7, false));	
			}
		}
		//8 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==6||i==12||i==14||i==20||i==21||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,8, false);
				walls.add(new Entity(EntityType.WALL, i,8, false));	
			}
		}
		//9 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==1||i==2||i==3||i==4||i==5||i==6||i==8||i==9||i==10||i==11||i==12||i==14||i==15||i==16||i==17||i==18||i==19||i==20) {
				map.addEntity(EntityType.WALL, i,9, false);
				walls.add(new Entity(EntityType.WALL, i,9, false));	
			}
		}
		//10 vertical
		for (int i = 0; i < 25; i++) {
			if(i==8||i==14||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,10, false);
				walls.add(new Entity(EntityType.WALL, i,10, false));	
			}
		}
		//11 vertical
		for (int i = 0; i < 25; i++) {
			if(/*i==0||*/i==1||i==2||i==4||i==5||i==6||i==7||i==8||i==9||i==10||i==11||i==12||i==14||i==16||i==17||i==18||i==19||i==20||i==21||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,11, false);
				walls.add(new Entity(EntityType.WALL, i,11, false));	
			}
		}
		//12 vertical
		for (int i = 0; i < 25; i++) {
			if(i==2||i==4||i==12||i==14||i==17/*||i==23*/) {
				map.addEntity(EntityType.WALL, i,12, false);
				walls.add(new Entity(EntityType.WALL, i,12, false));	
			}
		}
		//13 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==4||i==5||i==6||i==7||i==9||i==10||i==12||i==14||i==16||i==17||i==19||i==20||i==21||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,13, false);
				walls.add(new Entity(EntityType.WALL, i,13, false));	
			}
		}
		//14 vertical
		for (int i = 0; i < 25; i++) {
			if(/*i==1||*/i==9||i==10||i==12||i==14||i==16||i==19) {
				map.addEntity(EntityType.WALL, i,14, false);
				walls.add(new Entity(EntityType.WALL, i,14, false));	
			}
		}
		//15 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9||i==10||i==12||i==14||i==16||i==18||i==19||i==20||i==21||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,15, false);
				walls.add(new Entity(EntityType.WALL, i,15, false));	
			}
		}
		//16 vertical
		map.addEntity(EntityType.WALL, 14,16, false);
		walls.add(new Entity(EntityType.WALL, 14,16, false));
		//17 vert fix
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9||i==10||i==11||i==12||i==13||i==14||i==16||i==17||i==18) {
				map.addEntity(EntityType.WALL, i,17, false);
				walls.add(new Entity(EntityType.WALL, i,17, false));	
			}
		}
		//18 vertical
		for (int i = 0; i < 25; i++) {
			if(i==18||i==20||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,18, false);
				walls.add(new Entity(EntityType.WALL, i,18, false));	
			}
		}
		//19 vertical
		for (int i = 0; i < 25; i++) {
			if(i==0||i==1||i==2||i==3||i==4||i==6||i==8||i==9||i==10||i==11||i==12||i==14||i==15||i==16||i==18||i==20||i==22||i==23) {
				map.addEntity(EntityType.WALL, i,19, false);
				walls.add(new Entity(EntityType.WALL, i,19, false));	
			}
		}
		//20 vertical fix
		for (int i = 0; i < 25; i++) {
			if(i==4||i==6||i==8||i==12||i==16||i==18||i==20||i==22) {
				map.addEntity(EntityType.WALL, i,20, false);
				walls.add(new Entity(EntityType.WALL,i,20, false));	
			}
		}
		//21 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==3||i==4||i==6||i==8||i==10||i==12||i==13||i==14||i==16||i==18||i==20||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL,i,21, false);
				walls.add(new Entity(EntityType.WALL, i,21, false));	
			}
		}
		//22 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==6||i==8||i==10||i==14||i==16||i==18||i==20||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,22, false);
				walls.add(new Entity(EntityType.WALL, i,22, false));	
			}
		}
		//23 vertical
		for (int i = 0; i < 25; i++) {
			if(i==1||i==2||i==3||i==4||i==5||i==6||i==8||i==10||i==11||i==12||i==14||i==15||i==16||i==18||i==20||i==21||i==22||i==23||i==24) {
				map.addEntity(EntityType.WALL, i,23, false);
				walls.add(new Entity(EntityType.WALL, i,23, false));	
			}
		}
		//24 vertical
		for (int i = 0; i < 25; i++) {
			if(i==8||i==12||i==18) {
				map.addEntity(EntityType.WALL, i,24, false);
				walls.add(new Entity(EntityType.WALL, i,24, false));	
			}
		}
		//barrels
		/*
		map.addEntity(EntityType.BARREL, 19,6,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 19,10,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 2,10,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL,  2,6,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 20,17,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 23,16,false);
		switcher.add(false);
		vertic.add(false);
		map.addEntity(EntityType.BARREL, 0,15,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 19,0,true);
		switcher.add(false);
		vertic.add(true);
		map.addEntity(EntityType.BARREL, 7,5,true);
		switcher.add(false);
		vertic.add(true);
		*/
		map.addEntity(EntityType.BARREL, 5, 5, false);
		map.addEntity(EntityType.BARREL, 19, 0, false);
		map.addEntity(EntityType.BARREL, 0, 15, false);
		map.addEntity(EntityType.BARREL, 19, 6, false);
		map.addEntity(EntityType.BARREL, 23, 12, false);
		map.addEntity(EntityType.BARREL, 20, 16, false);
		map.addEntity(EntityType.BARREL, 24, 16, false);
		map.addEntity(EntityType.BARREL, 22, 17, false);
		
		//fruit
		map.addEntity(EntityType.BANANA, 7,2, false);
		map.addEntity(EntityType.BANANA, 1,12, false);
		map.addEntity(EntityType.BANANA, 5,12, false);
		map.addEntity(EntityType.BANANA, 9,10, false);
		map.addEntity(EntityType.BANANA, 17,6, false);
		map.addEntity(EntityType.BANANA, 15,22, false);
		map.addEntity(EntityType.BANANA, 24,3, false);
		map.addEntity(EntityType.BANANA, 23,22, false);
		map.addEntity(EntityType.BANANA, 15,3, false);
		map.addEntity(EntityType.BANANA, 18, 0, false);
		
		map.addEntity(EntityType.CHERRY, 3,4, false);
		map.addEntity(EntityType.CHERRY, 3,20, false);
		map.addEntity(EntityType.CHERRY, 11,6, false);
		map.addEntity(EntityType.CHERRY, 9,0, false);
		map.addEntity(EntityType.CHERRY, 13,4, false);
		map.addEntity(EntityType.CHERRY, 22,12, false);
		map.addEntity(EntityType.CHERRY, 16,12, false);
		map.addEntity(EntityType.CHERRY, 23,20, false);
		map.addEntity(EntityType.CHERRY, 15,17, false);
		
		map.addEntity(EntityType.MANGO, 1,8, false);
		map.addEntity(EntityType.MANGO, 2,22, false);
		map.addEntity(EntityType.MANGO, 11,8, false);
		map.addEntity(EntityType.MANGO, 0,10, false);
		map.addEntity(EntityType.MANGO, 13,16, false);
		map.addEntity(EntityType.MANGO, 24,7, false);
		map.addEntity(EntityType.MANGO, 20,14, false);
		map.addEntity(EntityType.MANGO, 24, 24, false);
		map.addEntity(EntityType.MANGO, 7,18, false);
	}
	public Space getWin() {
		return map.getWinSpace();
	}
	public String toString() {
		return null;
	}
	public void setWin(Space s) {
		map.setWinSpace(s);
	}
	public int getScore() {
		return score;
	}
	public int getNumRows() {
		return map.getNumRows();
	}
	public int getNumCols() {
		return map.getNumCols();
	}
	public Space getCharSpace() {
		return map.getCharacterSpace();
	}
	public void setCharSpace(int row, int col) {
		map.setCharacterSpace(row,col);
		
	}
	public void collision(Space s){
		if (!map.wallCollision(s)) {
			if (map.bananaCollision(s)) {
				score += 10;
				setCharSpace(s.getRow(), s.getCol());
				System.out.println("Banana collected!! +10 points");
			}
			if (map.cherryCollision(s)) {
				score += 7;
				setCharSpace(s.getRow(), s.getCol());
				System.out.println("Cherry collected!! +7 points");
			}
			if (map.mangoCollision(s)) {
				System.out.println("Cherry collected!! +7 points");
				score += 5;
				setCharSpace(s.getRow(), s.getCol());
			}
			if (map.barrelCollision(s)) {
				System.out.println("Barrel collsion detected!!!");
				program.removeLife();
				map.resetChara();
				
			}
		}
	}
	public int timer() {
		return barrel.getDelay();
	}
	public int moveBarrel(int i) {
		barrelMove = i;
		return barrelMove;
	}
	
	public Vector<Entity> getWalls() {
		return walls;
	}
	
	public Vector<Entity> getBarrels() {
		return map.getBarrels();
	}
	
	public Vector<Entity> getBananas() {
		return map.getBananas();
	}
	
	public Vector<Entity> getCherries() {
		return map.getCherries();
	}
	
	public Vector<Entity> getMangos() {
		return map.getMangoes();
	}
	
	public void checkPath() {
		Vector<Entity> checking = map.getBarrels();
		Boolean catcher = null;
		System.out.println("Now checking all barrel paths");
		int o = 0;
		
		for(Entity i: checking) {
			
			if(vertic.elementAt(o)) {
				if(switcher.elementAt(o)) {
					if (i.getRow()-1 >= 0) {
						if(map.wallCollision(new Space(i.getRow()-1,i.getCol()))) {
							catcher= !switcher.elementAt(o);
							switcher.setElementAt(catcher,o);
						}
					}else {
						continue;
					}
				}else {
					if (i.getRow()+1 < 10) {
						if(map.wallCollision(new Space(i.getRow()+1,i.getCol()))) {
							catcher= !switcher.elementAt(o);
							switcher.setElementAt(catcher,o);
						}
					}else {
						continue;
					}
				}
				
			}else {
				if(switcher.elementAt(o)) {
					if (i.getCol()-1 >= 0) {
						if(map.wallCollision(new Space(i.getRow(),i.getCol()-1))) {
							catcher= !switcher.elementAt(o);
							switcher.setElementAt(catcher,o);
						}
					}else {
						continue;
					}
				}else {
					if (i.getCol()+1 < 10) {
						if(map.wallCollision(new Space(i.getRow(),i.getCol()+1))) {
							catcher= !switcher.elementAt(o);
							switcher.setElementAt(catcher,o);
						}
					}else {
						continue;
					}
				}
			}
			o++;
		}
	}
	public String getMapSize() {
		return mapSize;
	}
	
	public Vector<Boolean> getSwitcher() {
		return switcher;
	}
	
	public Vector<Boolean> getVertic() {
		return vertic;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("now checking movement");
		//checkPath();
		System.out.println("Now moving barrels");
		//if (timerCount < 6) {
			//map.moveBarrels(1, vertic,switcher);
		//}
		//else if (timerCount >= 6 && timerCount < 11) {
			//map.moveBarrels(-1, vertic, switcher);
		//}
		//++timerCount;
		
		//if (timerCount == 11) {
			//timerCount = 0;
		//}
	}

	
	
	@Override
	public void keyPressed(KeyEvent e) {
		Space movenment;
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        System.out.println("going left");
	        movenment = new Space(map.getCharacterRow(),map.getCharacterCol()-1);
	        map.moveChara(movenment);
	        collision(movenment);
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	System.out.println("going right");
	    	movenment = new Space(map.getCharacterRow(),map.getCharacterCol()+1);
	    	map.moveChara(movenment);
	    }

	    if (key == KeyEvent.VK_UP) {
	    	System.out.println("going up");
	    	movenment = new Space(map.getCharacterRow()-1,map.getCharacterCol());
	    	map.moveChara(movenment);
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	System.out.println("going down");
	    	movenment = new Space(map.getCharacterRow()+1,map.getCharacterCol());
	    	map.moveChara(movenment);
	    	collision(movenment);
	    }
	    
	    
	    
	    if (key == KeyEvent.VK_A) {
	        System.out.println("going left (WASD)");
	        movenment = new Space(map.getCharacterRow(),map.getCharacterCol()-1);
	        map.moveChara(movenment);
	        collision(movenment);
	    }

	    if (key == KeyEvent.VK_D) {
	    	System.out.println("going right (WASD)");
	    	movenment = new Space(map.getCharacterRow(),map.getCharacterCol()+1);
	    	map.moveChara(movenment);
	    	collision(movenment);
	    }

	    if (key == KeyEvent.VK_W) {
	    	System.out.println("going up (WASD)");
	    	movenment = new Space(map.getCharacterRow()-1,map.getCharacterCol());
	    	map.moveChara(movenment);
	    	collision(movenment);
	    }

	    if (key == KeyEvent.VK_S) {
	    	System.out.println("going down (WASD)");
	    	movenment = new Space(map.getCharacterRow()+1,map.getCharacterCol());
	    	map.moveChara(movenment);
	    	collision(movenment);
	    }
	}
	
	public static void main(String[] args) {
		//new Level().start();
	}


	
	
}
