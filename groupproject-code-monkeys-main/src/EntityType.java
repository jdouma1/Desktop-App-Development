
public enum EntityType {
	BANANA, CHERRY, MANGO, BARREL, WALL, CHARACTER;
	
	public String toString() {
		switch(this) {
			case BANANA: return "banana";
			case CHERRY: return "cherry";
			case MANGO: return "mango";
			case BARREL: return "barrel";
			case WALL: return "wall";
			case CHARACTER: return "character";
		}
		return "n/a";
	}
}
