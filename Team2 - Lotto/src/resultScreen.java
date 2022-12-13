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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
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
	private JButton before;
	private JButton after;
	private JLabel[][] lblUserNumbers  = new JLabel[5][6];
	private int sheetNum;
	private List<Integer> gameNum = new ArrayList<>();
	private Map<Integer, List<Integer>> userNum = new TreeMap<>();
	private Map<Integer, List<Integer>> duplicateNum = new TreeMap<>();
	private Map<Integer, Map<Integer, List<Integer>>> duplicateList = new TreeMap<>();
	private int index = 0;
	private int pageCount = 0;
	private int[][] searchRounds;
	private JPanel[] pnl4BoxSets = new JPanel[5];
	private JLabel[] lblRanks = new JLabel[5];
	private JLabel[] lblTypes = new JLabel[5];;
	private JLabel[] lblSequences = new JLabel[5];;
	private JPanel pnl4Box = new JPanel();
	
	public Map<Integer, Map<Integer, List<Integer>>> getSheets() {
		return sheets;
	}

	public void setSheets(Map<Integer, Map<Integer, List<Integer>>> sheets) {
		this.sheets = sheets;
	}

	public resultScreenSet(Map<Integer, Map<Integer, List<Integer>>> sheets, Map<Integer, Map<Integer, String>> sheetTypes) {	
		this.sheets = sheets;
		this.sheetTypes = sheetTypes;
		
		sheetNum = sheets.size();
		for (int i = 0; i < sheetNum; i++) {
			gameNum.add(sheets.get(i).size());
		}
		
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
		
		// 당첨결과 여섯개 랜덤 숫자 만들기
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
		System.out.println("당첨번호" + winNumList);
		// 비교하기
		for (int i = 0; i < sheetNum; i++) {
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
		System.out.println("겹치는거" + duplicateList);			
		
		// panel 구성
		JPanel pnlBox = new JPanel();
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		JPanel pnl5 = new JPanel();
		
		// 전체적인 panel
		pnlBox.setLayout(new BoxLayout(pnlBox, BoxLayout.Y_AXIS));
		
		// 제일위 당첨결과 title panel
		JLabel turn = new JLabel("-");
		JLabel title = new JLabel("회 당첨결과");
		turn.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		title.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		pnl1.add(turn);
		pnl1.add(title);
		
		// 당첨결과 알려주는 공 표시해주는 panel
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.Y_AXIS));
		JPanel pnl2_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel pnl2_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		pnl2_2.add(Box.createHorizontalStrut(280));
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
		pnl2_1.setPreferredSize(new Dimension(0, 40));
		pnl2_2.setPreferredSize(new Dimension(0, 10));
		pnl2.add(pnl2_1);
		pnl2.add(pnl2_2);
		//panel 크기 확인용
//		pnl2_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		
		// 회색 박스(당첨결과와 돈) 알려주는 panel
		JPanel pnl3_1 = new JPanel();
		pnl3_1.setPreferredSize(new Dimension(450, 45));
		pnl3_1.setBackground(Color.LIGHT_GRAY);
		JLabel resultWord = new JLabel("축하합니다! 총 ");
		JLabel resultWord2 = new JLabel("원 당첨입니다!");
		JLabel resultMoney = new JLabel("-");
		resultWord.setFont(new Font("휴먼편지체", Font.BOLD, 18));
		resultWord2.setFont(new Font("휴먼편지체", Font.BOLD, 18));
		resultMoney.setFont(new Font("휴먼편지체", Font.BOLD, 18));
		resultWord.setForeground(Color.white);
		resultWord2.setForeground(Color.white);
		resultMoney.setForeground(Color.white);
		pnl3_1.add(Box.createVerticalStrut(40));
		pnl3_1.add(resultWord);
		pnl3_1.add(resultMoney);		
		pnl3_1.add(resultWord2);
		pnl3.add(Box.createHorizontalStrut(5));
		pnl3.add(pnl3_1);
		
		before = new JButton("<<<");
		after = new JButton(">>>");
		pnl4Box.setLayout(new BoxLayout(pnl4Box, BoxLayout.Y_AXIS));
		pnl4Box.setBackground(Color.WHITE);
		showResult(0);
		
		
		before.setPreferredSize(new Dimension(60,40));
		before.setBackground(Color.LIGHT_GRAY);
		before.addActionListener(this);
		after.setPreferredSize(new Dimension(60,40));
		after.setBackground(Color.LIGHT_GRAY);
		after.addActionListener(this);
		pnl4.add(before);
		pnl4.add(Box.createHorizontalStrut(30));
		pnl4.add(pnl4Box);
		pnl4.add(Box.createHorizontalStrut(30));
		pnl4.add(after);
		
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
		
		add(pnlBox);
		setModal(true);
		setSize(700,500);
	}
	
	public void showGUI() {
		setVisible(true);
	}
	
	public void showResult(int pageCount) {
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
							String imgName2 = "numbers/num" + userNum.get(indexOfRound).get(j) +".png";
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
		}
		
		userNum.clear();
		duplicateNum.clear();
		
		for (int i = 0; i < gameNum.get(pageCount); i++) {
			int indexOfRound = searchRounds[pageCount][i];
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
			pnl4BoxSets[i].add(Box.createHorizontalStrut(5));
			pnl4BoxSets[i].add(lblRanks[i]);
			pnl4BoxSets[i].add(Box.createHorizontalStrut(5));
			for (int j = 0; j < 6; j++) {
				pnl4BoxSets[i].add(lblUserNumbers[i][j]);			
			}
			pnl4Box.add(pnl4BoxSets[i]);			
		}		
	}	
	
	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		return icon;
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// 다시 수정예정............
////		Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
////		sheets.get(0).get(0);
//		Object command = e.getSource();
//		List<Integer> test = new ArrayList<>(Arrays.asList(1,6,12,16,25,34));
//		
//		if (command == before) {
//			for (int i = 0; i < test.size(); i++) {
//				for (int j = 0; j < 46; j++) {
//					if (test.contains(j)) {
//						test.indexOf(i);
//					ImageIcon image = convertToIcon("balls/ball" + j +".png", 30, 30);
//					lblUserNumbers[0][i].setIcon(image);
//					}
//				}
//			}				
//		}
		
		Object command = e.getSource();
		if (command == before) {
			for (int i = 0; i < lblUserNumbers.length; i++) {
				for (int j = 0; j < lblUserNumbers[i].length; j++) {
					ImageIcon image = convertToIcon("balls/ball5.png", 30, 30);
					lblUserNumbers[i][j].setIcon(image);
				}
			}
		}
		if (command == after) {
			for (int i = 0; i < lblUserNumbers.length; i++) {
				for (int j = 0; j < lblUserNumbers[i].length; j++) {
					ImageIcon image = convertToIcon("balls/ball7.png", 30, 30);
					lblUserNumbers[i][j].setIcon(image);
				}
			}
		}
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
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 6; j++) {
			lblUserNumbers[i][j] = null;
		}
	}
	
	if (command == before && pageCount > 0) {
		pnl4Box.removeAll();
		pageCount--;
		showResult(pageCount);
		pnl4Box.revalidate();
		pnl4Box.repaint();
	}
	if (command == after && pageCount < sheets.size() -1) {
		pnl4Box.removeAll();
		pageCount++;
		showResult(pageCount);
		pnl4Box.revalidate();
		pnl4Box.repaint();
	}
		
	}
	
}
public class resultScreen {
	public static void main(String[] args) {
		//new resultScreenSet().showGUI();
	}
}