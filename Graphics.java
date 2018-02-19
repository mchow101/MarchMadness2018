import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

@SuppressWarnings("serial")
public class Graphics extends JPanel implements Runnable {
	private Thread t;
	private JFrame frame = new JFrame("March Madness Bracket");
	private boolean run = true;
	private int dim = 1400;
	private int q1x = 25, q1y = (int)(dim*.8)/7, q2x = (int)(dim*1.2) - 25, q2y = (int)(dim*.8)/7;
	private int q3x = 25, q3y = (int)(dim*.4) + (int)(dim*.8)/42, q4x = (int)(dim*1.2) - 25, q4y = (int)(dim*.4) + (int)(dim*.8)/42;
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<Integer> toRemove = new ArrayList<Integer>();
	private int team = 0;
	
	public Graphics() {
		for(int i = 0; i < 64; i++) 
			teams.add(new Team(i));
		//Create JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int)(dim*1.2) + 15, (int)(dim*.8) + 35);
		frame.setVisible(true);
		frame.add(this);
		frame.repaint();
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		
	}
	
	public void setTeams(ArrayList<Team> t) {
		for(int i = 0; i < t.size(); i++)
			this.teams.add(t.get(i));
	}
	
	public String getTeam(int x) {
		return " " + teams.get(x);
	}
	
	public String getOpponent(int x) {
		return "  " + teams.get(x).getOpponent();
	}
	
	public void paintComponent(java.awt.Graphics g) {
		//Background and Heading
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int)(dim*1.2), (int)(dim*.8));
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		String txt = "March Madness Bracket";
		g.drawString(txt, (int)(dim*.6) - 250, dim/10);
		//Bracket
		int y = (int)(dim*.8)/42;
		int x = (int)(dim*.1);
		g.setFont(new Font("Times New Roman", Font.PLAIN, dim/50));
		//Round 1
		updateList();
		for(int i = 0; i < 64; i+=2) 
			team = i;
		for(int i = 0; i < 16; i+=2) {
			team = i;
			updateList(team);
			updateList(team + 16);
			updateList(team + 32);
			updateList(team + 48);
			bracket(g, 0, x, y*i, y*(i + 1));
		}
		updateList();
		//Round 2
		for(int i = 0; i < 16; i+=4) {
			team = i/2;
			updateList(team);
			updateList(team + 8);
			updateList(team + 16);
			updateList(team + 24);
			bracket(g, x, 2*x, (int)(y*(i + 0.5)), (int)(y*(i + 2.5)));
		}
		updateList();
		//Round 3
		for(int i = 0; i < 16; i+=8) {
			team = i/4;
			updateList(team);
			updateList(team + 4);
			updateList(team + 8);
			updateList(team + 12);
			bracket(g, 2*x, 3*x, (int)(y*(i + 1.5)), (int)(y*(i + 5.5)));
		}
		updateList();
		//Quarterfinals 
		team = 0;
		System.out.println(teams);
		updateList(team);
		updateList(team + 2);
		updateList(team + 4);
		updateList(team + 6);
		bracket(g, 3*x, 4*x, (int)(y*3.5), (int)(y*11.5));
		updateList();

		//Semifinals and Finals
		finalBracket(g, 4*x, 5*x, (int)(6.3*x), (int)(y*(7.5)), (int)(y*(23.5)));
	}
	
	public void bracket(java.awt.Graphics g, int x1, int x2, int y1, int y2) {
		g.setColor(Color.WHITE);
		int p = teams.size()/4;
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
		print(g, teams.get(team), q1x + x1, q1y + y1);
		print(g, teams.get(team + p), q3x + x1, q3y + y1);
		print(g, teams.get(team + 2*p), q2x - x2, q2y + y1);
		print(g, teams.get(team + 3*p), q4x - x2, q4y + y1);
		print(g, teams.get(team).getOpponent(), q1x + x1, q1y + y2);
		print(g, teams.get(team + p).getOpponent(), q3x + x1, q3y + y2);
		print(g, teams.get(team + 2*p).getOpponent(), q2x - x2, q2y + y2);
		print(g, teams.get(team + 3*p).getOpponent(), q4x - x2, q4y + y2);
	}
	
	public void finalBracket(java.awt.Graphics g, int x1, int x2, int x3, int y1, int y2) {
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
		updateList(team);
		print(g, teams.get(team), q1x + x1, q1y + y1);
		print(g, teams.get(team).getOpponent(), q1x + x1, q1y + y2);
		team += 2;
		updateList(team);
		print(g, teams.get(team), q2x - x2, q2y + y1);
		print(g, teams.get(team).getOpponent(), q2x - x2, q2y + y2);
		updateList();
		team = 0;
		updateList(team);
		print(g, teams.get(team), q1x + x2, q1y + (int)(y1*1.3));
		print(g, teams.get(team).getOpponent(), q2x - x3, q2y + y2 - (int)(y1*.3));
		updateList();
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("Times New Roman", Font.BOLD, dim/40));
		g.drawString(" " + teams.get(team), q2x - x3, (int)(dim*.4));
	}
	
	public void updateList() {
		Collections.sort(toRemove);
		for(int i = 0; i < toRemove.size(); i++) {
			int p = toRemove.get(i) - i;
			teams.remove(p);
		}
		toRemove.clear();
		for(int i = 0; i < teams.size(); i++) 
			teams.get(i).setNum(i);
		for(int i = 0; i <teams.size() - 1; i+=2) {
			teams.get(i).setOpponent(teams.get(i + 1));
			teams.get(i + 1).setOpponent(teams.get(i));
		}
		team = 0;
	}
	
	public void updateList(int t) {
		Game game = new Game(teams.get(t), teams.get(t).getOpponent());
		if(!game.getWinner().equals(teams.get(t)))
			toRemove.add(t);
		else
			toRemove.add(teams.get(t).getOpponent().getNum());
	}
	
	public void print(java.awt.Graphics g, Team team, int x, int y) {
		if(team.isWinner()) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Times New Roman", Font.BOLD, dim/50));
		} else {
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman", Font.PLAIN, dim/50));
		}
		g.drawString("  " + team, x, y);
	}
}