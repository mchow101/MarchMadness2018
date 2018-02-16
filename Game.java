public class Game {
	Team a, b;
	Team winner, loser;
	
	public Game(Team a, Team b) {
		this.a = a;
		this.b = b;
	}
	
	public void game() {
		if(a.getNum() < b.getNum()) {
			winner = a;
			loser = b;
		} else {
			winner = b;
			loser = a;
		}
	}
	
	public Team getWinner() { return winner; }
	public Team getLoser() { return loser; }
}
