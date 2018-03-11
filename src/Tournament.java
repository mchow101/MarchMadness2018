import java.awt.*;
import java.io.*;
import java.util.ArrayList;
<<<<<<< HEAD:src/Tournament.java
import java.util.Scanner;

public class Tournament {
	//list of teams still in tournament
	private ArrayList<Team> teams = new ArrayList<Team>(); 
	//list of results - allows referencing to each round
	private ArrayList<ArrayList<Team>> results = new ArrayList<ArrayList<Team>>();
	private Scanner scan;
	
	public Tournament(ArrayList<Team> teams) throws FileNotFoundException {
		scan = new Scanner(new File("2016TeamSetup.txt"));
		
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
=======
import java.util.Collections;

import javax.swing.*;

@SuppressWarnings("serial")
public class Graphics extends JPanel implements Runnable {
	//jframe variables
	private Thread t;
	private JFrame frame = new JFrame("March Madness Bracket");
	//size and placement values
	private int dim = 800;
	private int q1x = 25, q1y = (int)(dim*.8)/7, q2x = (int)(dim*1.2) - 25, q2y = (int)(dim*.8)/7;
	private int q3x = 25, q3y = (int)(dim*.4) + (int)(dim*.8)/42, q4x = (int)(dim*1.2) - 25, q4y = (int)(dim*.4) + (int)(dim*.8)/42;
	private Tournament tournament;
	
	public Graphics() throws FileNotFoundException {
		//initializes and reads in data for first 300 teams in txt file
		ArrayList<Team> teams = new ArrayList<Team>();
		for(int i = 0; i < 300; i++) 
			teams.add(new Team(i));
		//initializes tournament for this selection of teams
		tournament = new Tournament(teams);
		//create JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int)(dim*1.2) + 15, (int)(dim*.8) + 35);
		frame.setVisible(true);
		frame.add(this);
		frame.repaint();
		t = new Thread(this);
		t.start();
	}
	
	//(for implementation of Runnable)
	@Override
	public void run() {}
	
	public void paintComponent(java.awt.Graphics g) {
		//background, heading, key
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int)(dim*1.2), (int)(dim*.8));
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.drawString("March Madness Bracket", (int)(dim*.6) - 250, dim/10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, dim/50));
		g.drawString("Key: ", dim/15, dim/20);
		g.setColor(Color.GREEN);
		g.drawString("Winner", dim/15*2, dim/20);
		g.setColor(Color.RED);
		g.drawString("Loser", dim/15*3, dim/20);
		//placement values for bracket
		int y = (int)(dim*.8)/42;
		int x = (int)(dim*.1);
		g.setFont(new Font("Times New Roman", Font.PLAIN, dim/50));
		//round 1
		for(int i = 0; i < 16; i++) {
			//draw lines
			if(i%2 == 0) bracket(g, 0, x, y*i, y*(i + 1));
			//set color of text
			if(tournament.results().get(0).get(i).equals(tournament.results().get(1).get(i/2)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			//display team name
			g.drawString("  " + tournament.results().get(0).get(i), q1x, q1y + y*i);
			//set color
			if(tournament.results().get(0).get(i + 16).equals(tournament.results().get(1).get(i/2 + 8)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			//display
			g.drawString("  " + tournament.results().get(0).get(i + 16), q2x - x, q2y + y*i);
			//rinse and repeat
			if(tournament.results().get(0).get(i + 32).equals(tournament.results().get(1).get(i/2 + 16)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(0).get(i + 32), q3x, q3y + y*i);
			if(tournament.results().get(0).get(i + 48).equals(tournament.results().get(1).get(i/2 + 24)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(0).get(i + 48), q4x - x, q4y + y*i);
>>>>>>> f797041a81621d4ebdd102371ea384ab1481e538:Tournament.java
		}
		
		//round 2
		//same idea as round 1, but with different values and teams
		for(int i = 0; i < 16; i+=2) {
			if(i%4 == 0) bracket(g, x, 2*x, (int)(y*(i + 0.5)), (int)(y*(i + 2.5)));
			if(tournament.results().get(1).get(i/2).equals(tournament.results().get(2).get(i/4)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(1).get(i/2), q1x + x, q1y + (int)(y*(i + 0.5)));
			if(tournament.results().get(1).get(i/2 + 8).equals(tournament.results().get(2).get(i/4 + 4)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(1).get(i/2 + 8), q2x - 2*x, q2y + (int)(y*(i + 0.5)));
			if(tournament.results().get(1).get(i/2 + 16).equals(tournament.results().get(2).get(i/4 + 8)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(1).get(i/2 + 16), q3x + x, q3y + (int)(y*(i + 0.5)));
			if(tournament.results().get(1).get(i/2 + 24).equals(tournament.results().get(2).get(i/4 + 12)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(1).get(i/2 + 24), q4x - 2*x, q4y + (int)(y*(i + 0.5)));
		}
		
		//round 3
		//and so on and so forth
		for(int i = 0; i < 16; i+=4) {
			if(i%8 == 0) bracket(g, 2*x, 3*x, (int)(y*(i + 1.5)), (int)(y*(i + 5.5)));
			if(tournament.results().get(2).get(i/4).equals(tournament.results().get(3).get(i/8)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(2).get(i/4), q1x + 2*x, q1y + (int)(y*(i + 1.5)));
			if(tournament.results().get(2).get(i/4 + 4).equals(tournament.results().get(3).get(i/8 + 2)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(2).get(i/4 + 4), q2x - 3*x, q2y + (int)(y*(i + 1.5)));
			if(tournament.results().get(2).get(i/4 + 8).equals(tournament.results().get(3).get(i/8 + 4)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(2).get(i/4 + 8), q3x + 2*x, q3y + (int)(y*(i + 1.5)));
			if(tournament.results().get(2).get(i/4 + 12).equals(tournament.results().get(3).get(i/8 + 6)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(2).get(i/4 + 12), q4x - 3*x, q4y + (int)(y*(i + 1.5)));
		}
		
		//quarterfinals 
		for(int i = 0; i < 16; i+=8) {
			if(i == 0) bracket(g, 3*x, 4*x, (int)(y*(i + 3.5)), (int)(y*(i + 11.5)));
			if(tournament.results().get(3).get(i/8).equals(tournament.results().get(4).get(i/16)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(3).get(i/8), q1x + 3*x, q1y + (int)(y*(i + 3.5)));
			if(tournament.results().get(3).get(i/8 + 2).equals(tournament.results().get(4).get(i/16 +1)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(3).get(i/8 + 2), q2x - 4*x, q2y + (int)(y*(i + 3.5)));
			if(tournament.results().get(3).get(i/8 + 4).equals(tournament.results().get(4).get(i/16 + 2)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(3).get(i/8 + 4), q3x + 3*x, q3y + (int)(y*(i + 3.5)));
			if(tournament.results().get(3).get(i/8 + 6).equals(tournament.results().get(4).get(i/16 + 3)))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.drawString("  " + tournament.results().get(3).get(i/8 + 6), q4x - 4*x, q4y + (int)(y*(i + 3.5)));
		}

		//semifinals and finals
		finalBracket(g, 4*x, 5*x, (int)(6.3*x), (int)(y*(7.5)), (int)(y*(23.5)));
		if(tournament.results().get(4).get(0).equals(tournament.results().get(5).get(0)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.MAGENTA);
		g.drawString("  " + tournament.results().get(4).get(0), q1x + 4*x, q1y + (int)(y*(7.5)));
		if(tournament.results().get(4).get(1).equals(tournament.results().get(5).get(1)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.PINK);
		g.drawString("  " + tournament.results().get(4).get(1), q2x - 5*x, q2y + (int)(y*(7.5)));
		if(tournament.results().get(4).get(2).equals(tournament.results().get(5).get(0)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.ORANGE);
		g.drawString("  " + tournament.results().get(4).get(2), q3x + 4*x, q3y + (int)(y*(7.25)));
		if(tournament.results().get(4).get(3).equals(tournament.results().get(5).get(1)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.YELLOW);
		g.drawString("  " + tournament.results().get(4).get(3), q4x - 5*x, q4y + (int)(y*(7.25)));
		if(tournament.results().get(5).get(0).equals(tournament.results().get(6).get(0)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.CYAN);
		g.drawString("  " + tournament.results().get(5).get(0), q1x + 5*x, q1y + (int)(y*(7.5*1.3)));
		if(tournament.results().get(5).get(1).equals(tournament.results().get(6).get(0)))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
//		g.setColor(Color.GRAY);
		g.drawString("  " + tournament.results().get(5).get(1), q2x - (int)(6.3*x), (q2y + (int)(y*23.5) - (int)(y*7.5*.3)));
		g.setColor(Color.GREEN);
		g.drawString("  " + tournament.results().get(6).get(0), q2x - (int)(6.3*x), (int)(dim*.4));
		System.out.println(tournament.results());
	}
	
<<<<<<< HEAD:src/Tournament.java
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
=======
	public void bracket(java.awt.Graphics g, int x1, int x2, int y1, int y2) {
		//given the input of 2 x points and 2 y points, draws the bracket
		//four quadrants
		g.setColor(Color.WHITE);
		g.drawLine(q1x + x1, q1y + y1, q1x + x2, q1y + y1);
		g.drawLine(q1x + x1, q1y + y2, q1x + x2, q1y + y2);
		g.drawLine(q1x + x2, q1y + y1, q1x + x2, q1y + y2);
		g.drawLine(q2x - x1, q2y + y1, q2x - x2, q2y + y1);
		g.drawLine(q2x - x1, q2y + y2, q2x - x2, q2y + y2);
		g.drawLine(q2x - x2, q2y + y1, q2x - x2, q2y + y2);
		g.drawLine(q3x + x1, q3y + y1, q3x + x2, q3y + y1);
		g.drawLine(q3x + x1, q3y + y2, q3x + x2, q3y + y2);
		g.drawLine(q3x + x2, q3y + y1, q3x + x2, q3y + y2);
		g.drawLine(q4x - x1, q4y + y1, q4x - x2, q4y + y1);
		g.drawLine(q4x - x1, q4y + y2, q4x - x2, q4y + y2);
		g.drawLine(q4x - x2, q4y + y1, q4x - x2, q4y + y2);
>>>>>>> f797041a81621d4ebdd102371ea384ab1481e538:Tournament.java
	}
	
	public void finalBracket(java.awt.Graphics g, int x1, int x2, int x3, int y1, int y2) {
		//similar to bracket(), but for irregular last few games
		g.setColor(Color.WHITE);
		g.drawLine(q1x + x1, q1y + y1, q1x + x2, q1y + y1);
		g.drawLine(q1x + x1, q1y + y2, q1x + x2, q1y + y2);
		g.drawLine(q1x + x2, q1y + y1, q1x + x2, q1y + y2);
		g.drawLine(q2x - x1, q2y + y1, q2x - x2, q2y + y1);
		g.drawLine(q2x - x1, q2y + y2, q2x - x2, q2y + y2);
		g.drawLine(q2x - x2, q2y + y1, q2x - x2, q2y + y2);
		g.drawLine(q1x + x2, q1y + (int)(y1*1.3), q1x + x3, q1y + (int)(y1*1.3));
		g.drawLine(q2x - x2, q2y + y2 - (int)(y1*.3), q2x - x3, q2y + y2 - (int)(y1*.3));
		g.drawLine(q2x - x3, (int)(dim*.4), q1x + x3, (int)(dim*.4));
	}
}