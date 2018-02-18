import java.util.ArrayList;
import java.util.Collections;

//Rank	Team	Conf	W-L	AdjEM	AdjO	AdjD	AdjT	Luck	AdjEM	OppO	OppD	AdjEM

public class Team {
	private int num;
	private int index;
	private Team opponent;
	private boolean winner;
	
	public Team(int num) {
		this.num = num;
		this.index = num;
	}
	
	public void setOpponent(Team team) { this.opponent = team; }
	
	public Team getOpponent() {	return this.opponent; }
	
	public String toString() { return "blah" + index; }
	
	public int getNum() { return num; }

	public void setNum(int i) { this.num = i; }
	
	public void setWinner(boolean b) { this.winner = b; }
	
	public boolean isWinner() { return this.winner; }
}