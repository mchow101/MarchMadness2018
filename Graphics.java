import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Graphics extends JPanel implements Runnable {
	private Thread t;
	private JFrame frame = new JFrame("March Madness Bracket");
	private boolean run = true;
	private int dim = 1400;
	private ArrayList<Team> teams;
	
	public Graphics() {
		//Create JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dim, (int)(dim*.8));
		frame.setVisible(true);
		frame.add(this);
		frame.repaint();
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while(run)
			frame.repaint();
	}
	
	public void setTeams(ArrayList<Team> t) {
		for(int i = 0; i < t.size(); i++)
			this.teams.add(t.get(i));
	}
	
	public String getTeam(int x) {
		return "   blah " + x;
	}
	
	public void paintComponent(java.awt.Graphics g) {
		//Background and Heading
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim, (int)(dim*.8));
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		String txt = "Division I Men's Bracket";
		g.drawString(txt, dim/2 - 250, dim/10);
		//Bracket
		int y = (int)(dim*.8)/42;
		int x = dim/12;
		for(int i = 0; i < 16; i++) {
			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			int margin = 25;
			g.drawLine(0 + margin, y*(i + 6), x + margin, y*(i + 6));
			g.drawString(getTeam(i), 0 + margin, y*(i + 6));
			g.drawLine(dim - 2*margin, y*(i + 6), dim - x - 2*margin, y*(i + 6));
			g.drawString(getTeam(i + 16), dim - x - 2*margin, y*(i + 6));
			g.drawLine(0 + margin, (int)(dim*.8) - (int)(y)*(i + 6), x + margin, (int)(dim*.8) - (int)(y)*(i + 6));
			g.drawString(getTeam(i + 32), 0 + margin, (int)(dim*.8) - (int)(y)*(i + 6));
			g.drawLine(dim - 2*margin, (int)(dim*.8) - (int)(y)*(i + 6), dim - x - 2*margin, (int)(dim*.8) - (int)(y)*(i + 6));
			g.drawString(getTeam(i + 48), dim - x - 2*margin, (int)(dim*.8) - (int)(y)*(i + 6));
			if(i%2 == 1) {
				g.drawLine(x + margin, y*(i + 5), x + margin, y*(i + 6));
				g.drawLine(x + margin, (int)(y*(i + 5.5)), 2*x + margin, (int)(y*(i + 5.5)));
				g.drawLine(dim - x - 2*margin, y*(i + 5), dim - x - 2*margin, y*(i + 6));
				g.drawLine(dim - x - 2*margin, (int)(y*(i + 5.5)), dim - 2*x - 2*margin, (int)(y*(i + 5.5)));
				g.drawLine(x + margin, (int)(dim*.8) - (int)(y)*(i + 5), x + margin, (int)(dim*.8) - (int)(y)*(i + 6));
				g.drawLine(x + margin, (int)(dim*.8) - (int)(y*(i + 5.5)), 2*x + margin, (int)(dim*.8) - (int)(y*(i + 5.5)));
				g.drawLine(dim - x - 2*margin, (int)(dim*.8) - (int)(y)*(i + 5), dim - x - 2*margin, (int)(dim*.8) - (int)(y)*(i + 6));
				g.drawLine(dim - x - 2*margin, (int)(dim*.8) - (int)(y*(i + 5.5)), dim - 2*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 5.5)));
			}
			if(i%4 == 1) {
				g.drawLine(2*x + margin, (int)(y*(i + 5.5)), 2*x + margin, (int)(y*(i + 7.5)));
				g.drawLine(2*x + margin, (int)(y*(i + 6.5)), 3*x + margin, (int)(y*(i + 6.5)));
				g.drawLine(dim - 2*x - 2*margin, (int)(y*(i + 5.5)), dim - 2*x - 2*margin, (int)(y*(i + 7.5)));
				g.drawLine(dim - 2*x - 2*margin, (int)(y*(i + 6.5)), dim - 3*x - 2*margin, (int)(y*(i + 6.5)));
				g.drawLine(2*x + margin, (int)(dim*.8) - (int)(y*(i + 5.5)), 2*x + margin, (int)(dim*.8) - (int)(y*(i + 7.5)));
				g.drawLine(2*x + margin, (int)(dim*.8) - (int)(y*(i + 6.5)), 3*x + margin, (int)(dim*.8) - (int)(y*(i + 6.5)));
				g.drawLine(dim - 2*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 5.5)), dim - 2*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 7.5)));
				g.drawLine(dim - 2*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 6.5)), dim - 3*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 6.5)));
			}
			if(i%8 == 1) {
				g.drawLine(3*x + margin, (int)(y*(i + 6.5)), 3*x + margin, (int)(y*(i + 10.5)));
				g.drawLine(3*x + margin,  (int)(y*(i + 8.5)), 4*x + margin, (int)(y*(i + 8.5)));
				g.drawLine(dim - 3*x - 2*margin, (int)(y*(i + 6.5)), dim - 3*x - 2*margin, (int)(y*(i + 10.5)));
				g.drawLine(dim - 3*x - 2*margin, (int)(y*(i + 8.5)), dim - 4*x - 2*margin, (int)(y*(i + 8.5)));
				g.drawLine(3*x + margin, (int)(dim*.8) - (int)(y*(i + 6.5)), 3*x + margin, (int)(dim*.8) - (int)(y*(i + 10.5)));
				g.drawLine(3*x + margin, (int)(dim*.8) - (int)(y*(i + 8.5)), 4*x + margin, (int)(dim*.8) - (int)(y*(i + 8.5)));
				g.drawLine(dim - 3*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 6.5)), dim - 3*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 10.5)));
				g.drawLine(dim - 3*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 8.5)), dim - 4*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 8.5)));
			}
			if(i%16 == 1) {
				g.drawLine(4*x + margin, (int)(y*(i + 8.5)), 4*x + margin, (int)(y*(i + 16.5)));
				g.drawLine(4*x + margin,  (int)(y*(i + 12.5)), 5*x + margin, (int)(y*(i + 12.5)));
				g.drawLine(dim - 4*x - 2*margin, (int)(y*(i + 8.5)), dim - 4*x - 2*margin, (int)(y*(i + 16.5)));
				g.drawLine(dim - 4*x - 2*margin, (int)(y*(i + 12.5)), dim - 5*x - 2*margin, (int)(y*(i + 12.5)));
				g.drawLine(4*x + margin, (int)(dim*.8) - (int)(y*(i + 8.5)), 4*x + margin, (int)(dim*.8) - (int)(y*(i + 16.5)));
				g.drawLine(4*x + margin, (int)(dim*.8) - (int)(y*(i + 12.5)), 5*x + margin, (int)(dim*.8) - (int)(y*(i + 12.5)));
				g.drawLine(dim - 4*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 8.5)), dim - 4*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 16.5)));
				g.drawLine(dim - 4*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 12.5)), dim - 5*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 12.5)));
				g.drawLine(5*x + margin, (int)(y*(i + 12.5)), 5*x + margin, (int)(y*(i + 28.5)));
				g.drawLine(dim - 5*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 12.5)), dim - 5*x - 2*margin, (int)(dim*.8) - (int)(y*(i + 28.5)));
				g.drawLine(5*x + margin, (int)(y*(i + 15.5)), (int)(6.3*x) + margin, (int)(y*(i + 15.5)));
				g.drawLine(dim - 5*x - 2*margin, (int)(y*(i + 25.5)), dim - (int)(6.3*x) - 2*margin, (int)(y*(i + 25.5)));
				g.drawLine((int)(6.3*x) + margin, (int)(y*(i + 20.5)), dim - (int)(6.3*x) - 2*margin, (int)(y*(i + 20.5)));
			}
		}
	}
}
