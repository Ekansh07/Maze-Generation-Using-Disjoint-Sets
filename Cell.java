public class Cell {
	
	int value;
	boolean wallLeft, wallBottom;
	
	public Cell(int value, boolean wallLeft, boolean wallBottom) {
		this.value = value;		
		this.wallLeft = wallLeft;
		this.wallBottom = wallBottom;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isWallLeft() {
		return wallLeft;
	}

	public void setWallLeft(boolean wallLeft) {
		this.wallLeft = wallLeft;
	}

	public boolean isWallBottom() {
		return wallBottom;
	}

	public void setWallBottom(boolean wallBottom) {
		this.wallBottom = wallBottom;
	}
	
}