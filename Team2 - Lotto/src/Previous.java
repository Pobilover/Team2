import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @author GGG
 *
 */
class Previous extends JDialog implements ActionListener {
	private List<Integer> winPrice;
	private Methods methods = new Methods();
	private JLabel lblRound;
	private JComboBox cbList;
	private JButton btnSearch;
	private JLabel[] lblWinNums;
	private JLabel lblBonusNum;
	private JPanel pnlC1;
	private JLabel lblWinPrice;
	private JLabel lblWinner;
	private List<Integer> winPerson = new ArrayList<>();
	private RoundedButton btnPre;
	private RoundedButton btnNext;
	private JPanel pnlN1;
	private JPanel pnlNorth;	
	private int gameRound;
	private Map<Integer, List<Integer>> winNums;
	private int userPrice = 0;
	private DecimalFormat formatter = new DecimalFormat("###,###");
	private Map<Integer, Integer> rank = new TreeMap<>();
	private int getPrice = 0;
	private List<Integer> sNumber;
	private JButton btnStart;
	private JLabel lblSRank2;
	private JLabel lblSRank3;
	private JLabel lblSRank4;
	private JLabel lblSRank5;
	private JLabel lblSRank0;
	private JLabel lblSRank1;
	private JPanel pnlS2_2;
	private JPanel pnlS2;
	private JLabel lblMostNumbers;
	
	public int getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(int userPrice) {
		this.userPrice = userPrice;
	}

	public List<Integer> getWinPrice() {
		return winPrice;
	}

	public void setWinPrice(List<Integer> winPrice) {
		this.winPrice = winPrice;
	}
	
	
	public Previous(int gameRound, Map<Integer, List<Integer>> winNums, List<Integer> winPrice, int userPrice, Map<Integer, Integer> rank, int getPrice, List<Integer> sNumber) {
		this.gameRound = gameRound;
		this.winNums = winNums;
		this.winPrice = winPrice;
		this.userPrice = userPrice;
		this.rank = rank;
		this.getPrice = getPrice;
		this.sNumber = sNumber;
		
		// 임시적인 값 생성
		Random r = new Random();
		for (int i = 0; i < winPrice.size(); i++) {
			winPerson.add(r.nextInt(10) + 1);
		}
		
		// 주요패널 선언
		ImagePanel pnl = new ImagePanel(new Methods().convertToIcon("미국돈.png", 700, 460).getImage());
		pnlNorth = new JPanel();
		JPanel pnlWest = new JPanel();
		JPanel pnlCenter = new JPanel();
		JPanel pnlEast = new JPanel();
		JPanel pnlSouth = new JPanel();
		
		pnlNorth.setOpaque(false);
		pnlWest.setOpaque(false);
		pnlCenter.setOpaque(false);
		pnlEast.setOpaque(false);
		pnlSouth.setOpaque(false);

		// 주요패널 레이아웃 선택
		pnl.setLayout(new BorderLayout());
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		lblRound = new JLabel(String.valueOf(gameRound));
		lblRound.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		lblRound.setForeground(Color.black);
		lblRound.setOpaque(false);
		JLabel lblRoundT = new JLabel("회");
		lblRoundT.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		lblRoundT.setForeground(Color.black);
		JLabel lblResultT = new JLabel("당첨결과");
		lblResultT.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		JLabel lblSelectT = new JLabel("회차 바로가기");
		lblSelectT.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		String round[] = new String[winNums.size()];
		for (int i = 0; i < winNums.size(); i++) {
			round[i] = String.valueOf(i+1);
		}
		
		cbList = new JComboBox(round);
		cbList.setSelectedIndex(round.length - 1);
		cbList.setPreferredSize(new Dimension(100, 25));
		btnSearch = new RoundedButton("조회");
		btnSearch.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		btnSearch.addActionListener(this);
		
		pnlN1 = new JPanel();
		JPanel pnlN2 = new JPanel();
		pnlN1.setBackground(new Color(255, 255, 255, 150));
		pnlN2.setOpaque(false);
		
		// pnlN1에 component 추가
		pnlNorth.add(Box.createVerticalStrut(20));
		pnlN1.add(lblRound);
		pnlN1.add(lblRoundT);
		pnlN1.add(lblResultT);
		// pnlN2에 component 추가
		pnlN2.add(Box.createHorizontalStrut(400));
		pnlN2.add(lblSelectT);
		pnlN2.add(cbList);
		pnlN2.add(btnSearch);
		// pnlNorth에 각 패널 추가
		pnlNorth.add(pnlN1);
		pnlNorth.add(pnlN2);
		pnlNorth.add(Box.createVerticalStrut(10));
		
		btnPre = new RoundedButton("이전회");
		btnPre.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		btnPre.addActionListener(this);
		// pnlWest에 component 추가
		pnlWest.add(Box.createVerticalStrut(80));
		pnlWest.add(btnPre);		
		
		btnNext = new RoundedButton("다음회");	
		btnNext.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		btnNext.addActionListener(this);
		// pnlEast에 component 추가
		pnlEast.add(Box.createVerticalStrut(80));
		pnlEast.add(btnNext);
		pnlEast.add(Box.createHorizontalStrut(10));
		
		lblWinNums = new JLabel[7];
		for (int i = 0; i < lblWinNums.length; i++) {
			ImageIcon icon = methods.convertToIcon("balls/ballNull.png", 40, 40);
			lblWinNums[i] = new JLabel(icon);
			lblWinNums[i].setOpaque(false);
		}			
		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("바탕", Font.BOLD, 25));
		lblBonusNum = new JLabel();
		lblBonusNum = new JLabel(methods.convertToIcon("balls/ballNull.png", 40, 40));			
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		
		pnlC1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnlC1.setOpaque(false);
		pnlC1.setPreferredSize(new Dimension(0, 50));		
		JPanel pnlC2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 180, 10));
		pnlC2.setOpaque(false);
		
		// pnlC1에 component 추가
//		pnlC1.add(Box.createVerticalStrut(120));
		for (int i = 0; i < lblWinNums.length - 1; i++) {
			pnlC1.add(lblWinNums[i]);			
		}
		pnlC1.add(lblPlus);
		pnlC1.add(lblBonusNum);
		pnlC1.setPreferredSize(new Dimension(0, 60));
		// pnlC2에 component 추가
		pnlC2.add(lblWinNumT);
		pnlC2.add(lblBonusNumT);
		pnlC2.setPreferredSize(new Dimension(0, 20));
		// pnlCenter에 각 패널 추가
		pnlCenter.add(pnlC1);
		pnlCenter.add(pnlC2);
		// pnlCenter에 border 추가
		pnlCenter.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		
		// pnlS1에 들어갈 component
		JLabel lblWinPriceT = new JLabel("1등 총상금 : ");
		lblWinPriceT.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblWinPrice = new JLabel("0");
		lblWinPrice.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		JLabel lblWinPriceT2 = new JLabel("원");
		lblWinPriceT2.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		
		// pnlS2에 들어갈 component
		JPanel pnlS2_1 = new JPanel();
		pnlS2_1.setBackground(new Color(180, 180, 180, 150));
		pnlS2_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\uCD1D \uAD6C\uB9E4\uD604\uD669", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlS2_1.setPreferredSize(new Dimension(300, 0));
		JLabel lblUserPriceT = new JLabel("투자금액 : ");
		lblUserPriceT.setBounds(19, 43, 76, 15);
		JLabel lblUserPrice = new JLabel(formatter.format(userPrice));
		lblUserPrice.setBounds(83, 43, 150, 15);
		lblUserPrice.setHorizontalAlignment(JLabel.RIGHT);
		JLabel lblGetPriceT = new JLabel("당첨금 : ");
		lblGetPriceT.setBounds(19, 21, 76, 15);
		JLabel lblGetPrice = new JLabel(formatter.format(getPrice));
		lblGetPrice.setBounds(83, 21, 150, 15);
		lblGetPrice.setHorizontalAlignment(JLabel.RIGHT);
		JLabel lblProfitT = new JLabel("손익 : ");
		lblProfitT.setBounds(19, 65, 76, 15);
		JLabel lblProfit = new JLabel(formatter.format(getPrice - userPrice));
		lblProfit.setHorizontalAlignment(JLabel.RIGHT);
		lblProfit.setBounds(83, 65, 150, 15);
		JLabel lblRank1T = new JLabel("1등 : ");
		lblRank1T.setBounds(83, 90, 40, 15);
		JLabel lblRank1 = new JLabel(rank.get(1) + "번");		
		lblRank1.setBounds(140, 90, 82, 15);
		JLabel lblRank2T = new JLabel("2등 : ");
		lblRank2T.setBounds(83, 110, 40, 15);
		JLabel lblRank2 = new JLabel(rank.get(2) + "번");		
		lblRank2.setBounds(140, 110, 82, 15);
		JLabel lblRank3T = new JLabel("3등 : ");
		lblRank3T.setBounds(83, 130, 40, 15);
		JLabel lblRank3 = new JLabel(rank.get(3) + "번");		
		lblRank3.setBounds(140, 130, 82, 15);
		JLabel lblRank4T = new JLabel("4등 : ");
		lblRank4T.setBounds(83, 150, 40, 15);
		JLabel lblRank4 = new JLabel(rank.get(4) + "번");		
		lblRank4.setBounds(140, 150, 82, 15);
		JLabel lblRank5T = new JLabel("5등 : ");
		lblRank5T.setBounds(83, 170, 40, 15);
		JLabel lblRank5 = new JLabel(rank.get(5) + "번");		
		lblRank5.setBounds(140, 170, 82, 15);
		JLabel lblRank0 = new JLabel(rank.get(0) + "번");		
		lblRank0.setBounds(140, 190, 82, 15);
		pnlS2_1.setLayout(null);
		pnlS2_1.add(lblUserPriceT);
		pnlS2_1.add(lblUserPrice);
		pnlS2_1.add(lblGetPriceT);
		pnlS2_1.add(lblGetPrice);
		pnlS2_1.add(lblProfitT);
		pnlS2_1.add(lblProfit);
		pnlS2_1.add(lblRank1T);
		pnlS2_1.add(lblRank1);
		pnlS2_1.add(lblRank2T);
		pnlS2_1.add(lblRank2);
		pnlS2_1.add(lblRank3T);
		pnlS2_1.add(lblRank3);
		pnlS2_1.add(lblRank4T);
		pnlS2_1.add(lblRank4);
		pnlS2_1.add(lblRank5T);
		pnlS2_1.add(lblRank5);
		pnlS2_1.add(lblRank0);
		
		
		pnlS2_2 = new JPanel();
		pnlS2_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\uC2DC\uBBAC\uB808\uC774\uD130", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnlS2_2.setBackground(new Color(180, 180, 180, 150));
		
		
		// pnlSouth에 들어갈 패널
		JPanel pnlS1 = new JPanel(new FlowLayout());
		pnlS1.setOpaque(false);
		pnlS2 = new JPanel(new BorderLayout());
		pnlS2.setOpaque(false);
		
		// pnlS1에 component 추가
		pnlS1.add(lblWinPriceT);
		pnlS1.add(lblWinPrice);
		pnlS1.add(lblWinPriceT2);
		// pnlS2에 component 추가
		pnlS2.add(pnlS2_1, "West");
		JLabel lblRank0T = new JLabel("낙첨 : ");
		lblRank0T.setBounds(83, 190, 50, 15);
		pnlS2_1.add(lblRank0T);
		
		JLabel lblNewLabel = new JLabel("원");
		lblNewLabel.setBounds(245, 21, 22, 15);
		pnlS2_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("원");
		lblNewLabel_1.setBounds(245, 65, 22, 15);
		pnlS2_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("원");
		lblNewLabel_1_1.setBounds(245, 43, 22, 15);
		pnlS2_1.add(lblNewLabel_1_1);
		pnlS2.add(pnlS2_2, "Center");
		pnlS2_2.setLayout(null);
		
		JPanel pnlS2_2Numbers = new JPanel();
		pnlS2_2Numbers.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\uAD6C\uB9E4\uD55C \uBC88\uD638 \uC911 \uAC00\uC7A5 \uC55E\uC5D0\uC788\uB294 \uBC88\uD638\uAE30\uC900", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		pnlS2_2Numbers.setBackground(new Color(0, 0, 0, 0));
		pnlS2_2Numbers.setBounds(12, 15, 360, 65);
		pnlS2_2.add(pnlS2_2Numbers);
		JLabel num[] = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			String name = "normal/ball" + sNumber.get(i) + ".png";
 			num[i] = new JLabel(convertToIcon(name, 30, 30));
 			pnlS2_2Numbers.add(num[i]);
 			num[i].setBounds(20 + (35 * i), 18, 30, 30);
		}

		
		JLabel lblNewLabel_2 = new JLabel("1등 당첨까지 시행횟수 : ");
		lblNewLabel_2.setBounds(22, 90, 153, 15);
		pnlS2_2.add(lblNewLabel_2);
		
		lblSRank1 = new JLabel("0회");
		lblSRank1.setBounds(177, 90, 205, 15);
		pnlS2_2.add(lblSRank1);
		
		JLabel lblNewLabel_4 = new JLabel("가장 많이나온 6개 숫자 : ");
		lblNewLabel_4.setBounds(22, 115, 160, 15);
		pnlS2_2.add(lblNewLabel_4);
		
		lblMostNumbers = new JLabel("0, 0, 0, 0, 0, 0");
		lblMostNumbers.setBounds(187, 115, 195, 15);
		pnlS2_2.add(lblMostNumbers);
		
		btnStart = new JButton("시작");
		btnStart.addActionListener(this);
		btnStart.setBounds(275, 182, 97, 23);
		pnlS2_2.add(btnStart);
		
		JLabel lblNewLabel_6 = new JLabel("2등 : ");
		lblNewLabel_6.setBounds(22, 140, 40, 15);
		pnlS2_2.add(lblNewLabel_6);
		
		lblSRank2 = new JLabel("0번");
		lblSRank2.setBounds(55, 140, 80, 15);
		pnlS2_2.add(lblSRank2);
		
		JLabel lblNewLabel_6_1 = new JLabel("3등 : ");
		lblNewLabel_6_1.setBounds(137, 140, 40, 15);
		pnlS2_2.add(lblNewLabel_6_1);
		
		lblSRank3 = new JLabel("0번");
		lblSRank3.setBounds(170, 140, 80, 15);
		pnlS2_2.add(lblSRank3);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("4등 : ");
		lblNewLabel_6_1_1.setBounds(257, 140, 40, 15);
		pnlS2_2.add(lblNewLabel_6_1_1);
		
		lblSRank4 = new JLabel("0번");
		lblSRank4.setBounds(290, 140, 97, 15);
		pnlS2_2.add(lblSRank4);
		
		JLabel lblNewLabel_6_1_1_1 = new JLabel("5등 : ");
		lblNewLabel_6_1_1_1.setBounds(22, 165, 40, 15);
		pnlS2_2.add(lblNewLabel_6_1_1_1);
		
		lblSRank5 = new JLabel("0번");
		lblSRank5.setBounds(55, 165, 120, 15);
		pnlS2_2.add(lblSRank5);
		
		JLabel lblNewLabel_6_1_1_1_1 = new JLabel("낙첨 : ");
		lblNewLabel_6_1_1_1_1.setBounds(186, 165, 50, 15);
		pnlS2_2.add(lblNewLabel_6_1_1_1_1);
		
		lblSRank0 = new JLabel("0번");
		lblSRank0.setBounds(235, 165, 147, 15);
		pnlS2_2.add(lblSRank0);
		
		JLabel lblNewLabel_3 = new JLabel("시행횟수가 많을수록 시간이 소요됩니다.");
		lblNewLabel_3.setBounds(32, 190, 235, 15);
		pnlS2_2.add(lblNewLabel_3);
		

		// pnlSouth에 각 패널 추가
		pnlSouth.add(pnlS1);
		pnlSouth.add(pnlS2);
		
		pnl.add(pnlNorth, "North");
		pnl.add(pnlWest, "West");
		pnlWest.setPreferredSize(new Dimension(100, 0));
		pnl.add(pnlCenter, "Center");
		pnlCenter.setPreferredSize(new Dimension(0, 150));
		pnl.add(pnlEast, "East");
		pnl.add(pnlSouth, "South");
		pnlSouth.setPreferredSize(new Dimension(0, 250));
		
		getContentPane().add(pnl);
		
		searchRound();
		
		setSize(700, 500);
		setTitle("이전회차 조회");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void showGUI() {
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		if (command == btnSearch) {
			searchRound();
		}
		if (command == btnPre) {
			int index = cbList.getSelectedIndex();
			if (index - 1 >= 0) {
				int round = Integer.parseInt(lblRound.getText());
				round--;
				lblRound.setText(String.valueOf(round));
				cbList.setSelectedIndex(index - 1);
				searchRound();
				pnlNorth.revalidate();
				pnlNorth.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "가장 처음장 입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (command == btnNext) {
			int index = cbList.getSelectedIndex();
			if (index + 1 < winNums.size()) { 
				int round = Integer.parseInt(lblRound.getText());
				round++;
				lblRound.setText(String.valueOf(round));				
				cbList.setSelectedIndex(index + 1);
				searchRound();
				pnlNorth.revalidate();
				pnlNorth.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "마지막장 입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (command == btnStart) {
			btnStart.setText("계산중");
			btnStart.setEnabled(false);
			Random random = new Random();
			boolean go = false;
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			int count5 = 0;
			int count0 = 0;
			int bonus;
			int[] countNum = new int[45];
			for (int i = 0; i < 45; i++) {
				countNum[i] = 0;
			}
			Map<Integer, Integer> mostNum = new TreeMap<>();
			while (!go) { 
				Set<Integer> rNum = new TreeSet<>();
				List<Integer> copySNumber = new ArrayList<>(sNumber);
				for (int i = 0; i < 45; i++) {
					mostNum.put(i + 1, 0);
				}
				while (rNum.size() < 7) {
					int num = random.nextInt(45) + 1;
					rNum.add(num);
				}
				do {
					bonus = random.nextInt(45) + 1;
				} while (rNum.contains(bonus));
				for (int i = 0; i < 45; i++) {
					if (rNum.contains(i + 1)) {
						countNum[i]++;
					}
				}
				copySNumber.retainAll(rNum);
				if (copySNumber.size() >= 6) {
					count1 ++;
					go = true;
				} else if (copySNumber.size() == 5) {
					if (sNumber.contains(bonus)) {
						count2++;
					} else {
						count3++;
					}
				} else if (copySNumber.size() == 4) {
					count4++;
				} else if (copySNumber.size() == 3) {
					count5++;
				} else {
					count0++;
				}
				count1++;
			}
			for (int i = 0; i < 45; i++) {
				mostNum.put(i + 1, countNum[i]);
			}
			
			List<Integer> keySet = new ArrayList<>(mostNum.keySet());
			keySet.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return mostNum.get(o2).compareTo(mostNum.get(o1));
				}
			});
			lblMostNumbers.setText(keySet.get(0) + ", " + keySet.get(1) + ", " + keySet.get(2) + ", " + keySet.get(3) + ", " + keySet.get(4) + ", " + keySet.get(5));
			lblSRank1.setText(formatter.format(count1) + "회");
			lblSRank2.setText(formatter.format(count2) + "회");
			lblSRank3.setText(formatter.format(count3) + "회");
			lblSRank4.setText(formatter.format(count4) + "회");
			lblSRank5.setText(formatter.format(count5) + "회");
			lblSRank0.setText(formatter.format(count0) + "회");
			pnlS2.revalidate();
			pnlS2.repaint();
			btnStart.setText("시작");
			btnStart.setEnabled(true);
		}
		
	}
	
	public void searchRound() {
		int round = cbList.getSelectedIndex() + 1;
		for (int i = 0; i < 7; i++) {
			int num = winNums.get(round).get(i);
			String name = "balls/ball" + num + ".png"; 
			lblWinNums[i].setIcon(convertToIcon(name, 40, 40));
			lblBonusNum.setIcon(convertToIcon(name, 40, 40));
		}
		int price = winPrice.get(round - 1);
		lblWinPrice.setText(formatter.format(price));		
	}
	
	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		int thisWidth = width;
		int thisHeight = height;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Image image = kit.getImage(classLoader.getResource(imageName));
			image = image.getScaledInstance(thisWidth, thisHeight, Image.SCALE_SMOOTH);
			// 이미지크기조절
			ImageIcon icon = new ImageIcon(image);
			return icon;
		} catch (NullPointerException e) {
			System.out.println(name + " 해당 이미지 파일을 찾을 수 없습니다.");
		}
		return null;
	 }
	
	public static void main(String[] args) {

	}
}
