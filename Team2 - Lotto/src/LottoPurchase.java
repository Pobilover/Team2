import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

class Purchase extends JDialog implements MouseListener, ActionListener, ItemListener {
	private JPanel pnlNum;
	private boolean[] lottoNumsIcon;
	private JLabel lblNowGame;
	private JLabel[][] lblChoNums2;
	private int[] nowGameCounters;
	private JButton btnOk;
	private Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
	private Map<Integer, List<Integer>> inputRounds = new TreeMap<>();
	private List<Integer> inputNumbers = new ArrayList<>();
	private JLabel lblSheet;
	private JComboBox cbQuantity;
	private int numOfGame = 0;
	private boolean[] numOk = new boolean[5];
	private JButton btnReset;
	private JToggleButton btnAuto;
	private JCheckBox ckAuto;
	private RoundedButton btnAllReset;
	private List<JLabel> lblNumbers;
	private List<JPanel> pnlChoSets;
	private List<JLabel> lblTypes;
	private List<JButton> btnChoResets;
	private List<JButton> btnChoDeletes;
	private JLabel lblFinish;
	private JPanel pnlAllAuto;
	private JLabel lblGame;
	private JLabel lbltotal;
	private int numOfSheet = 0;
	private int numOfGames = 0;
	private JButton btnPurchase;
	private JButton btnFinish;
	private RoundedButton btnManual;
	
	public Map<Integer, Map<Integer, List<Integer>>> getSheets() {
		return sheets;
	}

	public void setSheets(Map<Integer, Map<Integer, List<Integer>>> sheets) {
		this.sheets = sheets;
	}

	public Purchase() {
		setModal(true); // 부모 창이랑 상호작용 못하게 막음
		
		setUIFont(new FontUIResource(new Font("휴먼편지체", 0, 15)));
		
		// 제일 큰 패널
		MyImageBackgroundPanel pnlBuy = new MyImageBackgroundPanel(new Methods().backgroud("배경.png"));
		
		// 로또번호 선택가능한 패널
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(Color.WHITE);
		pnlWest.setPreferredSize(new Dimension(230, 450));
		pnlWest.setBorder(new LineBorder(Color.black, 2, true));
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		
		// 로또번호 선택창 배너이미지
		JPanel pnlImage = new JPanel();
		pnlImage.setPreferredSize(new Dimension(200, 65));
		pnlImage.setBackground(Color.WHITE);
		JLabel lottoImage = new JLabel();
		lottoImage.setIcon(getIcon("lotto123.png", 230, 80));
		pnlImage.add(lottoImage);
		
		// 로또번호 선택창 현재 조 알려주는 패널
		JPanel pnlNowGame = new JPanel();
		pnlNowGame.setPreferredSize(new Dimension(200, 10));
		JLabel lblNowGameT = new JLabel("현재 작성중인 조 : ");
		lblNowGame = new JLabel("A");
		lblFinish = new JLabel("선택완료");
		pnlNowGame.add(lblNowGameT);
		pnlNowGame.add(lblNowGame);
		pnlNowGame.add(lblFinish);
		lblFinish.setVisible(false);
		
		// 로또번호 패널
		pnlNum = new JPanel();
		pnlNum.setBackground(Color.WHITE);
		lblNumbers = new ArrayList<>(); // 1 ~ 45 번호
		lottoNumsIcon = new boolean[45]; // 번호 선택여부 확인
		pnlNum.setLayout(new GridLayout(7, 7, 0, 0));
		for (int i = 0; i < 45; i++) {
			String name = "num" + (i + 1) + ".png";
			lblNumbers.add(new JLabel(getIcon("numbers/" + name, 30, 30)));
			lblNumbers.get(i).addMouseListener(this);
			pnlNum.add(lblNumbers.get(i));
			lottoNumsIcon[i] = false; 
		}
		// 자동 선택시 보여줄 패널
		pnlAllAuto = new JPanel();
		pnlAllAuto.setPreferredSize(new Dimension(200, 210));
		pnlAllAuto.setBackground(new Color(200, 200, 200, 200));
		JPanel pnlAll = new JPanel();
		JLabel lblAllAuto = new JLabel("<html><body><center>구매하기 완료시<br>로또번호가 자동으로<br>생성됩니다.</center></body></html>");
		lblAllAuto.setForeground(Color.white);
		pnlAll.setPreferredSize(new Dimension(150, 100));
		pnlAll.add(Box.createVerticalStrut(85));
		pnlAll.add(lblAllAuto);
		pnlAll.setBackground(Color.DARK_GRAY);
		pnlAll.setBorder(new LineBorder(Color.RED, 2, true));
		pnlAllAuto.add(Box.createVerticalStrut(200));
		pnlAllAuto.add(pnlAll, "Center");
			
		// 로또번호 선택기능 패널
		JPanel pnlNumSkill1 = new JPanel();
		pnlNumSkill1.setPreferredSize(new Dimension(200, 15));
		pnlNumSkill1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNumSkill1.setBackground(Color.WHITE);
		btnReset = new JButton("초기화");
		btnReset.addActionListener(this);
		btnAuto = new JToggleButton("자동선택");
		btnAuto.addItemListener(this);
		pnlNumSkill1.add(btnReset);
		pnlNumSkill1.add(Box.createHorizontalStrut(10));
		pnlNumSkill1.add(btnAuto);
		
		// 로또번호 수량선택 패널
		JPanel pnlNumSkill2 = new JPanel();
		pnlNumSkill2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNumSkill2.setBackground(Color.WHITE);
		JLabel lblQuantity = new JLabel("적용수량");
		String[] QuantityList = {"1", "2", "3", "4", "5"};
		cbQuantity = new JComboBox(QuantityList);
		cbQuantity.setPreferredSize(new Dimension(80, 20));
		btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		ckAuto = new JCheckBox("선택수량 자동선택");
		ckAuto.setBackground(Color.white);
		ckAuto.addItemListener(this);
		pnlNumSkill2.add(lblQuantity);
		pnlNumSkill2.add(cbQuantity);
		pnlNumSkill2.add(Box.createHorizontalStrut(5));
		pnlNumSkill2.add(btnOk);
		pnlNumSkill2.add(ckAuto);
		
		pnlWest.add(pnlImage);
		pnlWest.add(pnlNowGame);
		pnlWest.add(pnlNum);
		pnlWest.add(pnlAllAuto);
		pnlAllAuto.setVisible(false);
		pnlWest.add(pnlNumSkill1);
		pnlWest.add(pnlNumSkill2);
		
		pnlBuy.add(pnlWest);
		
		
		// 로또번호 선택 보이는 큰 패널
		JPanel pnlEast = new JPanel();
		pnlEast.setPreferredSize(new Dimension(450, 450));
		pnlEast.setBorder(new LineBorder(Color.black, 2, true));
		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		
		// 전체 초기화기능 패널
		JPanel pnlReset = new JPanel();
		JLabel lblConfirm = new JLabel("선택번호 확인");
		lblConfirm.setFont(new Font("휴먼편지체", 1, 20));
		btnManual = new RoundedButton("사용법");
		btnManual.addActionListener(this);
		btnManual.setBackground(new Color(200, 128, 128));
		btnAllReset = new RoundedButton("초기화");
		btnAllReset.setBackground(new Color(128, 128, 128));
		btnAllReset.setForeground(Color.white);
		btnAllReset.addActionListener(this);
		pnlReset.add(lblConfirm);
		pnlReset.add(Box.createHorizontalStrut(135));
		pnlReset.add(btnManual);
		pnlReset.add(btnAllReset);
		
		// 선택한 번호 보이는 패널
		JPanel pnlChoNum = new JPanel();
		pnlChoNum.setBackground(Color.WHITE);
		pnlChoNum.setLayout(new BoxLayout(pnlChoNum, BoxLayout.Y_AXIS));
		pnlChoSets = new ArrayList<>();
		lblTypes = new ArrayList<>();
		btnChoResets = new ArrayList<>();
		btnChoDeletes = new ArrayList<>();
		lblChoNums2 = new JLabel[5][6];
		for (int i = 0; i < 5; i++) {
			// 각 게임당 하나의 패널 선언
			pnlChoSets.add(i, new JPanel());
			pnlChoSets.get(i).setLayout(new FlowLayout(FlowLayout.CENTER));			
			pnlChoSets.get(i).setBackground(Color.WHITE);
			pnlChoSets.get(i).setBorder(new LineBorder(Color.black, 1, true));
			pnlChoSets.get(i).addMouseListener(this);
			// 게임의 조(A, B, C, D, E)를 나타낼 라벨
			char ch = (char) (i + 65);
			JLabel lblChoRound = new JLabel(String.valueOf(ch));
			// 게임의 타입을 나타내줄 라벨
			lblTypes.add(i, new JLabel("미지정"));
			lblTypes.get(i).setPreferredSize(new Dimension(42, 14));
			// 각 줄의 선택한 번호들 이미지 표시할 라벨
			for (int j = 0; j < lblChoNums2[i].length ; j++) {
				ImageIcon choiceImage = getIcon("balls/" + "ballNull.png", 30, 30);
				lblChoNums2[i][j] = new JLabel(choiceImage);
			}
			// 수정, 삭제 버튼
			btnChoResets.add(i, new JButton("수정"));
			btnChoResets.get(i).addActionListener(this);
			btnChoDeletes.add(i, new JButton("삭제"));
			btnChoDeletes.get(i).addActionListener(this);
			// 각 줄에 컴포넌트 삽입
			pnlChoSets.get(i).add(lblChoRound);
			pnlChoSets.get(i).add(lblTypes.get(i));
			pnlChoSets.get(i).add(Box.createHorizontalStrut(5));
			for (int j = 0; j < 6; j++) {
				pnlChoSets.get(i).add(lblChoNums2[i][j]);
			}
			pnlChoSets.get(i).add(Box.createHorizontalStrut(5));
			pnlChoSets.get(i).add(btnChoResets.get(i));
			pnlChoSets.get(i).add(btnChoDeletes.get(i));
			pnlChoNum.add(pnlChoSets.get(i));
		}
		nowGameCounters = new int[5];
		for (int i = 0; i < 5; i++) {
			nowGameCounters[i] = 0;
		}
		
		
		// 금액과 구매버튼 패널
		JPanel pnlPurchase = new JPanel();
		pnlPurchase.setLayout(new FlowLayout());
		JLabel lblSheetT = new JLabel("구매수: ");
		lblSheet = new JLabel("0");
		JLabel lblSheetTT = new JLabel("장");
		JLabel lblGameT = new JLabel("구매게임: ");
		lblGame = new JLabel("0");
		JLabel lblGameTT = new JLabel("개");
		JLabel lbltotalT = new JLabel("결제 금액: ");
		lbltotal = new JLabel("0");
		JLabel lblTotal = new JLabel("원");
		
		btnPurchase = new JButton("구매하기");
		btnPurchase.addActionListener(this);
		btnFinish = new JButton("구매종료");
		btnFinish.addActionListener(this);
		pnlPurchase.add(lblSheetT);		
		pnlPurchase.add(lblSheet);
		pnlPurchase.add(lblSheetTT);
		pnlPurchase.add(Box.createHorizontalStrut(10));
		pnlPurchase.add(lblGameT);
		pnlPurchase.add(lblGame);
		pnlPurchase.add(lblGameTT);
		pnlPurchase.add(Box.createHorizontalStrut(10));
		pnlPurchase.add(lbltotalT);
		pnlPurchase.add(lbltotal);
		pnlPurchase.add(lblTotal);
		pnlPurchase.add(Box.createHorizontalStrut(150));
		pnlPurchase.add(btnPurchase);
		pnlPurchase.add(btnFinish);
		
		pnlEast.add(pnlReset);
		pnlEast.add(pnlChoNum);
		pnlEast.add(pnlPurchase);
		pnlBuy.add(pnlEast);
		
		add(pnlBuy);

		setSize(720, 500);
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		Object command = e.getSource();
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65;
		if (pnlChoSets.contains(command)) {
			clearSelectedNumber(nowGame);
			int index = pnlChoSets.indexOf(command);
			char round = (char) (index + 65);
			if (!numOk[index]) {
				lblNowGame.setText(String.valueOf(round));
			}
		}
		if (lblNumbers.contains(command) && !numOk[nowGame] && !ckAuto.isSelected()) {
			int index = lblNumbers.indexOf(command);
			String name = "num" + (index + 1) + ".png";
			if (lottoNumsIcon[index] && nowGameCounters[nowGame] <= 6) {
				lblNumbers.get(index).setIcon(getIcon("numbers/" + name, 30, 30)); 
				lottoNumsIcon[index + 1] = false;
				nowGameCounters[nowGame]--;
				inputNumbers.remove(inputNumbers.indexOf(index + 1));
			} else if (!lottoNumsIcon[index] && nowGameCounters[nowGame] < 6) {
				lblNumbers.get(index).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				lottoNumsIcon[index] = true;
				nowGameCounters[nowGame]++;
				inputNumbers.add(index + 1);
			} else if (!lottoNumsIcon[index] && nowGameCounters[nowGame] >= 6) {
				JOptionPane.showMessageDialog(null, "최대 6개의 숫자를 선택할 수 있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object command = e.getSource();
		if (pnlChoSets.contains(command)) {
			int index = pnlChoSets.indexOf(command);
			pnlChoSets.get(index).setBackground(new Color(180, 180, 180));
		}
		if (lblNumbers.contains(command)) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
			int index = lblNumbers.indexOf(command);
			lblNumbers.get(index).setCursor(cursor);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object command = e.getSource();
		if (pnlChoSets.contains(command)) {
			int num = pnlChoSets.indexOf(command);
			pnlChoSets.get(num).setBackground(Color.white);
		}	
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
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65;
		int selectedQuantity = cbQuantity.getSelectedIndex() + 1;
		int games = numOfGame + selectedQuantity; 
		if (command == btnOk && games > 5) {
			JOptionPane.showMessageDialog(null, "1장당 최대 5개의 게임만 구매가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		} else if (command == btnOk && ckAuto.isSelected()) {
			List<Integer> possible = searchPossible(nowGame);
			randomNumbers(nowGame, inputNumbers);
			changeBallIcon(nowGame, inputNumbers, "등록", "자동");
			inputNumbers.clear();
			for (int i = 0; i < selectedQuantity - 1; i++) {
				nowGame = possible.get(i);
				inputNumbers = randomNumbers(nowGame, inputNumbers);
				changeBallIcon(nowGame, inputNumbers, "등록", "자동");		
				inputNumbers.clear();
			}
			cbQuantity.setSelectedIndex(0);
			searchPossible(nowGame);
			clearSelectedNumber(nowGame);			
		} else if (command == btnOk && nowGameCounters[nowGame] == 6) {
			if (inputNumbers.contains(100)) {
				changeBallIcon(nowGame, inputNumbers, "등록", "반자동");
			} else {
				changeBallIcon(nowGame, inputNumbers, "등록", "수동");
			}
			selectedQuantity--;
			int temp = selectedQuantity;
			List<Integer> possible = searchPossible(nowGame);
			for (int i = 0; i < temp; i++) {
				nowGame = possible.get(i); 
				inputRounds.put(nowGame, inputNumbers);
				changeBallIcon(nowGame, inputNumbers, "등록", "수동");	
				selectedQuantity--;
			}
			cbQuantity.setSelectedIndex(0);
			searchPossible(nowGame);
			clearSelectedNumber(nowGame);
		} else if (command ==btnOk && btnAuto.isSelected()) {
			int count = 6 - nowGameCounters[nowGame];
			if (count == 6) {
				randomNumbers(nowGame, inputNumbers);
				changeBallIcon(nowGame, inputNumbers, "등록", "자동");		
				cbQuantity.setSelectedIndex(0);
				searchPossible(nowGame);
				clearSelectedNumber(nowGame);
				selectedQuantity--;
				int temp = selectedQuantity;
				List<Integer> possible = searchPossible(nowGame);
				for (int i = 0; i < temp; i++) {
					nowGame = possible.get(i); 
					randomNumbers(nowGame, inputNumbers);
					changeBallIcon(nowGame, inputNumbers, "등록", "수동");	
					selectedQuantity--;
				}				
			} else if (count <= 5) {
				randomNumbers(nowGame, inputNumbers);
				changeBallIcon(nowGame, inputNumbers, "등록", "반자동");
				selectedQuantity--;
				List<Integer> possible = searchPossible(nowGame);
				for (int i = 0; i < selectedQuantity; i++) {
					nowGame = possible.get(i); 
					inputRounds.put(nowGame, inputNumbers);
					changeBallIcon(nowGame, inputNumbers, "등록", "반자동");			
				}				
				cbQuantity.setSelectedIndex(0);
				searchPossible(nowGame);
				clearSelectedNumber(nowGame);
			}
		} else if (command == btnOk && nowGameCounters[nowGame] < 6) {
			JOptionPane.showMessageDialog(null, "6개의 번호를 선택해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}
		if (command == btnReset) {
			clearSelectedNumber(nowGame);
		}
		if (command == btnAllReset) {
			for (int i = 0; i < 5; i++) {
				if (inputRounds.get(i) != null) {
					changeBallIcon(i, inputNumbers, "삭제", "미지정");
					cbQuantity.setSelectedIndex(0);
					searchPossible(nowGame);
					clearSelectedNumber(nowGame);
					lblNowGame.setText("A");
				}
			}
		}
		if (btnChoResets.contains(command)) {
			int index = btnChoResets.indexOf(command);
			char temp = (char) (index + 65);
			lblNowGame.setText(String.valueOf(temp));
			lblNowGame.setVisible(true);
			lblFinish.setVisible(false);
			nowGame = (int) lblNowGame.getText().charAt(0) - 65;
			if (inputRounds.get(nowGame) != null) {
				showSelectedNumber(nowGame);
				numOfGame--;
				numOfGames--;
			}
		}
		if (btnChoDeletes.contains(command)) {
			int index = btnChoDeletes.indexOf(command);
			char temp = (char) (index + 65);
			lblNowGame.setText(String.valueOf(temp));
			lblNowGame.setVisible(true);
			lblFinish.setVisible(false);
			if (inputRounds.get(index) != null) {
				nowGame = lblNowGame.getText().charAt(0) - 65;
				changeBallIcon(nowGame, inputNumbers, "삭제", "미지정");
			}
			clearSelectedNumber(nowGame);
		}
		if (command == btnPurchase && numOfGame != 0) {
			List<Integer> existence = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				if (inputRounds.get(i) != null) {
					existence.add(i);
				}
			}
			Collections.sort(existence);
			for (int i = 0; i < inputRounds.size(); i++) {
				int numI = existence.get(i);
				if (inputRounds.get(numI).contains(100)) {
					do {
						int index = inputRounds.get(numI).indexOf(100);
						if (index != -1) {
							inputRounds.get(numI).remove(index);	
						}			
					} while (inputRounds.get(numI).contains(100));
					Set<Integer> tempSet = new TreeSet<>();	
					tempSet.addAll(inputRounds.get(numI));
					Random random = new Random();
					while (tempSet.size() < 6) {
						int num = random.nextInt(45);
						tempSet.add(num);
						inputRounds.get(numI).clear();
						inputRounds.get(numI).addAll(tempSet);
						Collections.sort(inputRounds.get(numI));	
					}					
				}
			}
			Map<Integer, List<Integer>>[] tempMap = new TreeMap[100];
			tempMap[numOfSheet] = new TreeMap<>();
			tempMap[numOfSheet].putAll(inputRounds);
			sheets.put(numOfSheet, tempMap[numOfSheet]);
			for (int i = 0; i < 5; i++) {
				if (inputRounds.get(i) != null) {
					changeBallIcon(i, inputNumbers, "삭제", "미등록");
					numOfGames++;
				}
				clearSelectedNumber(i);
				nowGameCounters[i] = 0;
				numOk[i] = false;
			}
			inputRounds.clear();
			numOfSheet++;
			lblSheet.setText(String.valueOf(numOfSheet));
			lblNowGame.setVisible(true);
			lblFinish.setVisible(false);			
			lblNowGame.setText("A");
			lblGame.setText(String.valueOf(numOfGames));
			System.out.println(sheets);
		} else if (command == btnPurchase && numOfGame == 0) {
			JOptionPane.showMessageDialog(null, "1게임 이상 등록되어야 구매가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}
		lblGame.setText(String.valueOf(numOfGames));
		lbltotal.setText(String.valueOf(numOfGames * 1000));		
		if (command == btnFinish) {
			
		}
		if (command == btnManual) {
			new Manual().showGUI();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65;
		if (ckAuto.isSelected()) {	
			clearSelectedNumber(nowGame);
			pnlNum.setVisible(false);
			pnlAllAuto.setVisible(true);
			btnReset.setEnabled(false);
			btnAuto.setEnabled(false);
		}
		if (!ckAuto.isSelected()) {
			pnlNum.setVisible(true);
			pnlAllAuto.setVisible(false);		
			btnReset.setEnabled(true);
			btnAuto.setEnabled(true);			
		}
	}
	
	public List<Integer> randomNumbers(int nowGame, List<Integer> inputNumbers) {
		Collections.sort(inputNumbers);
		while (inputNumbers.size() < 6) {
			inputNumbers.add(100);
		}
		for (int i =0; i < 6; i++) {
			int num = inputNumbers.get(i);
			String name = "num" + (num + 1) + ".png";
			if (num != 100) {
				lblNumbers.get(num).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				lottoNumsIcon[num] = true;
			}
		}
		nowGameCounters[nowGame] = 6;
		return inputNumbers;
	}
	
	public List<Integer> searchPossible(int nowGame) {
		List<Integer> possible = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			if (!numOk[i] && i != nowGame) {
				possible.add(i);
			}
		}
		Collections.sort(possible);
		if (possible.size() != 0) {
			lblNowGame.setVisible(true);
			lblFinish.setVisible(false);
			char round = (char) (possible.get(0) + 65); 
			lblNowGame.setText(String.valueOf(round));
		} else {
			lblNowGame.setVisible(false);
			lblFinish.setVisible(true);
		}
		return possible;
	}
	
	public void changeBallIcon(int nowGame, List<Integer> inputNumbers, String function, String type) {
		Collections.sort(inputNumbers);
		if (function.equals("등록")) {
			List<Integer>[] numList = new ArrayList[5];
			for (int i = 0; i < 5; i ++) {
				numList[i] = new ArrayList(6);
			}
			numList[nowGame].addAll(inputNumbers);
			inputRounds.put(nowGame, numList[nowGame]);
			for (int i = 0; i < 6; i++) {
				int number = inputRounds.get(nowGame).get(i);
				String name = "ball" + (number) + ".png";
				if (number == 100) {
					name = "ball100.png";
				}
				lblChoNums2[nowGame][i].setIcon(getIcon("balls/" + name, 30, 30));
			}
			lblTypes.get(nowGame).setText(type);
			numOk[nowGame] = true;
			numOfGame++;
			numOfGames++;
		}
		if (function.equals("삭제")) {
			for (int i = 0; i < 6; i++) {
				String name = "ballNull.png";
				lblChoNums2[nowGame][i].setIcon(getIcon("balls/" + name, 30, 30));
			}
			lblTypes.get(nowGame).setText("미지정");
			inputRounds.remove(nowGame);
			numOk[nowGame] = false;
			numOfGame--;
			numOfGames--;
		}
	}
	
	public void clearSelectedNumber(int nowGame) {
		for (int i = 0; i < 45; i++) {
			String name = "num" + (i + 1) + ".png";
			lblNumbers.get(i).setIcon(getIcon("numbers/" + name, 30, 30));
			lottoNumsIcon[i] = false;
			nowGameCounters[nowGame] = 0;
			inputNumbers.clear();
		}
	}
	public void showSelectedNumber(int nowGame) {
		for (int i = 0; i < 6; i++) {
			inputNumbers = inputRounds.get(nowGame);
			int num = inputNumbers.get(i);
			String name = "num" + (num + 1) + ".png";
			if (num != 100) {
				lblNumbers.get(num).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				lottoNumsIcon[num] = true;
			}
			nowGameCounters[nowGame] = 6;
			numOk[nowGame] = false;
		}		
	}
	
	public ImageIcon getIcon(String name, int width, int height) {
		String imageName = name;
		int w = width;
		int h = height;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Image image = kit.getImage(classLoader.getResource(imageName));
			image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			// 이미지크기조절
			ImageIcon icon = new ImageIcon(image);
			return icon;	
		} catch(NullPointerException e) {
			System.out.println(imageName + "이 없습니다.");
		}
		return null;
	 }
	
    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
    
    public void showGUI() {
    	setVisible(true);
    }
}

public class LottoPurchase {
	public static void main(String[] args) {
		new Purchase().showGUI();
	}
}