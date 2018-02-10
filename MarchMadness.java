//Mitali Chowdhury
//REMEMBER TO COMMMENT
import java.util.ArrayList;

public class MarchMadness {
	static ArrayList<Team> t = new ArrayList<Team>();
	public MarchMadness() {
		for(int i = 1; i < 65; i++) 
			t.add(new Team(i));
	}

	public static void main(String[] args) {
		Graphics frame = new Graphics();
		frame.setTeams(t);
	}
}
