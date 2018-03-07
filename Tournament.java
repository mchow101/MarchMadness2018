import java.util.ArrayList;
import java.util.Collections;

public class Tournament {
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<ArrayList<Team>> results = new ArrayList<ArrayList<Team>>();
	public Tournament(ArrayList<Team> teams) {
		int count = 0;
		//sets teams in order they are playing
		for(int i = 0; i < teams.size(); i++) {
			this.teams.add(teams.get(i));
			teams.get(i).setOpponent(teams.get(16*(1 + i/16) - i%16 - 1));
			if(!teams.get(i).getPlayed()) {
				teams.get(i).setIndex(count);
				teams.get(i).getOpponent().setIndex(count + 1);
				teams.get(i).played();
				teams.get(i).getOpponent().played();
				count += 2;
			}
		}
		Team.reset(teams);
	}
	
	public ArrayList<Team> getStartingTeams() {
		return teams;
	}
	
	public ArrayList<Team> round1() {
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
		return list;
	}
	
	public ArrayList<Team> getRound1() {
		if(results.size() < 1) results.add(round1());
		return results.get(0);
	}
	
	public ArrayList<Team> round2() {
		System.out.println("Round 2*****");
		ArrayList<Team> temp = results.get(0);
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(temp);
		
		for(int i = 0; i < temp.size(); i+=2) {
			temp.get(i).setOpponent(temp.get(i + 1));
			temp.get(i + 1).setOpponent(temp.get(i));
		}
		
		for(int i = 0; i < temp.size(); i++) { 
			if(!temp.get(i).getPlayed()) {
				System.out.println(temp.get(i) + " " + temp.get(i).getOpponent());
				list.add(game(temp.get(i), temp.get(i).getOpponent()));
				temp.get(i).played();
				temp.get(i).getOpponent().played();
			}
		}
		return list;
	}
		
	public ArrayList<Team> getRound2() {
		if(results.size() < 2) results.add(round2());
		return results.get(1);
	}

	public ArrayList<Team> round3() {
		System.out.println("Round 3*****");
		ArrayList<Team> temp = results.get(1);
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(temp);
		
		for(int i = 0; i < temp.size(); i+=2) {
			temp.get(i).setOpponent(temp.get(i + 1));
			temp.get(i + 1).setOpponent(temp.get(i));
		}
		
		for(int i = 0; i < temp.size(); i++) { 
			if(!temp.get(i).getPlayed()) {
				System.out.println(temp.get(i) + " " + temp.get(i).getOpponent());
				list.add(game(temp.get(i), temp.get(i).getOpponent()));
				temp.get(i).played();
				temp.get(i).getOpponent().played();
			}
		}
		return list;
	}
	
	public ArrayList<Team> getRound3() {
		if(results.size() < 3) results.add(round3());
		return results.get(2);
	}
	
	public ArrayList<Team> roundQuarters() {
		System.out.println("Round Q*****");
		ArrayList<Team> temp = results.get(2);
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(temp);
		
		for(int i = 0; i < temp.size(); i+=2) {
			temp.get(i).setOpponent(temp.get(i + 1));
			temp.get(i + 1).setOpponent(temp.get(i));
		}
		
		for(int i = 0; i < temp.size(); i++) { 
			if(!temp.get(i).getPlayed()) {
				System.out.println(temp.get(i) + " " + temp.get(i).getOpponent());
				list.add(game(temp.get(i), temp.get(i).getOpponent()));
				temp.get(i).played();
				temp.get(i).getOpponent().played();
			}
		}
		return list;
	}
	
	public ArrayList<Team> getRoundQuarters() {
		if(results.size() < 4) results.add(roundQuarters());
		return results.get(3);
	}

	public ArrayList<Team> roundSemis() {
		System.out.println("Round S*****");
		ArrayList<Team> temp = results.get(3);
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(temp);
		
		for(int i = 0; i < temp.size(); i+=2) {
			temp.get(i).setOpponent(temp.get(i + 1));
			temp.get(i + 1).setOpponent(temp.get(i));
		}
		
		for(int i = 0; i < temp.size(); i++) { 
			if(!temp.get(i).getPlayed()) {
				System.out.println(temp.get(i) + " " + temp.get(i).getOpponent());
				list.add(game(temp.get(i), temp.get(i).getOpponent()));
				temp.get(i).played();
				temp.get(i).getOpponent().played();
			}
		}
		return list;
	}
	
	public ArrayList<Team> getRoundSemis() {
		if(results.size() < 5) results.add(roundSemis());
		return results.get(4);
	}
	

	public ArrayList<Team> roundFinals() {
		System.out.println("Round F*****");
		ArrayList<Team> temp = results.get(4);
		ArrayList<Team> list = new ArrayList<Team>();
		Team.reset(temp);
		
		for(int i = 0; i < temp.size(); i+=2) {
			temp.get(i).setOpponent(temp.get(i + 1));
			temp.get(i + 1).setOpponent(temp.get(i));
		}
		
		for(int i = 0; i < temp.size(); i++) { 
			if(!temp.get(i).getPlayed()) {
				System.out.println(temp.get(i) + " " + temp.get(i).getOpponent());
				list.add(game(temp.get(i), temp.get(i).getOpponent()));
				temp.get(i).played();
				temp.get(i).getOpponent().played();
			}
		}
		return list;
	}
	
	public ArrayList<Team> getRoundFinals() {
		if(results.size() < 6) results.add(roundFinals());
		return results.get(5);
	}
	
	public Team getWinner() {
		System.out.println("Winner*****");
		return results.get(5).get(0);
	}
	
	public Team getTeam(int round, int index) {
		ArrayList<Team> temp = results.get(round - 1);
		for(int i = 0; i < temp.size(); i++) 
			if(temp.get(i).getIndex() == index) return temp.get(i);
		return null;
	}
	
	public Team game(Team a, Team b) {
		if(a.getNum() > b.getNum()) {
			a.setWinner(true);
			b.getOpponent().setWinner(false);
			return a;
		} else {
			a.setWinner(false);
			b.setWinner(true);
			 return b;
		}
	}
	
	public ArrayList<ArrayList<Team>> results() {
		getRound1();
		getRound2();
		getRound3();
		getRoundQuarters();
		getRoundSemis();
		getRoundFinals();
		System.out.println(getWinner());
		return results;
	}
}
