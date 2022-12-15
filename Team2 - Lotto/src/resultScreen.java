import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class resultScreenSet extends JDialog implements MouseListener, ActionListener{
	private Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
	private Map<Integer, Map<Integer, String>> sheetTypes = new TreeMap<>();
	private Map<Integer, List<Integer>> userNum = new TreeMap<>();
	private Map<Integer, List<Integer>> duplicateNum = new TreeMap<>();
	private Map<Integer, Map<Integer, List<Integer>>> duplicateList = new TreeMap<>();
	private List<Integer> duplicateSize = new ArrayList<>();
	private Map<Integer, List<Integer>> winNums = new TreeMap<>();
	private List<Integer> winPrice = new ArrayList<>();
	private List<Integer> gameNum = new ArrayList<>();
	private JPanel pnlBox;
	private JPanel pnlLoading;
	private JPanel pnl4Box = new JPanel();
	private JPanel pnl4Page = new JPanel();
	private JPanel[] pnl4BoxSets = new JPanel[5];
	private JLabel resultWord = new JLabel();
	private JLabel[] lblRanks = new JLabel[5];
	private JLabel[] lblTypes = new JLabel[5];;
	private JLabel[] lblSequences = new JLabel[5];;
	private JLabel[][] lblUserNumbers  = new JLabel[5][6];
	private JLabel currentPage = new JLabel("1");
	private JButton before;
	private JButton after;
	private JButton btnSkip;
	private Timer timer;
	private int[][] searchRounds;
	private int[] winMoney = new int[5];
	private int index = 0;
	private int pageCount = 0;
	private int numTime;
	private int indexOfRound;
	private int winNumBonus = 0;
	private int gameRound;
	private long resultMoney;
	private boolean winCheck = false;
	private int nowPage = 1;
	

	public Map<Integer, Map<Integer, List<Integer>>> getSheets() {
		return sheets;
	}

	public void setSheets(Map<Integer, Map<Integer, List<Integer>>> sheets) {
		this.sheets = sheets;
	}
	
	public Map<Integer, List<Integer>> getWinNums() {
		return winNums;
	}

	public void setWinNums(Map<Integer, List<Integer>> winNums) {
		this.winNums = winNums;
	}

	public List<Integer> getWinPrice() {
		return winPrice;
	}
	
	public void setWinPrice(List<Integer> winPrice) {
		this.winPrice = winPrice;
	}

	
	public resultScreenSet(Map<Integer, Map<Integer, List<Integer>>> sheets, Map<Integer, Map<Integer, String>> sheetTypes, int gameRound) {	
		this.sheets = sheets;
		this.sheetTypes = sheetTypes;
		this.gameRound = gameRound;
		
		// 장당 게임수
		for (int i = 0; i < sheets.size(); i++) {
			gameNum.add(sheets.get(i).size());
		}
		// 회차당 라운드 수 
		searchRounds = new int[sheets.size()][];
		int numOfGames = 0;	
	    for (int i = 0; i < sheets.size(); i++) {
	        searchRounds[i] = new int[sheets.get(i).size()];
	        Iterator<Entry<Integer, List<Integer>>> entries = sheets.get(i).entrySet().iterator();
	        int round = 0;
	        while(entries.hasNext()){
	            Map.Entry<Integer, List<Integer>> entry = entries.next();
	            searchRounds[i][round] = entry.getKey();
	            round++;
	            numOfGames++;
	        }
	     }
		
		// 당첨결과 일곱개 랜덤 숫자 만들기
		Set<Integer> winNumSet = new HashSet<>();
		Random random = new Random();
		while (winNumSet.size() < 7) {
			int num = random.nextInt(45) + 1;
			winNumSet.add(num);
		}
		List<Integer> winNumList = new ArrayList<>(winNumSet);
		Collections.sort(winNumList);
		
		// 보너스 번호 넣기
		int winNumBonus = 0;
		int bonusRandom = random.nextInt(7);
		winNumBonus = winNumList.get(bonusRandom);
		winNumList.remove(bonusRandom);
		
		// 출력용 당첨번호
		List<Integer> tempWinNums = new ArrayList<>();
		tempWinNums.addAll(winNumList);
		tempWinNums.add(winNumBonus);
		winNums.put(gameRound, tempWinNums); //key에 회차넣기
		
		
		getDuplicateNums(winNumList);
		getWinMoney();

		
		JPanel pnlFirst = new JPanel();
		
		// 로딩화면 구성
		pnlLoading = new JPanel(new BorderLayout());
		JPanel pnlMessage = new JPanel();
		JLabel lblLoadingImg = new JLabel(convertToIconGIF("행복회로.gif"));
		lblLoadingImg.setPreferredSize(new Dimension(700, 400));
		JLabel lblMessage = new JLabel("당첨번호 추첨을 시작합니다.");
		lblMessage.setPreferredSize(new Dimension(350, 20));
		lblMessage.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		String[] messages = {"당신의 행복회로가 돌아가는 중입니다.",
							 "당신의 행복회로가 과부화 됩니다.",
							 "인생은 한방입니다. 포기하지 마세요",
							 "단돈 1,000원에 기회를 잡을 수 있습니다."};
		btnSkip = new JButton("Skip");
		btnSkip.addActionListener(this);
		pnlMessage.add(Box.createHorizontalStrut(140));
		pnlMessage.add(lblMessage);
		pnlMessage.add(Box.createHorizontalStrut(80));
		pnlMessage.add(btnSkip);
		pnlLoading.add(lblLoadingImg, "Center");
		pnlLoading.add(pnlMessage, "South");
		timer = new Timer();
		numTime = 0;
		TimerTask tTask = new TimerTask(){
			@Override
			public void run() {
				if (numTime >= 4) {
					timer.cancel();
					pnlLoading.setVisible(false);
					pnlBox.setVisible(true);
				} else {
					lblMessage.setText(messages[numTime]);
					numTime++;
				}
			}
		};
		timer.schedule(tTask, 1000, 1000);
		
		// 전체 pnl
		pnlBox = new JPanel();
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		JPanel pnl5 = new JPanel();
		
		// 전체적인 panel
		pnlBox.setLayout(new BoxLayout(pnlBox, BoxLayout.Y_AXIS));
		pnlBox.setPreferredSize(new Dimension(700, 460));
		
		// 제일위 당첨결과 title panel
		JLabel turn = new JLabel(String.valueOf(gameRound));
		JLabel title = new JLabel("회 당첨결과");
		turn.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		title.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		pnl1.add(turn);
		pnl1.add(title);
		pnl1.setBorder(new LineBorder(Color.LIGHT_GRAY, 3 , true));
		pnl1.setBackground(new Color(35, 100, 165, 150));
		
		// 당첨결과 알려주는 공 표시해주는 panel
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.Y_AXIS));
		JPanel pnl2_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel pnl2_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		pnl2_2.add(Box.createHorizontalStrut(290));
		pnl2_2.add(lblWinNumT);
		pnl2_2.add(Box.createHorizontalStrut(140));
		pnl2_2.add(lblBonusNumT);
		JLabel[] lblWinNum = new JLabel[6];
		for (int i = 0; i < lblWinNum.length; i++) {
			int ballNum = winNumList.get(i);
			String ballName = "balls/ball" + ballNum +".png";
			ImageIcon icon = convertToIcon(ballName, 40, 40);
			lblWinNum[i] = new JLabel(icon);
		}
		JLabel plus = new JLabel("+");
		plus.setFont(new Font("바탕", Font.BOLD, 20));
		JLabel bonus = new JLabel();
		bonus.setIcon(convertToIcon("balls/ball" + winNumBonus +".png", 40, 40));
		
		for (int i = 0; i < lblWinNum.length; i++) {
			pnl2_1.add(lblWinNum[i]);			
		}
		pnl2_1.add(plus);
		pnl2_1.add(bonus);
		pnl2_1.setPreferredSize(new Dimension(0, 50));
		pnl2_2.setPreferredSize(new Dimension(0, 15));
		pnl2.add(pnl2_1);
		pnl2.add(pnl2_2);
		
		// 회색 박스(당첨결과와 돈) 알려주는 panel
		JPanel pnl3_1 = new JPanel();
		pnl3_1.setPreferredSize(new Dimension(450, 45));
		pnl3_1.setBackground(Color.WHITE);
		resultWord.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		pnl3_1.add(resultWord, "Center");
		pnl3.add(pnl3_1, "Center");
		
		// pnl4에 들어갈 구성 버튼과 표
		before = new JButton("<<<");
		after = new JButton(">>>");
		before.setPreferredSize(new Dimension(70,40));
		before.setBackground(Color.LIGHT_GRAY);
		before.addActionListener(this);
		after.setPreferredSize(new Dimension(70,40));
		after.setBackground(Color.LIGHT_GRAY);
		after.addActionListener(this);

		pnl4Box.setLayout(new BoxLayout(pnl4Box, BoxLayout.Y_AXIS));
		pnl4Box.setBackground(Color.WHITE);
		showResult(0);
		showResultWord();
		JLabel pageName = new JLabel("page ");
		JLabel pages = new JLabel("/ " + String.valueOf(sheets.size()));
		pnl4Page.add(pageName);
		pnl4Page.add(currentPage);
		pnl4Page.add(pages);
		pnl4Box.add(pnl4Page);
		
		// pnl4구성 - 버튼과 표
		JPanel beforeBox = new JPanel();
		JPanel afterBox = new JPanel();
		beforeBox.add(Box.createVerticalStrut(200));
		beforeBox.add(before);
		afterBox.add(Box.createVerticalStrut(200));
		afterBox.add(after);
		pnl4.setPreferredSize(new Dimension(0, 235));
		pnl4.add(beforeBox);
		pnl4.add(Box.createHorizontalStrut(30));
		pnl4.add(pnl4Box);
		pnl4.add(Box.createHorizontalStrut(30));
		pnl4.add(afterBox);
		
		// 구매 갯수와 게임 갯수 알려주는 panel
		JLabel lbl1 = new JLabel("구매: ");
		JLabel lbl1_1 = new JLabel(String.valueOf(sheets.size()));
		JLabel lbl2 = new JLabel("장");
		JLabel lbl2_1 = new JLabel(", ");		
		JLabel lbl3 = new JLabel("게임: ");
		JLabel lbl3_1 = new JLabel(String.valueOf(numOfGames));
		JLabel lbl4 = new JLabel("게임");
		pnl5.add(lbl1);
		pnl5.add(lbl1_1);
		pnl5.add(lbl2);
		pnl5.add(lbl2_1);
		pnl5.add(lbl3);
		pnl5.add(lbl3_1);
		pnl5.add(lbl4);

		// 패널 표시해주기
		pnlBox.add(pnl1);
		pnlBox.add(pnl2);
		pnlBox.add(pnl3);
		pnlBox.add(pnl4);
		pnlBox.add(pnl5);
		
		pnlFirst.add(pnlLoading);
		pnlFirst.add(pnlBox);
		pnlBox.setVisible(false);
		
		add(pnlFirst);
		setModal(true);
		setSize(700,500);
	}
	// 상금 넣기
	public void getWinMoney() {
		Random random = new Random();
		
		winMoney[0] =random.nextInt(1147483647) + 1000000000;
		winMoney[1] =random.nextInt(40000000) + 50000000;
		winMoney[2] =random.nextInt(1000000) + 1000000;
		winMoney[3] =50000;
		winMoney[4] =5000;

		winPrice.add(winMoney[0]);
	}
	
	// 숫자 비교하기
	public void getDuplicateNums(List winNumList) {
		// 비교하기
		for (int i = 0; i < sheets.size(); i++) {
			Map<Integer, List<Integer>> tempMap = new TreeMap<>();
			for (int j = 0; j < 5; j++) {
				if(sheets.get(i).get(j) != null) {
					Set<Integer> duplicate = new HashSet<>();
					duplicate.addAll(sheets.get(i).get(j));			
					duplicate.retainAll(winNumList);
					List<Integer> duplicateToList = new ArrayList<>(duplicate);
					Collections.sort(duplicateToList);
					tempMap.put(j, duplicateToList);	
				}
			}
			duplicateList.put(i, tempMap);
		}
	}
	
	// 표 안에 번호, 순위, 종류, 맞는 숫자 표로 알려주는 메소드
	public void showResult(int pageCount) {
		resultMoney = 0;
		winCheck = false;
		
		for (int i = 0; i < lblSequences.length; i++) {
			lblSequences[i] = new JLabel();
		}

		for (int i = 0; i < lblTypes.length; i++) {
			lblTypes[i] = new JLabel();
		}
		
		for (int i = 0; i < lblRanks.length; i++) {
			lblRanks[i] = new JLabel("1등");
		}
		
		userNum.putAll(sheets.get(pageCount));
		duplicateNum.putAll(duplicateList.get(pageCount));
		
		for (int i = 0; i < gameNum.get(pageCount); i++) {
			int indexOfRound = searchRounds[pageCount][i];
				for (int j = 0; j < 6; j++) {
					try {
						if(lblUserNumbers[indexOfRound][j] == null) {
							String imgName2 = "normal/ball" + userNum.get(indexOfRound).get(j) +".png";
							lblUserNumbers[indexOfRound][j] = new JLabel(convertToIcon(imgName2, 30, 30));
						}
						if(userNum.get(indexOfRound).indexOf(duplicateNum.get(indexOfRound).get(j)) >= 0) {
							index = userNum.get(indexOfRound).indexOf(duplicateNum.get(indexOfRound).get(j));
							String imgName = "balls/ball" + duplicateNum.get(indexOfRound).get(j) + ".png" ;
							lblUserNumbers[indexOfRound][index] = new JLabel(convertToIcon(imgName, 30, 30));
						} 
					} catch (IndexOutOfBoundsException e) {
						continue;
					}
				}
				duplicateSize.add(duplicateNum.get(indexOfRound).size());
		}
		
		for (int i = 0; i < gameNum.get(pageCount); i++) {
			indexOfRound = searchRounds[pageCount][i];
			pnl4BoxSets[i] = new JPanel();
			pnl4BoxSets[i].setBackground(Color.WHITE);
			pnl4BoxSets[i].setBorder(new LineBorder(Color.black, 1 , true));
			pnl4BoxSets[i].setLayout(new FlowLayout(FlowLayout.CENTER));
			pnl4BoxSets[i].add(lblSequences[i]);
			lblSequences[i].setText(String.valueOf((char) (indexOfRound + 65)));
			pnl4BoxSets[i].add(Box.createHorizontalStrut(5));
			pnl4BoxSets[i].add(lblTypes[i]);
			String type = sheetTypes.get(pageCount).get(indexOfRound);
			lblTypes[i].setText(type);
			lblTypes[i].setPreferredSize(new Dimension(40, 15));
			pnl4BoxSets[i].add(lblRanks[i]);
			if(duplicateSize.get(i) == 6) {
				lblRanks[i].setText(" 1등");
				winCheck = true;
				resultMoney += winMoney[0];
			} else if (duplicateSize.get(i) == 5  && userNum.get(indexOfRound).contains(winNumBonus)) {
				lblRanks[i].setText(" 2등");
				winCheck = true;
				resultMoney += winMoney[1];
			} else if (duplicateSize.get(i) == 5) {
				lblRanks[i].setText(" 3등");
				winCheck = true;
				resultMoney += winMoney[2];
			} else if (duplicateSize.get(i) == 4) {
				lblRanks[i].setText(" 4등");
				winCheck = true;
				resultMoney += winMoney[3];
			} else if (duplicateSize.get(i) == 3) {
				lblRanks[i].setText(" 5등");
				winCheck = true;
				resultMoney += winMoney[4];
			} else {
				lblRanks[i].setText("낙첨");
			} 
			pnl4BoxSets[i].add(Box.createHorizontalStrut(5));
			for (int j = 0; j < 6; j++) {
				pnl4BoxSets[i].add(lblUserNumbers[indexOfRound][j]);			
			}
			pnl4Box.add(pnl4BoxSets[i]);			
		}
		pnl4Box.add(pnl4Page);
		userNum.clear();
		duplicateNum.clear();
		duplicateSize.clear();		
	}
	
	// 당첨결과와 당첨금 알려주는 메소드
	public void showResultWord() {
		DecimalFormat df = new DecimalFormat("###,###");
		String resultMoneyWord = df.format(resultMoney);
		if (!winCheck) {
			resultWord.setText("<html><body><center>아쉽게도,<br>낙첨되었습니다.</center></body></html>");
		} else {
			resultWord.setText("<html><body><center>축하합니다!<br>총 "+ resultMoneyWord + "원 당첨되었습니다.</center></body></html>");
		}
	}
	
	// 아이콘 표시
	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		return icon;
		
	}
	
	// gif표시
	public ImageIcon convertToIconGIF(String name) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		ImageIcon icon = new ImageIcon(image);
		return icon;
		
	}
	
	// gui보여주는 메소드
	public void showGUI() {
		setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	} 
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	Object command = e.getSource();
	
	if (command == btnSkip) {
		timer.cancel();
		pnlLoading.setVisible(false);
		pnlBox.setVisible(true);
	}
	
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 6; j++) {
			lblUserNumbers[i][j] = null;
		}
	}
	
	if (command == before && pageCount > 0) {
		pnl4Box.removeAll();
		pageCount--;
		showResult(pageCount);
		showResultWord();
		pnl4Box.revalidate();
		pnl4Box.repaint();
		currentPage.setText(Integer.toString(pageCount + 1));
	}
	if (command == after && pageCount < sheets.size() -1) {
		pnl4Box.removeAll();
		pageCount++;
		showResult(pageCount);
		showResultWord();
		pnl4Box.revalidate();
		pnl4Box.repaint();
		currentPage.setText(Integer.toString(pageCount + 1));
	}
		
	}
	
}
public class resultScreen {
	public static void main(String[] args) {
		//new resultScreenSet().showGUI();
	}
}