package rps.main;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/*
 * Main controller of the application that contains most of the mapping functions.
 */
@RestController
public class RpsController {
	
	
	@RequestMapping(value = "/playerone", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String playerOne(String rps) {
		return RockPaperScissors.playerJSON(rps, 1);
	}
	
	@RequestMapping(value = "/playertwo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String playerTwo(String rps) {
		return RockPaperScissors.playerJSON(rps, 2);
	}

	@RequestMapping(value = "/round", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String playRound() {
		return RockPaperScissors.round();
	}
	
	@RequestMapping(value = "/rounds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String playRounds(int rounds) {
		return RockPaperScissors.manyRounds(rounds);
	}
	
	@RequestMapping(value = "/score", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showScore() {
		return ScoreBean.JSON();
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String clearScore() {
		ScoreBean.clear();
		return ScoreBean.JSON();
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String clearScore(String _method) {
		if (_method.equalsIgnoreCase("put")) {
			ScoreBean.clear();
		}
		return ScoreBean.JSON();
	}
	
	@RequestMapping(value = "/setscore", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String setScore(int wins, int losses, int ties) {
		ScoreBean.setScore(wins, losses, ties);
		return ScoreBean.JSON();
	}
	
	@RequestMapping(value = "/setscore", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String setScore(String _method, int wins, int losses, int ties) {
		if (_method.equalsIgnoreCase("put")) {
			ScoreBean.setScore(wins, losses, ties);
		}
		return ScoreBean.JSON();
	}
}
