import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

class Previous extends JDialog implements ActionListener {
	private Map<Integer, List<Integer>> winNumber = new TreeMap<>();
	private List<Integer> winPrice = new ArrayList<>();
	private Methods methods = new Methods();
	private JLabel lblRound;
	private JComboBox cbList;
	private JButton btnSearch;
	private JLabel[] lblWinNums;
	private JLabel lblBonusNum;
	private JPanel pnlC1;
	private JLabel lblWinPrice;
	private JLabel lblWinner;
	private List<Integer> winPerson;
	private JButton btnPre;
	private JButton btnNext;	
	
	public Map<Integer, List<Integer>> getWinNumber() {
		return winNumber;
	}

	public void setWinNumber(Map<Integer, List<Integer>> winNumber) {
		this.winNumber = winNumber;
	}

	public List<Integer> getWinPrice() {
		return winPrice;
	}

	public void setWinPrice(List<Integer> winPrice) {
		this.winPrice = winPrice;
	}
	
	public Previous(Map<Integer, List<Integer>> winNumber, List<Integer> winPrice) {
		this.winNumber = winNumber;
		this.winPrice = winPrice;
	}

	public Previous() {	
		// 임시적인 값 생성
		List<Integer> num1 = Arrays.asList(1, 2, 3, 4, 5, 6, 10);
		List<Integer> num2 = Arrays.asList(11, 12, 13, 14, 15, 16, 11);
		List<Integer> num3 = Arrays.asList(21, 22, 23, 24, 25, 26, 12);
		List<Integer> num4 = Arrays.asList(31, 32, 33, 34, 35, 36, 13);
		List<Integer> num5 = Arrays.asList(41, 42, 43, 44, 45, 6, 14);
		winNumber.put(1, num1);
		winNumber.put(2, num2);
		winNumber.put(3, num3);
		winNumber.put(4, num4);
		winNumber.put(5, num5);
		winPrice = Arrays.asList(100, 200, 300, 400, 500);
		winPerson = Arrays.asList(1, 2, 3, 4, 5);
		
		// 주요패널 선언
		MyImageBackgroundPanel pnl = new MyImageBackgroundPanel(methods.backgroud("배경.png"));
		JPanel pnlNorth = new JPanel();
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
		
		lblRound = new JLabel("1");
		lblRound.setFont(new Font("휴먼편지체", Font.BOLD, 35));
		lblRound.setForeground(Color.ORANGE);
		JLabel lblRoundT = new JLabel("회");
		lblRoundT.setFont(new Font("휴먼편지체", Font.BOLD, 35));
		lblRoundT.setForeground(Color.ORANGE);
		JLabel lblResultT = new JLabel("당첨결과");
		lblResultT.setFont(new Font("휴먼편지체", Font.BOLD, 25));
		JLabel lblSelectT = new JLabel("회차 바로가기");
		lblSelectT.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		String round[] = new String[winNumber.size()];
		for (int i = 0; i < winNumber.size(); i++) {
			round[i] = String.valueOf(i+1);
		}
		
		cbList = new JComboBox(round);
		cbList.setPreferredSize(new Dimension(100, 25));
		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		btnSearch.addActionListener(this);
		
		// pnlNorth에 들어갈 패널
		JPanel pnlN1 = new JPanel();
		JPanel pnlN2 = new JPanel();
		
		pnlN1.setOpaque(false);
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
		pnlNorth.add(Box.createVerticalStrut(40));
		
		btnPre = new JButton("이전회");
		btnPre.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		btnPre.addActionListener(this);
		// pnlWest에 component 추가
		pnlWest.add(Box.createVerticalStrut(80));
		pnlWest.add(btnPre);		
		
		btnNext = new JButton("다음회");	
		btnNext.setFont(new Font("휴먼편지체", Font.BOLD, 14));
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
		lblPlus.setFont(new Font("휴먼편지체", Font.BOLD, 25));
		lblBonusNum = new JLabel();
		lblBonusNum = new JLabel(methods.convertToIcon("balls/ballNull.png", 40, 40));			
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		
		pnlC1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnlC1.setOpaque(false);
		pnlC1.setPreferredSize(new Dimension(0, 50));		
		JPanel pnlC2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 183, 10));
		pnlC2.setOpaque(false);
		
		// pnlC1에 component 추가
//		pnlC1.add(Box.createVerticalStrut(120));
		for (int i = 0; i < lblWinNums.length - 1; i++) {
			pnlC1.add(lblWinNums[i]);			
		}
		pnlC1.add(lblPlus);
		pnlC1.add(lblBonusNum);
		// pnlC2에 component 추가
		pnlC2.add(lblWinNumT);
		pnlC2.add(lblBonusNumT);
		// pnlCenter에 각 패널 추가
		pnlCenter.add(pnlC1);
		pnlC2.setPreferredSize(new Dimension(0, 20));
		pnlCenter.add(pnlC2);
		pnlC2.setPreferredSize(new Dimension(0, 20));
		// pnlCenter에 border 추가
		pnlCenter.setBorder(new LineBorder(Color.BLACK, 2, true));
		
		// pnlSouth에 들어갈 component
		JLabel lblWinPriceT = new JLabel("1등 당첨금액 : ");
		lblWinPriceT.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		lblWinPrice = new JLabel("0");
		lblWinPrice.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		JLabel lblWinnerT = new JLabel("당첨자수 : ");
		lblWinnerT.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		lblWinner = new JLabel("0");
		lblWinner.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		
		// pnlSouth에 들어갈 패널
		JPanel pnlS1 = new JPanel();
		pnlS1.setOpaque(false);
		JPanel pnlS2 = new JPanel();
		pnlS2.setOpaque(false);
		
		// pnlS1에 component 추가
		pnlS1.add(lblWinPriceT);
		pnlS1.add(lblWinPrice);
		// pnlS2에 component 추가
		pnlS2.add(lblWinnerT);
		pnlS2.add(lblWinner);
		// pnlSouth에 각 패널 추가
		pnlSouth.add(Box.createVerticalStrut(50));
		pnlSouth.add(pnlS1);
		pnlSouth.add(pnlS2);
		pnlSouth.add(Box.createVerticalStrut(30));
		
		pnl.add(pnlNorth, "North");
		pnl.add(pnlWest, "West");
		pnlWest.setPreferredSize(new Dimension(100, 0));
		pnl.add(pnlCenter, "Center");
		pnl.add(pnlEast, "East");
		pnl.add(pnlSouth, "South");
		pnlSouth.setPreferredSize(new Dimension(0, 200));
		
		add(pnl);
		
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
			} else {
				JOptionPane.showMessageDialog(null, "가장 처음장 입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (command == btnNext) {
			int index = cbList.getSelectedIndex();
			if (index + 1 < winNumber.size()) { 
				int round = Integer.parseInt(lblRound.getText());
				round++;
				lblRound.setText(String.valueOf(round));				
				cbList.setSelectedIndex(index + 1);
				searchRound();
			} else {
				JOptionPane.showMessageDialog(null, "마지막장 입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public void searchRound() {
		int round = cbList.getSelectedIndex() + 1;
		for (int i = 0; i < 7; i++) {
			int num = winNumber.get(round).get(i);
			String name = "balls/ball" + num + ".png"; 
			lblWinNums[i].setIcon(convertToIcon(name, 40, 40));
			lblBonusNum.setIcon(convertToIcon(name, 40, 40));
		}
		lblWinPrice.setText(String.valueOf(winPrice.get(round - 1)));
		lblWinner.setText(String.valueOf(winPerson.get(round - 1)));		
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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Previous().showGUI();
			}
		});
	}
}
