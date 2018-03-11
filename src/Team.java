<<<<<<< HEAD:src/Team.java
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
			scan = new Scanner(new File("2016data.txt"));
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
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
	//list of teams still in tournament
	private ArrayList<Team> teams = new ArrayList<Team>(); 
	//list of results - allows referencing to each round
	private ArrayList<ArrayList<Team>> results = new ArrayList<ArrayList<Team>>();
	private Scanner scan;
	
	public Tournament(ArrayList<Team> teams) throws FileNotFoundException {
		scan = new Scanner(new File("TeamSetup.txt"));
		
		//preparation to set teams in order they are playing
		ArrayList<Integer> pos = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		while(scan.hasNextInt()) {
			pos.add(scan.nextInt());
			names.add(scan.nextLine());
			System.out.println(pos);
		}
		
		//sets teams for tournament
		//only adds teams if setup txt document tells it to do so
		for(int i = 0; i < pos.size(); i++) {
			//accounting for the first four
			if(i != 0) if(this.teams.get(i - 1).getIndex() == pos.get(i) + 16*((i)/16)) {
				if(game(Team.getTeam(teams, names.get(i).substring(1)), this.teams.get(i - 1)).equals(this.teams.get(i - 1))) {
					pos.remove(i - 1);
					names.remove(i - 1);
				} else {
					pos.remove(i);
					names.remove(i);
				}
				i--;
				System.out.println(names);
				continue;
			}
			int count = pos.get(i) + 16*((i)/16);
			this.teams.add(Team.getTeam(teams, names.get(i).substring(1)));
			this.teams.get(i).setIndex(count);
			System.out.println(count + " " + this.teams.get(i));
		}
		
		//sets opponents
		for(int i = 0; i < this.teams.size(); i+=2) {
			this.teams.get(i).setOpponent(this.teams.get(i + 1));
			this.teams.get(i + 1).setOpponent(this.teams.get(i));
			if(!this.teams.get(i).getPlayed()) {
				this.teams.get(i).setIndex(i);
				this.teams.get(i).getOpponent().setIndex(i + 1);
				this.teams.get(i).played();
				this.teams.get(i).getOpponent().played();
			}
		}
		
		//tournament time!!
		getStartingTeams();
		round1();
		round2();
		round3();
		roundQuarters();
		roundSemis();
		roundFinals();
		System.out.println(getWinner());
	}
	
	//begin with all teams
	public void getStartingTeams() {
		results.add(0, (ArrayList<Team>) teams.clone());
	}
	
	//eliminate teams that have lost
	public void round1() {
		System.out.println("Round 1*****");
		Team.reset(teams);
		ArrayList<Team> list = new ArrayList<Team>();
		for(int i = 0; i < teams.size(); i++) {
			if(!teams.get(i).getPlayed()) {
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(1, (ArrayList<Team>) teams.clone());
	}
	
	//using round 1 winners, play another round
	//etc. 
	public void round2() {
		System.out.println("Round 2*****");
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(teams);
		
		for(int i = 0; i < teams.size(); i+=2) {
			teams.get(i).setOpponent(teams.get(i + 1));
			teams.get(i + 1).setOpponent(teams.get(i));
		}
		
		for(int i = 0; i < teams.size(); i++) { 
			if(!teams.get(i).getPlayed()) {
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(2, (ArrayList<Team>) teams.clone());
	}

	public void round3() {
		System.out.println("Round 3*****");
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(teams);
		
		for(int i = 0; i < teams.size(); i+=2) {
			teams.get(i).setOpponent(teams.get(i + 1));
			teams.get(i + 1).setOpponent(teams.get(i));
		}
		
		for(int i = 0; i < teams.size(); i++) { 
			if(!teams.get(i).getPlayed()) {
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(3, (ArrayList<Team>) teams.clone());
	}
	
	public void roundQuarters() {
		System.out.println("Round Q*****");
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(teams);
		
		for(int i = 0; i < teams.size(); i+=2) {
			teams.get(i).setOpponent(teams.get(i + 1));
			teams.get(i + 1).setOpponent(teams.get(i));
		}
		
		for(int i = 0; i < teams.size(); i++) { 
			if(!teams.get(i).getPlayed()) {
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(4, (ArrayList<Team>) teams.clone());
	}
	
	public void roundSemis() {
		System.out.println("Round S*****");
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(teams);
		
		teams.get(0).setOpponent(teams.get(2));
		teams.get(2).setOpponent(teams.get(0));
		teams.get(1).setOpponent(teams.get(3));
		teams.get(3).setOpponent(teams.get(1));

		for(int i = 0; i < teams.size(); i++) { 
			if(!teams.get(i).getPlayed()) {
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(5, (ArrayList<Team>) teams.clone());
	}
	
	public void roundFinals() {
		System.out.println("Round F*****");
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(teams);
		
		teams.get(0).setOpponent(teams.get(1));
		teams.get(1).setOpponent(teams.get(0));
		
		for(int i = 0; i < teams.size(); i++) { 
			if(!teams.get(i).getPlayed()) {
				System.out.println(teams.get(i) + " " + teams.get(i).getOpponent());
				list.add(game(teams.get(i), teams.get(i).getOpponent()));
				teams.get(i).played();
				teams.get(i).getOpponent().played();
			}
		}
		for(int i = 0; i < list.size(); i++) {
			int j = 0;
			while(!teams.get(j).equals(list.get(i))) j++;
			teams.remove(j);
		}
		results.add(6, (ArrayList<Team>) teams.clone());
	}
	
	public Team getWinner() {
		System.out.println("Winner*****");
		return teams.get(0);
	}
		
	//compares the two teams to determine a winner
	public Team game(Team a, Team b) {
		if(a.getNum() < b.getNum()) {
			a.setWinner(true);
			b.setWinner(false);
			return a;
		} else {
			a.setWinner(false);
			b.setWinner(true);
			 return b;
		}
	}
	
	public ArrayList<ArrayList<Team>> results() {
		return results;
	}
}
>>>>>>> f797041a81621d4ebdd102371ea384ab1481e538:Team.java
