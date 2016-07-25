package ua.nenya.domain;

public class Horse {
	
	private int number;
	private String name;
	private int odds;
	private String didWin;
	
	public Horse(int number, String name, int odds) {
		this.number = number;
		this.name = name;
		this.odds = odds;
	}

	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}

	public int getOdds() {
		return odds;
	}

	public String getDidWin() {
		return didWin;
	}

	public void setDidWin(String didWin) {
		this.didWin = didWin;
	}
	
}
