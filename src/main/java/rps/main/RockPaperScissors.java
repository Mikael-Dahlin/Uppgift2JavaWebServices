package rps.main;

import java.util.Random;

/*
 * Main logic class for handling the rock, paper, scissors game.
 */
public class RockPaperScissors {
	
	static Random random = new Random();
	static String[] printChoice = {"rock", "paper", "scissors"};
	static String result;
	
	/*
	 * Method for handling one round of rock, paper, scissors.
	 */
	public static String round() {
		
		// Check if inputs are valid, provides a random input if not.
		if (PlayerBean.PLAYERONE < 1 || PlayerBean.PLAYERONE > 3) {
			handleInput(String.valueOf(PlayerBean.PLAYERONE),  "playerOne");
		}
		if (PlayerBean.PLAYERTWO < 1 || PlayerBean.PLAYERTWO > 3) {
			handleInput(String.valueOf(PlayerBean.PLAYERTWO),  "playerTwo");
		}
		
		// See if i is a tie or which player wins.
		if (PlayerBean.PLAYERONE == PlayerBean.PLAYERTWO) {
			ScoreBean.addTie();
			result = "tie";
		} else {
			checkResult();
		}
		
		// return JSON response.
		String pattern	= "{\"Player1\":\"%s\",\"Player2\":\"%s\",\"Result\":\"%s\"}";
		return String.format(pattern, printChoice[PlayerBean.PLAYERONE-1], printChoice[PlayerBean.PLAYERTWO-1], result);
		
	}
	
	/*
	 * Method for handling the input from the user.
	 */
	public static void handleInput(String input, String player) {
		int choice = 0;
		switch(input.toLowerCase())
		{
			case "1":
			case "rock":
				choice = 1;
				break;
			case "2":
			case "paper":
				choice = 2;
				break;
			case "3":
			case "scissors":
				choice = 3;
				break;
		}
		
		// random number from 1 to 3, if the input is none of the above.
		if (choice == 0) {
			choice = random.nextInt(3) + 1;
		}
		
		// check which player sent the input.
		if(player.equals("playerOne")) {
			PlayerBean.PLAYERONE = choice;
		} else {
			PlayerBean.PLAYERTWO = choice;
		}
	}
	
	/*
	 * Method to see which player won.
	 */
	public static void checkResult() {
		int sum = PlayerBean.PLAYERONE - PlayerBean.PLAYERTWO;

		if (sum == 1 || sum == -2) {
			ScoreBean.addWin();
			result = "player one wins";
		} else {
			ScoreBean.addLoss();
			result = "player two wins";
		}
	}

	/*
	 * Method that returns a JSON with the player's input.
	 */
	public static String playerJSON(String input, int player) {
		String choice = "";
		
		if (player == 1) {
			handleInput(input, "playerOne");
			choice = printChoice[PlayerBean.PLAYERONE-1];
		} else {
			handleInput(input, "playerTwo");
			choice = printChoice[PlayerBean.PLAYERTWO-1];
		}
		
		String pattern	= "{\"Player" + player + "\":\"%s\"}";
		return String.format(pattern, choice);
	}

	/*
	 * Method for running many rounds against the computer, with same player input.
	 */
	public static String manyRounds(int rounds) {
		String StringToJSON = "{\"Rounds\":[";
		for(int i = 1; i <= rounds; i++) {
			PlayerBean.PLAYERTWO = 0;
			StringToJSON += round();
			if ( i < rounds) {
				StringToJSON += ",";
			}
		}
		StringToJSON += "]}";
		return StringToJSON;
	}
}
