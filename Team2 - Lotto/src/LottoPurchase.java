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
import java.util.Arrays;
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
	private JPanel pnlWest_3;
	private boolean[] checkNumber;
	private JLabel lblNowGame;
	private JLabel[][] lblSelectedNum;
	private int[] nowGameCounters;
	private RoundedButton btnOk;
	private Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
	private Map<Integer, List<Integer>> inputRounds = new TreeMap<>();
	private List<Integer> inputNumbers = new ArrayList<>();
	private Map<Integer, Map<Integer, String>> sheetTypes = new TreeMap<>();
	private Map<Integer, String> roundTypes = new TreeMap<>();
	private JLabel lblNumOfSheets;
	private JComboBox cbQuantity;
	private int numOfGame = 0;
	private boolean[] numOk = new boolean[5];
	private RoundedButton btnReset;
	private JToggleButton btnAuto;
	private JCheckBox ckAuto;
	private RoundedButton btnAllReset;
	private List<JLabel> lblNumbers;
	private List<JPanel> pnlGames;
	private List<JLabel> lblTypes;
	private List<JButton> btnSNResets;
	private List<JButton> btnSNDeletes;
	private JLabel lblFinish;
	private JPanel pnlWest3_Allauto;
	private JLabel lblNumOfGames;
	private JLabel lblPrice;
	private int numOfSheet = 0;
	private int numOfGames = 0;
	private RoundedButton btnPurchase;
	private RoundedButton btnFinish;
	private RoundedButton btnManual;
	private boolean[] resetING = new boolean[5];
	private String[] quantityLists;
	private int[] tempInputNumbers; 
	
	public Map<Integer, Map<Integer, List<Integer>>> getSheets() {
		return sheets;
	}

	public void setSheets(Map<Integer, Map<Integer, List<Integer>>> sheets) {
		this.sheets = sheets;
	}
	
	
	public Map<Integer, Map<Integer, String>> getSheetTypes() {
		return sheetTypes;
	}

	public void setSheetTypes(Map<Integer, Map<Integer, String>> sheetTypes) {
		this.sheetTypes = sheetTypes;
	}

	public Purchase(int gameRound) {
		// 전체 폰트 설정
		new Methods().setUIFont(new FontUIResource(new Font("휴먼편지체", 0, 14)));
		
		// 바탕 패널
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.white);
		
		// 로또번호 선택가능한 패널
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(Color.WHITE);
		pnlWest.setPreferredSize(new Dimension(230, 450));
		pnlWest.setBorder(new LineBorder(Color.black, 2, true));
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		
		// pnlWest에 들어갈 1번 - 로또번호 선택창 배너이미지
		JPanel pnlWest_1 = new JPanel();
		pnlWest_1.setPreferredSize(new Dimension(200, 65));
		pnlWest_1.setBackground(Color.WHITE);
		JLabel lblLottoImage = new JLabel();
		lblLottoImage.setIcon(getIcon("lotto123.png", 230, 80));
		pnlWest_1.add(lblLottoImage);
		
		// pnlWest에 들어갈 2번 - 현재 조 알려주는 패널
		JPanel pnlWest_2 = new JPanel();
		pnlWest_2.setBackground(new Color(220,220,220));
		pnlWest_2.setPreferredSize(new Dimension(200, 10));
		JLabel lblNowGameT = new JLabel("현재 작성중인 조 : ");
		lblNowGame = new JLabel("A");
		lblFinish = new JLabel("선택완료");
		pnlWest_2.add(lblNowGameT);
		pnlWest_2.add(lblNowGame);
		pnlWest_2.add(lblFinish);
		lblFinish.setVisible(false); // 5개 조가 모두 작성되면 보이게 
		
		// pnlWest에 들어갈 3번 - 로또번호(1 ~ 45) 패널
		pnlWest_3 = new JPanel();
		pnlWest_3.setBackground(Color.white);
		lblNumbers = new ArrayList<>(); // 1 ~ 45 번호
		checkNumber = new boolean[45]; // 번호 선택여부 확인
		pnlWest_3.setLayout(new GridLayout(7, 7, 0, 0));
		for (int i = 0; i < 45; i++) {
			String name = "num" + (i + 1) + ".png";
			lblNumbers.add(new JLabel(getIcon("numbers/" + name, 30, 30)));
			lblNumbers.get(i).addMouseListener(this);
			pnlWest_3.add(lblNumbers.get(i));
			checkNumber[i] = false; // 번호를 클릭하면 true로 하여 구분할 것임
		}
		
		// 자동 선택시 보여줄 패널
		pnlWest3_Allauto = new JPanel();
		pnlWest3_Allauto.setPreferredSize(new Dimension(200, 210));
		pnlWest3_Allauto.setBackground(new Color(200, 200, 200, 200));
		JPanel pnlAll = new JPanel();
		JLabel lblAllAuto = new JLabel("<html><body><center>구매하기 완료시<br>로또번호가 자동으로<br>생성됩니다.</center></body></html>");
		lblAllAuto.setForeground(Color.white);
		pnlAll.setPreferredSize(new Dimension(150, 100));
		pnlAll.add(Box.createVerticalStrut(85));
		pnlAll.add(lblAllAuto);
		pnlAll.setBackground(Color.DARK_GRAY);
		pnlAll.setBorder(new LineBorder(Color.RED, 2, true));
		pnlWest3_Allauto.add(Box.createVerticalStrut(200));
		pnlWest3_Allauto.add(pnlAll, "Center");
			
		// pnlWest에 들어갈  4번 - 초기화, 자동선택 버튼
		JPanel pnlWest_4 = new JPanel();
		pnlWest_4.setPreferredSize(new Dimension(200, 15));
		pnlWest_4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlWest_4.setBackground(new Color(245, 245, 245));
		btnReset = new RoundedButton("초기화");
		btnReset.setBackground(new Color(220, 220, 220));
		btnReset.addActionListener(this);
		btnAuto = new RoundedToggleButton("자동선택");
		btnAuto.setBackground(new Color(220, 220, 220));
		btnAuto.setBorderPainted(false);
		btnAuto.setFocusable(false);
		btnAuto.addItemListener(this);
		btnAuto.setRolloverEnabled(true);
		pnlWest_4.add(btnReset);
		pnlWest_4.add(Box.createHorizontalStrut(10));
		pnlWest_4.add(btnAuto);
		
		// pnlWest에 들어갈  5번 - 수량선택, 확인 버튼
		JPanel pnlWest_5 = new JPanel();
		pnlWest_5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlWest_5.setBackground(new Color(245, 245, 245));
		JLabel lblQuantity = new JLabel("적용수량");
		quantityLists = new String[] {"1", "2", "3", "4", "5"};
		cbQuantity = new JComboBox(quantityLists);
		cbQuantity.setPreferredSize(new Dimension(80, 20));
		btnOk = new RoundedButton("확인");
		btnOk.setBackground(new Color(220, 220, 220));
		btnOk.addActionListener(this);
		ckAuto = new JCheckBox("선택수량 자동선택");
		ckAuto.setBackground(new Color(245, 245, 245));
		ckAuto.addItemListener(this);
		pnlWest_5.add(lblQuantity);
		pnlWest_5.add(cbQuantity);
		pnlWest_5.add(btnOk);
		pnlWest_5.add(ckAuto);
		
		// pnlWest에 각 패널들 추가
		pnlWest.add(pnlWest_1);
		pnlWest.add(pnlWest_2);
		pnlWest.add(pnlWest_3);
		pnlWest.add(pnlWest3_Allauto);
		pnlWest3_Allauto.setVisible(false);
		pnlWest.add(pnlWest_4);
		pnlWest.add(pnlWest_5);
		
		
		// 로또 선택한 번호가 보이는 패널
		JPanel pnlEast = new JPanel();
		pnlEast.setPreferredSize(new Dimension(450, 450));
		pnlEast.setBorder(new LineBorder(Color.black, 2, true));
		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		
		// pnlEast에 들어갈 1번 - 사용법, 전체 초기화버튼
		JPanel pnlEast_1 = new JPanel();
		JLabel lblConfirmT = new JLabel(gameRound + 1 + "회차 선택번호 확인");
		lblConfirmT.setFont(new Font("휴먼편지체", 1, 20));
		btnManual = new RoundedButton("사용법");
		btnManual.addActionListener(this);
		btnManual.setBackground(new Color(200, 128, 128));
		btnAllReset = new RoundedButton("초기화");
		btnAllReset.setBackground(new Color(128, 128, 128));
		btnAllReset.setForeground(Color.white);
		btnAllReset.addActionListener(this);
		pnlEast_1.add(lblConfirmT);
		pnlEast_1.add(Box.createHorizontalStrut(50));
		pnlEast_1.add(btnManual);
		pnlEast_1.add(btnAllReset);
		
		// pnlEast에 들어갈 2번 - 선택한 각 조의 번호, 수정 & 삭제 버튼
		JPanel pnlEast_2 = new JPanel();
		pnlEast_2.setBackground(Color.WHITE);
		pnlEast_2.setLayout(new BoxLayout(pnlEast_2, BoxLayout.Y_AXIS));
		pnlGames = new ArrayList<>(); // 각 조에 1줄씩
		lblTypes = new ArrayList<>(); // 유저가 선택한 입력 방식
		btnSNResets = new ArrayList<>(); // 각 조에 수정버튼 
		btnSNDeletes = new ArrayList<>(); // 각 조에 삭제버튼
		lblSelectedNum = new JLabel[5][6]; // 유저가 선택한 번호들을 넣을 라벨
		for (int i = 0; i < 5; i++) {
			// 각 게임당 하나의 패널 선언 및 설정
			pnlGames.add(i, new JPanel());
			pnlGames.get(i).setLayout(new FlowLayout(FlowLayout.CENTER));			
			pnlGames.get(i).setBackground(Color.WHITE);
			pnlGames.get(i).setBorder(new LineBorder(Color.black, 1, true));
			pnlGames.get(i).addMouseListener(this);
			// 게임의 조(A, B, C, D, E)를 나타낼 라벨
			char ch = (char) (i + 65);
			JLabel lblRoundT = new JLabel(String.valueOf(ch));
			// 게임의 타입을 나타내줄 라벨
			lblTypes.add(i, new JLabel("미지정"));
			lblTypes.get(i).setPreferredSize(new Dimension(42, 14));
			// 각 줄의 선택한 번호들 이미지 표시할 라벨
			for (int j = 0; j < 6 ; j++) {
				lblSelectedNum[i][j] = new JLabel(new Methods().convertToIcon("balls/ballNull.png", 30, 30));
			}
			// 수정, 삭제 버튼
			btnSNResets.add(i, new RoundedButton("수정"));
			btnSNResets.get(i).setBackground(new Color(220, 220, 220));
			btnSNResets.get(i).addActionListener(this);
			btnSNDeletes.add(i, new RoundedButton("삭제"));
			btnSNResets.get(i).setBackground(new Color(220, 220, 220));
			btnSNDeletes.get(i).addActionListener(this);
			// 각 줄에 컴포넌트 삽입
			pnlGames.get(i).add(lblRoundT);
			pnlGames.get(i).add(lblTypes.get(i));
			pnlGames.get(i).add(Box.createHorizontalStrut(5));
			for (int j = 0; j < 6; j++) {
				pnlGames.get(i).add(lblSelectedNum[i][j]);
			}
			pnlGames.get(i).add(Box.createHorizontalStrut(5));
			pnlGames.get(i).add(btnSNResets.get(i));
			pnlGames.get(i).add(btnSNDeletes.get(i));
			pnlEast_2.add(pnlGames.get(i));
		}
		nowGameCounters = new int[5]; // 현재 작성중인 조의 선택된 숫자를 세기 위한 변수
		for (int i = 0; i < 5; i++) {
			nowGameCounters[i] = 0;
		}
		
		
		// pnlEast에 들어갈 3번 - 금액과 구매버튼 패널
		JPanel pnlEast_3 = new JPanel();
		pnlEast_3.setLayout(new FlowLayout());
		JLabel lblNumOfSheetsT = new JLabel("구매수: ");
		lblNumOfSheets = new JLabel("0");
		JLabel lblNumOfSheetsTT = new JLabel("장");
		JLabel lblNumOfGamesT = new JLabel("구매게임: ");
		lblNumOfGames = new JLabel("0");
		JLabel lblNumOfGamesTT = new JLabel("개");
		JLabel lblPriceT = new JLabel("결제 금액: ");
		lblPrice = new JLabel("0");
		JLabel lblPriceTT = new JLabel("원");
		btnPurchase = new RoundedButton("구매하기");
		btnPurchase.setBackground(new Color(220, 220, 220));
		btnPurchase.addActionListener(this);
		btnFinish = new RoundedButton("구매종료");
		btnFinish.setBackground(new Color(220, 220, 220));
		btnFinish.addActionListener(this);
		pnlEast_3.add(lblNumOfSheetsT);		
		pnlEast_3.add(lblNumOfSheets);
		pnlEast_3.add(lblNumOfSheetsTT);
		pnlEast_3.add(Box.createHorizontalStrut(10));
		pnlEast_3.add(lblNumOfGamesT);
		pnlEast_3.add(lblNumOfGames);
		pnlEast_3.add(lblNumOfGamesTT);
		pnlEast_3.add(Box.createHorizontalStrut(10));
		pnlEast_3.add(lblPriceT);
		pnlEast_3.add(lblPrice);
		pnlEast_3.add(lblPriceTT);
		pnlEast_3.add(Box.createHorizontalStrut(230));
		pnlEast_3.add(btnPurchase);
		pnlEast_3.add(btnFinish);
		
		pnlEast.add(pnlEast_1);
		pnlEast.add(pnlEast_2);
		pnlEast.add(pnlEast_3);
		
		pnl.add(pnlWest);
		pnl.add(pnlEast);
		
		add(pnl);
		
		setModal(true); // 부모 창이랑 상호작용 못하게 막음
		setSize(720, 500);
		setTitle("로또구매 - 인생역전");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object command = e.getSource();
		int num = pnlGames.indexOf(command);
			
		// 마우스가 들어오면 해당 패널 색상 바꿔서 보여줌
		if (pnlGames.contains(command) && !resetING[num]) {
			pnlGames.get(num).setBackground(new Color(180, 180, 180));
		}
		
		// 현재 선택완료된 게임이 몇개인지 확인. numOk[i]가 true라면 (i+1) 번째 게임은 선택완료된 것
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (numOk[i]) {
				count++;
			}
		}
		// 1 ~ 45의 번호에 마우스가 가면 손가락모양으로 변경. 5게임 모두 완료시 기본마우스모양.
		if (lblNumbers.contains(command) && count < 5) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
			int index = lblNumbers.indexOf(command);
			lblNumbers.get(index).setCursor(cursor);
		} else if (lblNumbers.contains(command) && count >= 5) {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
			int index = lblNumbers.indexOf(command);
			lblNumbers.get(index).setCursor(cursor);			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object command = e.getSource();
		int num = pnlGames.indexOf(command);
		// 마우스가 들어오면 해당 패널 색상 바꿔서 보여줌(다시 되돌림)
		if (pnlGames.contains(command) && !resetING[num]) {
			pnlGames.get(num).setBackground(Color.white);
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object command = e.getSource();
		int resettingGame = 0; // 수정중인 게임
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65; // 현재 선택한 게임
		
		// 수정중인 게임 인덱스 가져오기
		for (int i = 0; i < 5; i++) {
			if (resetING[i]) {
				resettingGame = i;
				break;
			}
		}
		
		if (resetING[resettingGame] && !lblNumbers.contains(command)) {
			if (inputNumbers.size() != 6 || inputNumbers.size() == 0) {
				inputNumbers.clear();
				for (int i = 0; i < 6; i++) {
					inputNumbers.add(tempInputNumbers[i]);
				}
			}
			String type = lblTypes.get(resettingGame).getText();
			nowGameCounters[resettingGame] = 6;
			changeBallIcon(resettingGame, inputNumbers, "등록", type);	
			resetING[resettingGame] = false;
			pnlGames.get(resettingGame).setBackground(Color.white);	
			cbQuantity.setSelectedIndex(0);
			clearSelectedNumber(resettingGame);	
			searchPossible(resettingGame);
			nowGameCounters[resettingGame] = 6;
		}
		
		// 아직 완료되지 않은 조를 클릭하면 그 조에 입력할 수 있도록 변경
		if (pnlGames.contains(command)) {
			int index = pnlGames.indexOf(command);
			char round = (char) (index + 65);
			if (!numOk[index]) {
				clearSelectedNumber(index);
				lblNowGame.setText(String.valueOf(round));
			}
		}
		
		// 번호를 클릭했을때, 해당 조가 완료되지 않았을때, 수량선택 자동번호가 체크되지 않았을때
		if (lblNumbers.contains(command) && !numOk[nowGame] && !ckAuto.isSelected()) {
			int index = lblNumbers.indexOf(command);
			String name = "num" + (index + 1) + ".png";
			if (checkNumber[index] && nowGameCounters[nowGame] <= 6) { // 선택한 번호가 이미 선택되었을 때 지우기
				lblNumbers.get(index).setIcon(getIcon("numbers/" + name, 30, 30)); 
				checkNumber[index] = false;
				nowGameCounters[nowGame]--;
				System.out.println(index);
				System.out.println(inputNumbers);
				System.out.println(inputNumbers.indexOf(index + 1));
				inputNumbers.remove(inputNumbers.indexOf(index + 1));
			} else if (!checkNumber[index] && nowGameCounters[nowGame] < 6) { // 번호 선택하기
				lblNumbers.get(index).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				checkNumber[index] = true;
				nowGameCounters[nowGame]++;
				inputNumbers.add(index + 1);
			} else if (!checkNumber[index] && nowGameCounters[nowGame] >= 6 && inputNumbers.contains(100)) { // 자동번호가 포함되어있는 게임 수정할때
				int deleteNum = inputNumbers.indexOf(100); 
				inputNumbers.remove(deleteNum); // 랜덤번호 1개 삭제
				lblNumbers.get(index).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				checkNumber[index] = true;
				inputNumbers.add(index + 1); // 새로 선택한 번호 추가
			} else if (!checkNumber[index] && nowGameCounters[nowGame] >= 6) {
				JOptionPane.showMessageDialog(null, "최대 6개의 숫자를 선택할 수 있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}		
		lblNumOfGames.setText(String.valueOf(numOfGames));
		lblPrice.setText(String.valueOf(numOfGames * 1000));
		// 선택수량 변경
		if (numOfGame < 5) {
			quantityLists = new String[5 - numOfGame];
			cbQuantity.removeAllItems();
			for (int i = 0; i < quantityLists.length; i++) {
				quantityLists[i] = String.valueOf(i + 1);
				cbQuantity.addItem(quantityLists[i]);
			}
		} else {
			cbQuantity.removeAllItems();
			cbQuantity.addItem(0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		int resettingGame = 0; // 수정중인 게임
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65; // 현재 선택한 게임
		int selectedQuantity = cbQuantity.getSelectedIndex() + 1; // 선택된 수량
		int games = numOfGame + selectedQuantity; // 이미 완료된 게임수와 선택된 수량
		
		// 수정중인 게임 인덱스 가져오기
		for (int i = 0; i < 5; i++) {
			if (resetING[i]) {
				resettingGame = i;
				break;
			}
		}
		
		if (command == btnReset && !resetING[resettingGame]) {
			clearSelectedNumber(nowGame);
		}
		
		// 수정중인 게임과 현재 게임이 다르다면 or 확인버튼, 초기화버튼이 아니라면 수정중인게임 그대로 나두기
		if (resetING[resettingGame] && command != btnOk && !btnSNResets.contains(command)) {
			if (inputNumbers.size() != 6 || inputNumbers.size() == 0) {
				inputNumbers.clear();
				for (int i = 0; i < 6; i++) {
					inputNumbers.add(tempInputNumbers[i]);
				}
			}
			String type = lblTypes.get(resettingGame).getText();
			nowGameCounters[resettingGame] = 6;
			changeBallIcon(resettingGame, inputNumbers, "등록", type);	
			resetING[resettingGame] = false;
			pnlGames.get(resettingGame).setBackground(Color.white);	
			cbQuantity.setSelectedIndex(0);
			clearSelectedNumber(resettingGame);	
			searchPossible(resettingGame);
			nowGameCounters[resettingGame] = 6;
		}
	
		if (command == btnOk && games > 5) { // 등록된 게임이 현재 장에서 5개 이상이면 메세지 출력
			JOptionPane.showMessageDialog(null, "1장당 최대 5개의 게임만 구매가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		} else if (command == btnOk && (ckAuto.isSelected() || btnAuto.isSelected())) { // 자동관련 설정이 되어있을때
			randomNumbers(nowGame, inputNumbers);
			inputNumbers(selectedQuantity, nowGame);
		} else if (command == btnOk && nowGameCounters[nowGame] == 6) { // 6개의 번호가 모두 선택되었을때
			inputNumbers(selectedQuantity, nowGame);
		}  else if (command == btnOk && nowGameCounters[nowGame] < 6) { // 자동없이 선택된 번호가 6개 미만일때
			JOptionPane.showMessageDialog(null, "6개의 번호를 선택해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
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
		
		if (btnSNResets.contains(command)) {
			int index = btnSNResets.indexOf(command);
			if (nowGameCounters[index] == 6 && !resetING[index]) {
				char temp = (char) (index + 65);
				lblNowGame.setText(String.valueOf(temp));
				lblNowGame.setVisible(true);
				lblFinish.setVisible(false);
				nowGame = (int) lblNowGame.getText().charAt(0) - 65;
				if (inputRounds.get(nowGame) != null) {
					pnlGames.get(nowGame).setBackground(new Color(220, 180, 180));
					resetING[nowGame] = true;
					showSelectedNumber(nowGame);
					numOfGame--;
					numOfGames--;
					ckAuto.setSelected(false);
					pnlWest_3.setVisible(true);
					pnlWest3_Allauto.setVisible(false);		
					btnReset.setEnabled(true);
					btnAuto.setEnabled(true);					
				}
			}
		}
		if (btnSNDeletes.contains(command)) {
			int index = btnSNDeletes.indexOf(command);
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
						int num = random.nextInt(45) + 1;
						tempSet.add(num);
						inputRounds.get(numI).clear();
						inputRounds.get(numI).addAll(tempSet);
						Collections.sort(inputRounds.get(numI));	
					}					
				}
			}
			Map<Integer, List<Integer>>[] tempSheets = new TreeMap[100];
			Map<Integer, String>[] tempTypes = new TreeMap[100];
			tempSheets[numOfSheet] = new TreeMap<>();
			tempSheets[numOfSheet].putAll(inputRounds);
			sheets.put(numOfSheet, tempSheets[numOfSheet]);
			tempTypes[numOfSheet] = new TreeMap<>();
			tempTypes[numOfSheet].putAll(roundTypes);
			sheetTypes.put(numOfSheet, tempTypes[numOfSheet]);
			System.out.println(sheetTypes);
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
			lblNumOfSheets.setText(String.valueOf(numOfSheet));
			lblNowGame.setVisible(true);
			lblFinish.setVisible(false);			
			lblNowGame.setText("A");
			lblNumOfGames.setText(String.valueOf(numOfGames));
			System.out.println(sheets);
		} else if (command == btnPurchase && numOfGame == 0) {
			JOptionPane.showMessageDialog(null, "1게임 이상 등록되어야 구매가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}
		lblNumOfGames.setText(String.valueOf(numOfGames));
		lblPrice.setText(String.valueOf(numOfGames * 1000));		
		if (command == btnFinish) {
			
		}
		if (command == btnManual) {
			new Manual().showGUI();
		}
		if (command == btnOk) {
			for (int i = 0; i < 5; i++) {
				pnlGames.get(i).setBackground(Color.white);
			}
		}
		
		// 선택수량 변경
		if (numOfGame < 5) {
			quantityLists = new String[5 - numOfGame];
			cbQuantity.removeAllItems();
			for (int i = 0; i < quantityLists.length; i++) {
				quantityLists[i] = String.valueOf(i + 1);
				cbQuantity.addItem(quantityLists[i]);
			}
		} else {
			cbQuantity.removeAllItems();
			cbQuantity.addItem(0);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int nowGame = (int) lblNowGame.getText().charAt(0) - 65;
		if (ckAuto.isSelected()) {	
			clearSelectedNumber(nowGame);
			pnlWest_3.setVisible(false);
			pnlWest3_Allauto.setVisible(true);
			btnReset.setEnabled(false);
			btnAuto.setEnabled(false);
		}
		if (!ckAuto.isSelected()) {
			pnlWest_3.setVisible(true);
			pnlWest3_Allauto.setVisible(false);		
			btnReset.setEnabled(true);
			btnAuto.setEnabled(true);			
		}
	}
	
	public void inputNumbers(int selectedQuantity, int nowGame) {
		int count = 0;
		String type;
		for (int i = 0; i < 6; i++) {
			if (inputNumbers.get(i) == 100) {
				count++;
			}
		}
		if (count >= 6) {
			changeBallIcon(nowGame, inputNumbers, "등록", "자동");
			type = "자동";
		} else if (count > 0) {
			changeBallIcon(nowGame, inputNumbers, "등록", "반자동");
			type = "반자동";
		} else {
			changeBallIcon(nowGame, inputNumbers, "등록", "수동");
			type = "수동";
		}
		selectedQuantity--;
		List<Integer> possible = searchPossible(nowGame);
		for (int i = 0; i < selectedQuantity; i++) {
			nowGame = possible.get(i);
			nowGameCounters[nowGame] = 6;
			changeBallIcon(nowGame, inputNumbers, "등록", type);
		}
		inputNumbers.clear();
		cbQuantity.setSelectedIndex(0);
		clearSelectedNumber(nowGame);
		nowGameCounters[nowGame] = 6;
		searchPossible(nowGame);			
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
				checkNumber[num] = true;
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
		if (function.equals("등록") && nowGameCounters[nowGame] != 0) {
			List<Integer>[] numList = new ArrayList[5];
			for (int i = 0; i < 5; i ++) {
				numList[i] = new ArrayList(6);
			}
			numList[nowGame].addAll(inputNumbers);
			inputRounds.put(nowGame, numList[nowGame]);
			roundTypes.put(nowGame, type);
			for (int i = 0; i < 6; i++) {
				int number = inputRounds.get(nowGame).get(i);
				String name = "ball" + number + ".png";
				if (number == 100) {
					name = "ball100.png";
				}
				lblSelectedNum[nowGame][i].setIcon(getIcon("balls/" + name, 30, 30));
			}
			lblTypes.get(nowGame).setText(type);
			numOk[nowGame] = true;
			numOfGame++;
			numOfGames++;
		}
		if (function.equals("삭제")) {
			for (int i = 0; i < 6; i++) {
				String name = "ballNull.png";
				lblSelectedNum[nowGame][i].setIcon(getIcon("balls/" + name, 30, 30));
			}
			lblTypes.get(nowGame).setText("미지정");
			inputRounds.remove(nowGame);
			roundTypes.remove(nowGame);
			numOk[nowGame] = false;
			numOfGame--;
			numOfGames--;
		}
	}
	
	public void clearSelectedNumber(int nowGame) {
		for (int i = 0; i < 45; i++) {
			String name = "num" + (i + 1) + ".png";
			lblNumbers.get(i).setIcon(getIcon("numbers/" + name, 30, 30));
			checkNumber[i] = false;
			nowGameCounters[nowGame] = 0;
			inputNumbers.clear();
		}
	}
	public void showSelectedNumber(int nowGame) {
		inputNumbers = inputRounds.get(nowGame);
		tempInputNumbers = new int[inputNumbers.size()];
		for (int i = 0; i < 6; i++) { 
			int num = inputNumbers.get(i) - 1;
			tempInputNumbers[i] = inputNumbers.get(i);
			String name = "num" + (num + 1) + ".png";
			if (num != 99) {
				lblNumbers.get(num).setIcon(getIcon("afterNumbers/" + name, 30, 30));
				checkNumber[num] = true;
			}
			nowGameCounters[nowGame] = 6;
			numOk[nowGame] = false;
		}		
		System.out.println(inputNumbers);
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
		new Purchase(1).showGUI();
	}
}