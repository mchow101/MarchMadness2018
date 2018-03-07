import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Rank	Team	Conf	W-L	AdjEM	AdjO	AdjD	AdjT	Luck	AdjEM	OppO	OppD	AdjEM

public class Team implements Comparable<Team> {
	private String name;
	private int num; //value determining how good team is
	private int index; //ArrayList organization
	private Team opponent; //current opposing Team
	private boolean winner, played; //whether or not win, played yet in current round
	private static int line = 0; //keeping track of data input
	private static Scanner scan;
	
	public Team(int num) throws FileNotFoundException {
		if(line == 0)
			scan = new Scanner(new File("2016data.txt"));
		this.setStats();
		this.winner = false;
		this.played = false;
		scan.nextLine();
		line++;
	}
	
	public void setStats() {
		this.num = scan.nextInt();
		this.name = scan.next();
	}
	
	public void setOpponent(Team team) { this.opponent = team; }
	
	public Team getOpponent() {	return this.opponent; }
	
	public String toString() { return this.name; }
	
	public int getNum() { return this.num; }
	
	public int getIndex() { return this.index; }

	public void setIndex(int i) { this.num = i; }
	
	public void setWinner(boolean b) { this.winner = b; }
	
	public boolean isWinner() { return this.winner; }
	
	public static void reset(ArrayList<Team> teams) { 
		for(int i = 0; i < teams.size(); i++) teams.get(i).played = false; 
		Collections.sort(teams);
	}
	
	public void played() { this.played = true; }
	
	public boolean getPlayed() { return this.played; }
	
	public int compareTo(Team t) { return this.index - t.index; }
}
