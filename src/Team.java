import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Rank	Team	Conf	W-L	AdjEM	AdjO	AdjD	AdjT	Luck	AdjEM	OppO	OppD	AdjEM

public class Team {
	private String name;
	private double num; //value determining how good team is
	private int index; //ArrayList organization
	private Team opponent; //current opposing Team
	private boolean winner, played; //whether or not win, played yet in current round
	private double adjem, adjo, adjd, adjt, luck; //stats
	private static int line = 0; //keeping track of data input
	private static Scanner scan;
	
	public Team(int index) throws FileNotFoundException {
		//setup scanner and team information
		if(line == 0)
			scan = new Scanner(new File("2017data.txt"));
		this.index = index + 1;
		this.setStats();
		this.winner = false;
		this.played = false;
		scan.nextLine();
		line++;
	}
	
	public void setStats() {
		//read in values
		scan.nextInt();
		name = scan.next();
		scan.next();
		while(!scan.hasNextDouble()) scan.next();
		adjem = scan.nextDouble();
		while(!scan.hasNextDouble()) scan.next();
		adjo = scan.nextDouble();
		while(!scan.hasNextDouble()) scan.next();
		scan.next();
		adjd = scan.nextDouble();
		while(!scan.hasNextDouble()) scan.next();
		scan.next();
		adjt = scan.nextDouble();
		while(!scan.hasNextDouble()) scan.next();
		scan.next();
		luck = scan.nextDouble();
		System.out.println(this.name + " " + this.adjem + " " + this.adjo + " " + this.adjd + "  " + this.adjt + " " + this.luck);
		//calculate team's num
		double temp;
		temp = 3*(adjo - adjd);
		temp *= adjt*2;
		if(Math.random() < Math.abs(luck)) temp += 10*luck;
		num = temp;
	}
	
	//setters and getters
	public void setOpponent(Team team) { this.opponent = team; }
	
	public Team getOpponent() { return this.opponent; }
	
	public String toString() { return "" + this.name; }
	
	public double getNum() { System.out.println(this.name + " " + this.num); return this.num; }
	
	public int getIndex() { return this.index; }

	public void setIndex(int i) { this.index = i; }
	
	public void setWinner(boolean b) { this.winner = b; }
	
	public boolean isWinner() { return this.winner; }
		
	//prevents team from "playing" multiple times in a round
	public void played() { this.played = true; }
	
	public boolean getPlayed() { return this.played; }
	
	public static void reset(ArrayList<Team> teams) { 
		for(int i = 0; i < teams.size(); i++) teams.get(i).played = false; 
	}
	
	//finds teams given the name
	public static Team getTeam(ArrayList<Team> team, String s) {
		int i = 0; 
		while(i < team.size() && !team.get(i).name.equals(s))
			i++;
		if(i < team.size())
			return team.get(i);
		return team.get(0);
	}	
}