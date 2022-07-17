import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.*; 
 
public class LeaderBoard {
	protected String playerName;
	protected int playerScore;
	public Object ScoreCount = "Score: " + playerName;// intialized new scorecount 
	JLabel scoreLabel = new JLabel("Score: 0"); // //added jlabel to display the single line text


	
	private TreeMap<Integer,String > easy = new TreeMap<Integer,String>(Collections.reverseOrder());
	private TreeMap<Integer,String> medium = new TreeMap<Integer,String>(Collections.reverseOrder());
	private TreeMap<Integer,String> hard = new TreeMap<Integer,String>(Collections.reverseOrder());

	LeaderBoard(String n,String m, int s){
		
		
		File f = new File("leaderboard.txt"); 
		if(!f.exists())
		{
			makeFile();
			//Scores that will be on the leaderboard so it's not empty
			easy.put(20, "jj");
			easy.put(23, "hat");
			easy.put(19, "iop");
			easy.put(22, "koi");
			easy.put(26, "gep");
			
			medium.put(35, "uop");
			medium.put(37, "yike");
			medium.put(31, "pet");
			medium.put(32, "tate");
			medium.put(30, "gom");
			
			hard.put(45, "kil");
			hard.put(46, "huy");
			hard.put(48, "ruy");
			hard.put(38, "bad");
			hard.put(47,"chris");
			
			
			
			if(m=="easy")
			{
				easy.put(s,n);
			}
			else if(m=="medium")
			{
				medium.put(s,n);
			}
			else
			{
				hard.put(s,n);
			}
			writeFile();
		}
		else
		{
			readFile();
			writeFile();
		}
		readOnly();
	}
	
	public static void main(String[] args) {
		new LeaderBoard("huk","easy",25);
		
	}
	public void updateLeaderboard(String[]args, Object player) {
	 	this.ScoreCount = player; 
        new LeaderBoard(playerName,playerName, playerScore);
        
	}
	 public void GameEntry(String name, int score) {
	    	this.playerName = name;
	    	this.playerScore = score;
	    }
	public void setPlayerName(String playerName, int board) {
			this.playerName = playerName;
			this.playerScore = board; // set for the first player to insert name 
		}
	public void setPlayerScore(int playerScore) {
			this.playerScore = playerScore;
		}
    public String getPlayerScore(String playerScore) {
			return playerScore;
		}
	public int getNumberOfEntries(int numOfEntries) {
			return numOfEntries;
		}
	
	public void add(String userName,String size, int score) {
		if(size == "easy") {
			easy.put(score,userName);
		}else if(size == "medium") {
			medium.put(score,userName);
		}else{
			hard.put(score,userName);
		}
		
	}
	
	public void makeFile()
	{
		try
		{
			Writer output = null;
		    File file = new File("leaderboard.txt");
		    output = new BufferedWriter(new FileWriter(file));

		    String toWrite = "Easy\n\n"+"Medium\n\n"+"Hard";
		    output.append(toWrite);
		        

		    output.close();
		}catch(IOException ioe) {}
		

	    
	}

	public void writeFile()
	{
		try
		{
			String filename= "leaderboard.txt";
		    FileWriter fw = new FileWriter(filename,false);
		    
		    fw.write("Easy   ");
		    
		    int i = 0;
		    
		    
		    for (Entry<Integer, String> mapElement : easy.entrySet()) {
	            int key = mapElement.getKey();
	            String value = (mapElement.getValue());
	            
	            
	            if(i<5)
			    {
	            	fw.write("\t"+(i+1)+". "+value+" "+key+"\t");
			    }
	            i++;
	            
		    }
		    
		    i = 0;
		    
		    
		    fw.write("\n\nMedium");
		    for (Entry<Integer, String> mapElement : medium.entrySet()) {
	            int key = mapElement.getKey();
	            String value = (mapElement.getValue());
	            
	            if(i<5)
			    {
	            	fw.write("\t"+(i+1)+". "+value+" "+key+"\t");
			    }
	            i++;
		    }
		    
		    
		    i = 0;
		   
		    fw.write("\n\nHard   ");
		    for (Entry<Integer, String> mapElement : hard.entrySet()) {
	            int key = mapElement.getKey();
	            String value = (mapElement.getValue());
	            
	            
	            if(i<5)
			    {
	            	fw.write("\t"+(i+1)+". "+value+" "+key+"\t");
			    }
	            i++;
		    }
		    
		    fw.close();
		}catch(IOException ioe) {}
		
	}
	public void readFile()
	{
		try
		{
			boolean nextv = false;
			boolean nextk = false;
			int key = 0;
			int i=0;
			String value = "";
			Scanner sc2 = new Scanner(new File("leaderboard.txt"));
			
			 while (sc2.hasNextLine()) {
		            Scanner s2 = new Scanner(sc2.nextLine());
		        while (s2.hasNext()) {
		            String st = s2.next();
		            
		            if((st.equals("5.")||st.equals("4.")||st.equals("3.")||st.equals("2.")||st.equals("1.")))
		            {
		            	nextv=true;
		            }
		            else if(nextv)
		            {
		            	value = st;
		            	nextv=false;
		            	nextk=true;
		            }
		            else if(nextk)
		            {
		            	key=Integer.parseInt(st);
		            	nextk=false;
		            	 if(i<16)
				            {
				            	easy.put(key, value);
				            }
				            else if(i>15&&i<32)
				            {
				            	medium.put(key, value);
				            }
				            else
				            {
				            	hard.put(key, value);
				            }
		            }
		            
		           
		            i++;
		        }   
	        }
			 
				   
		}catch(IOException ioe) {}
		
	}
	public void gameScore()
	{
		int score = 0;
	    score++;

	    scoreLabel.setText("Score: " + score);
	}
	
	public void readOnly()
	{
		try
		{
			FileReader fr = new FileReader("leaderboard.txt");
			int ch = 0;
			while ((ch=fr.read())!=-1)
			{
				System.out.print((char)ch);
			}
			fr.close();
		}catch(IOException ioe) {}
	}
	

    public void Leaderboard(String name, int score)
    {
    }
  
	public String getValues()
	{
		String st="";
		st+=("Easy     ");
	    
	    int i = 0;
	    
	    
	    for (Entry<Integer, String> mapElement : easy.entrySet()) {
            int key = mapElement.getKey();
            String value = (mapElement.getValue());
            
            
            if(i<5)
		    {
            	st+=("   "+(i+1)+". "+value+" "+key+"   ");
		    }
            i++;
	    }
	    
	    i = 0;
	    
	    
	    st+=("\n\n\n\nMedium");
	    for (Entry<Integer, String> mapElement : medium.entrySet()) {
            int key = mapElement.getKey();
            String value = (mapElement.getValue());
            
            if(i<5)
		    {
            	st+=("   "+(i+1)+". "+value+" "+key+"   ");
		    }
            i++;
	    }
	    
	    
	    i = 0;
	   
	    st+=("\n\n\n\nHard     ");
	    for (Entry<Integer, String> mapElement : hard.entrySet()) {
            int key = mapElement.getKey();
            String value = (mapElement.getValue());
            
            
            if(i<5)
		    {
            	st+=("   "+(i+1)+". "+value+" "+key+"   ");
		    }
            i++;
	    }
		return st;
	}
	
}
