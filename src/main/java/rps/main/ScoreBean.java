package rps.main;

/*
 * Class for keeping track of the score.
 */
public class ScoreBean {
	
	private static int WINS, LOSSES, TIES;

	public static void addLoss() {
		LOSSES++;
	}
	
	public static void addTie() {
		TIES++;
	}
	
	public static void addWin() {
		WINS++;
	}
	
	/*
	 * Method to return the score as JSON.
	 */
	public static String JSON() {
		String pattern	= "{\"Player1 wins\":\"%s\",\"Player2 wins\":\"%s\",\"Ties\":\"%s\"}";
		return String.format(pattern, WINS, LOSSES, TIES);
	}

	public static void clear() {
		WINS = 0;
		LOSSES = 0;
		TIES = 0;
	}

	public static void setScore(int newWins, int newLosses, int newTies) {
		WINS = newWins;
		LOSSES = newLosses;
		TIES = newTies;
		
	}

}
