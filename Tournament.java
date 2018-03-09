import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tournament {
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<ArrayList<Team>> results = new ArrayList<ArrayList<Team>>();
	private Scanner scan;
	
	public Tournament(ArrayList<Team> teams) throws FileNotFoundException {
		scan = new Scanner(new File("TeamSetup.txt"));
		//sets teams in order they are playing
//		this.teams.addAll(teams);
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		while(scan.hasNextInt()) pos.add(scan.nextInt());
		for(int i = 0; i < teams.size(); i++) {
			int count = pos.get(i) + 16*(i/16);
			this.teams.add(Team.getTeam(teams, count));
			System.out.println(i + " " + count + " " + this.teams);
		}
		
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

		getStartingTeams();
		round1();
		round2();
		round3();
		roundQuarters();
		roundSemis();
		roundFinals();
		System.out.println(getWinner());
	}
	
	public void getStartingTeams() {
		results.add(0, (ArrayList<Team>) teams.clone());
	}
	
	public void round1() {
		System.out.println("Round 1*****");
		Team.reset(teams);
		ArrayList<Team> list = new ArrayList<Team>();
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
		results.add(1, (ArrayList<Team>) teams.clone());
	}
	
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
		results.add(5, (ArrayList<Team>) teams.clone());
	}
	
	public void roundFinals() {
		System.out.println("Round F*****");
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
		results.add(6, (ArrayList<Team>) teams.clone());
	}
	
	public Team getWinner() {
		System.out.println("Winner*****");
		return teams.get(0);
	}
	
	public Team getTeam(int round, int index) {
		ArrayList<Team> teams = results.get(round - 1);
		for(int i = 0; i < teams.size(); i++) 
			if(teams.get(i).getIndex() == index) return teams.get(i);
		return null;
	}
	
	public Team game(Team a, Team b) {
		
		double p = Math.random();
		if(p < 0.5) {
			a.setWinner(true);
			b.setWinner(false);
			return a;
		} else {
			a.setWinner(false);
			b.setWinner(true);
			 return b;
		}
		
		/*
		if(a.getNum() > b.getNum()) {
			a.setWinner(true);
			b.setWinner(false);
			return a;
		} else {
			a.setWinner(false);
			b.setWinner(true);
			 return b;
		}
		*/
	}
	
	public ArrayList<ArrayList<Team>> results() {
		return results;
	}
}
