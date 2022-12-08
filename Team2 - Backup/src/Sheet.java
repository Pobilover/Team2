import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

// 해당 클래스에서 numOfGames를 불러와 몇게임이 있는지 알면
// getGame().get()을 통해서 해당 게임의 번호 List 불러올 수 있음!

public class Sheet {
	private int count = 0;
	private int numOfGames = 0;
	private Map<Integer, List<Integer>> game = new TreeMap<>();
	private List<Integer> numbers = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	
	public Sheet(List<Integer> numbers, String type) {		
		count++;
		game.put(count, numbers);
		types.add(type);
	}
	
	public void checkSheet(int numOfGames) {
		if (numOfGames != count) {
			JOptionPane.showMessageDialog(null, "입력된 게임과 저장된 게임의 수가 틀립니다!!", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			this.numOfGames = numOfGames;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}

	public Map<Integer, List<Integer>> getGame() {
		return game;
	}

	public void setGame(Map<Integer, List<Integer>> game) {
		this.game = game;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
