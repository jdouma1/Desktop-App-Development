public class Entity {
	private Space space;
	private int row;
	private int col;
	private EntityType type;
	private boolean isVertical;
	
	public Entity(EntityType tp, int row, int col, boolean vertical){
		type = tp ;
		space = new Space(row,col);
		this.row = row;
		this.col = col;
		isVertical = vertical;
	}
	
	void move(int nRow, int nCol){
		space = new Space(nRow, nCol);
	}
	
	public Space getSpace() {
		return space;
	}
	
	public void setSpace(int r, int c) {
		space = new Space(r, c);
	}
	
	public int getRow() {
		return space.getRow();
	}
	
	public int getCol(){
		return space.getCol();
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setCol(int c) {
		col = c;
	}
	
	public boolean vertical() {
		return isVertical;
	}
	
	public EntityType getType(){
		return type;
	}
	
	public String toString() {
		return "";	
	}	
}

