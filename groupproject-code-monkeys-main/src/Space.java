public class Space {
	private int row;
	private int col;
	
	Space(int r, int c) {
		row = r;
		col = c;
	}
	
	void setRow(int r) {
		row = r;
	}
	
	void setCol(int c) {
		col = c;
	}
	
	int getRow() {
		return row;
	}
	
	int getCol() {
		return col;
	}
}