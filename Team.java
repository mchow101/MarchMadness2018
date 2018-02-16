public class Team {
	private int num;
	private Team opponent;
	
	public Team(int num) {
		this.num = num;
	}
	
	public void setOpponent(Team team) { this.opponent = team; }
	
	public Team getOpponent() {	return this.opponent; }
	
	public String toString() { return "blah" + num; }
	
	public int getNum() { return num; }
}
