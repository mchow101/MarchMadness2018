import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Graphics extends JPanel implements Runnable {
	private Thread t;
	private JFrame frame = new JFrame("March Madness Bracket");
	private boolean run = true;
	private int dim = 800;
	private int q1x = 25, q1y = (int)(dim*.8)/7, q2x = (int)(dim*1.2) - 25, q2y = (int)(dim*.8)/7;
	private int q3x = 25, q3y = (int)(dim*.4) + (int)(dim*.8)/42, q4x = (int)(dim*1.2) - 25, q4y = (int)(dim*.4) + (int)(dim*.8)/42;
	private ArrayList<Team> teams = new ArrayList<Team>();
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
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		String txt = "March Madness Bracket";
		g.drawString(txt, (int)(dim*.6) - 250, dim/10);
		//Bracket
		int y = (int)(dim*.8)/42;
		int x = (int)(dim*.1);
		g.setFont(new Font("Times New Roman", Font.PLAIN, dim/50));
		//Round 1
		for(int i = 0; i < 64; i+=2) {
			team = i;
			teams.get(i).setOpponent(teams.get(i + 1));
			teams.get(i + 1).setOpponent(teams.get(i));
		}
		for(int i = 0; i < 16; i+=2) {
			team = i;
			bracket(g, 0, x, y*i, y*(i + 1));
		}
		//Round 2
		for(int i = 0; i < 16; i+=4) {
			bracket(g, x, 2*x, (int)(y*(i + 0.5)), (int)(y*(i + 2.5)));
		}
		//Round 3
		for(int i = 0; i < 16; i+=8) {
			team = i;
			bracket(g, 2*x, 3*x, (int)(y*(i + 1.5)), (int)(y*(i + 5.5)));
		}
		//Quarterfinals
		bracket(g, 3*x, 4*x, (int)(y*(3.5)), (int)(y*(11.5))); 
		//Semifinals and Finals
		finalBracket(g, 4*x, 5*x, (int)(6.3*x), (int)(y*(7.5)), (int)(y*(23.5)));
	}
	
	public void bracket(java.awt.Graphics g, int x1, int x2, int y1, int y2) {
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
		g.drawLine(q4x - x2, q4y + y1, q4x - x2, q4y + y2);repaint();
		g.drawString(getTeam(team), q1x + x1, q1y + y1);
		g.drawString(getTeam(team + 16), q3x + x1, q3y + y1);
		g.drawString(getTeam(team + 32), q2x - x2, q2y + y1);
		g.drawString(getTeam(team + 48), q4x - x2, q4y + y1);
		g.setColor(Color.GREEN);
		g.drawString(getOpponent(team), q1x + x1, q1y + y2);
		g.drawString(getOpponent(team + 16), q3x + x1, q3y + y2);
		g.drawString(getOpponent(team + 32), q2x - x2, q2y + y2);
		g.drawString(getOpponent(team + 48), q4x - x2, q4y + y2);
	}
	
	public void finalBracket(java.awt.Graphics g, int x1, int x2, int x3, int y1, int y2) {
		g.setColor(Color.WHITE);
		g.drawLine(q1x + x1, q1y + y1, q1x + x2, q1y + y1);
		g.drawLine(q1x + x1, q1y + y2, q1x + x2, q1y + y2);
		g.drawLine(q1x + x2, q1y + y1, q1x + x2, q1y + y2);
		g.drawString(getTeam(team), q1x + x1, q1y + y1);
		g.drawString(getTeam(team + 1), q1x + x1, q1y + y2);
		g.drawLine(q2x - x1, q2y + y1, q2x - x2, q2y + y1);
		g.drawLine(q2x - x1, q2y + y2, q2x - x2, q2y + y2);
		g.drawLine(q2x - x2, q2y + y1, q2x - x2, q2y + y2);
		g.drawString(getTeam(team), q1x + x1, q1y + y1);
		g.drawString(getTeam(team + 1), q1x + x1, q1y + y2);
		g.drawLine(q1x + x2, q1y + (int)(y1*1.3), q1x + x3, q1y + (int)(y1*1.3));
		g.drawLine(q2x - x2, q2y + y2 - (int)(y1*.3), q2x - x3, q2y + y2 - (int)(y1*.3));
		g.drawLine(q2x - x3, (int)(dim*.4), q1x + x3, (int)(dim*.4));
		g.drawString(getTeam(team), q1x + x2, q1y + (int)(y1*1.3));
		g.drawString(getTeam(team + 1), q2x - x3, q2y + y2 - (int)(y1*.3));
	}
}
