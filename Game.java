public class Game {
	Team a, b;
	Team winner, loser;
	
	public Game(Team a, Team b) {
		this.a = a;
		this.b = b;
		game();
	}
	
	public void game() {
		double p = Math.random();
		if(p < 0.5) {
			winner = a;
			a.setWinner(true);
			loser = b;
			b.setWinner(false);
		} else {
			winner = b;
			b.setWinner(true);
			loser = a;
			a.setWinner(false);
		}
	}
	
	public Team getWinner() { return winner; }
	public Team getLoser() { return loser; }
}