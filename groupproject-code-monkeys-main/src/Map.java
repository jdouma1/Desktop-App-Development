import java.util.ArrayList;
import java.util.Vector;

public class Map {
	private Entity[][] map;
	private Entity character = new Entity(EntityType.CHARACTER,0,0,false);
	private Space startSpace = new Space(0,0);
	private int numRows;
	private int numCols;
	private int numEntities = 0;
	private Space winningSpace = new Space(0,0);
	Vector<Entity> barrels = new Vector<Entity>();
	Vector<Entity> cherries = new Vector<Entity>();
	Vector<Entity> mangoes = new Vector<Entity>();
	Vector<Entity> bananas = new Vector<Entity>();
	
	public Map(int r, int c) {
		map = new Entity[r][c];
		numRows = r;
		numCols = c;
	}
	
	public boolean canMove(Space s) {
		if (map[s.getRow()][s.getCol()].getType() == EntityType.WALL) {
			return false;
		}
		else if (s.getRow() >= numRows || s.getCol() >= numCols || s.getRow() < 0 || s.getCol() < 0) {
			return false;
		}
		return true;
	}
	
	public boolean moveChara(Space s) {
		if (canMove(s)) {
			character.setSpace(s.getRow(), s.getCol());
			//map[character.getRow()][character.getCol()] = null;
			//map[s.getRow()][s.getCol()] = character;
		}
		return canMove(s);
	}
	
	public String toString() {
		return "";
	}
	
	public int getNumCols() {
		return numCols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public Entity getEnt(Space s) {
		if (s.getRow() >= 0 && s.getCol() >= 0 && s.getRow() <= getNumRows() && s.getCol() <= getNumCols()) {
			return map[s.getRow()][s.getCol()];
		}
		return null;
	}
	
	public int getNumEnt() {
		return numEntities;
	}
	
	public void setWinSpace(Space winningSpace) {
		this.winningSpace = winningSpace;
	}
	
	public Space getWinSpace() {
		return winningSpace;
	}
	
	public Space getStartSpace() {
		return startSpace;
	}
	
	public Space getCharacterSpace() {
		return character.getSpace();
	}
	public void setCharacterSpace(int row, int col) {
		//map[character.getRow()][character.getCol()] = null;
		character.setSpace(row, col);
		//map[row][col] = character;
		
	}
	public int getCharacterRow() {
		return character.getRow();
	}
	
	public int getCharacterCol() {
		return character.getCol();
	}
	
	public void resetChara() {
		character.setSpace(startSpace.getRow(), startSpace.getCol());
	}
	
	public void addEntity(EntityType type, int r, int c, boolean b) {
		Entity e = new Entity(type, r, c, b);
		map[r][c] = e;
		
		if (type != EntityType.WALL) {
			numEntities++;
		}
		
		if (type == EntityType.BARREL) {
			barrels.add(e);
		}
		
		if (type == EntityType.CHARACTER) {
			//map[r][c] = null;
			character = new Entity(type, r, c, b);
			startSpace = new Space(r, c);
		}
		
		if (type == EntityType.CHERRY) {
			cherries.add(e);
		}
		
		if (type == EntityType.MANGO) {
			mangoes.add(e);
		}
		
		if (type == EntityType.BANANA) {
			bananas.add(e);
		}
		
		
	}
	
	public Vector<Entity> getBarrels() {
		return barrels;
	}
	
	public Vector<Entity> getCherries() {
		return cherries;
	}
	
	public Vector<Entity> getMangoes() {
		return mangoes;
	}
	
	public Vector<Entity> getBananas() {
		return bananas;
	}
	
	public boolean moveBarrels(int nSpaces, Vector<Boolean> vertical, Vector<Boolean>switchy) {
		int p =0;
		for (Entity i: barrels) {
			if (vertical.elementAt(p)) {
				if(switchy.elementAt(p)) {
					if (i.getRow()-1>0) {
					map[i.getRow()][i.getCol()] = null;
					i.setSpace(i.getRow()-1, i.getCol());
					map[i.getRow()][i.getCol()] = i;
					}
				}else {
					if (i.getRow()+1<getNumRows()) {
					map[i.getRow()][i.getCol()] = null;
					i.setSpace(i.getRow()+1, i.getCol());
					map[i.getRow()][i.getCol()] = i;
					}
				}
			}else { 
				if(switchy.elementAt(p)) {
					if (i.getCol()-1>0) {
					map[i.getRow()][i.getCol()] = null;
					i.setSpace(i.getRow(), i.getCol()-1);
					map[i.getRow()][i.getCol()] = i;
					}
				}else {
					if (i.getCol()+1<getNumCols()) {
					map[i.getRow()][i.getCol()] = null;
					i.setSpace(i.getRow(), i.getCol()+1);
					map[i.getRow()][i.getCol()] = i;
					}
				}
			}
			p++;
		}
		return false;
	}
	
	public boolean wallCollision (Space s) {
		if(s.getRow()>=numRows||s.getCol()>=numCols||s.getRow()<0||s.getCol()<0) {
			return true;
		}
		if (map[s.getRow()][s.getCol()] == null) {
			return false;
		}
		if (map[s.getRow()][s.getCol()].getType() == EntityType.WALL) {
			return true;
		}
		return false;
	}
	
	public boolean barrelCollision(Space s) {
		if(map[s.getRow()][s.getCol()] ==  null) {
			return false;
		}
		if (map[s.getRow()][s.getCol()].getType() == EntityType.BARREL) {
			return true;
		}
		return false;
	}
	
	public boolean bananaCollision(Space s) {
		if(map[s.getRow()][s.getCol()] ==  null) {
			return false;
		}
		if (map[s.getRow()][s.getCol()].getType() == EntityType.BANANA) {
			map[s.getRow()][s.getCol()] = null;
			--numEntities;
			return true;
		}
		return false;
	}
		
	public boolean cherryCollision(Space s) {
		if(map[s.getRow()][s.getCol()] ==  null) {
			return false;
		}
		if (map[s.getRow()][s.getCol()].getType() == EntityType.CHERRY) {
			map[s.getRow()][s.getCol()] = null;
			--numEntities;
			return true;
		}
		return false;
	}
		
	public boolean mangoCollision(Space s) {
		if(map[s.getRow()][s.getCol()] ==  null) {
			return false;
		}
		if (map[s.getRow()][s.getCol()].getType() == EntityType.MANGO) {
			map[s.getRow()][s.getCol()] = null;
			--numEntities;
			return true;
		}
		return false;
	}

	
}
