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
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	
	public List<Integer> getWinPrice() {
		return winPrice;
	}

	public void setWinPrice(List<Integer> winPrice) {
		this.winPrice = winPrice;
	}
	
	
	public Previous(int gameRound, Map<Integer, List<Integer>> winNums, List<Integer> winPrice) {
		this.gameRound = gameRound;
		this.winNums = winNums;
		this.winPrice = winPrice;
		
		// 임시적인 값 생성
		Random r = new Random();
		for (int i = 0; i < winPrice.size(); i++) {
			winPerson.add(r.nextInt(10) + 1);
		}
		
		// 주요패널 선언
		ImagePanel pnl = new ImagePanel(new Methods().convertToIcon("미국돈.png", 700, 500).getImage());
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
		lblRound.setFont(new Font("휴먼편지체", Font.PLAIN, 40));
		lblRound.setForeground(Color.black);
		lblRound.setOpaque(false);
		JLabel lblRoundT = new JLabel("회");
		lblRoundT.setFont(new Font("휴먼편지체", Font.PLAIN, 40));
		lblRoundT.setForeground(Color.black);
		JLabel lblResultT = new JLabel("당첨결과");
		lblResultT.setFont(new Font("휴먼편지체", Font.PLAIN, 40));
		JLabel lblSelectT = new JLabel("회차 바로가기");
		lblSelectT.setFont(new Font("휴먼편지체", Font.PLAIN, 14));
		String round[] = new String[winNums.size()];
		for (int i = 0; i < winNums.size(); i++) {
			round[i] = String.valueOf(i+1);
		}
		
		cbList = new JComboBox(round);
		cbList.setSelectedIndex(round.length - 1);
		cbList.setPreferredSize(new Dimension(100, 25));
		btnSearch = new RoundedButton("조회");
		btnSearch.setFont(new Font("휴먼편지체", Font.PLAIN, 14));
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
		pnlNorth.add(Box.createVerticalStrut(40));
		
		btnPre = new RoundedButton("이전회");
		btnPre.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		btnPre.addActionListener(this);
		// pnlWest에 component 추가
		pnlWest.add(Box.createVerticalStrut(80));
		pnlWest.add(btnPre);		
		
		btnNext = new RoundedButton("다음회");	
		btnNext.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
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
		lblPlus.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		lblBonusNum = new JLabel();
		lblBonusNum = new JLabel(methods.convertToIcon("balls/ballNull.png", 40, 40));			
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		
		pnlC1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnlC1.setOpaque(false);
		pnlC1.setPreferredSize(new Dimension(0, 50));		
		JPanel pnlC2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 175, 10));
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
		JLabel lblWinPriceT = new JLabel("1등 총상금 : ");
		lblWinPriceT.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		lblWinPrice = new JLabel("0");
		lblWinPrice.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		JLabel lblWinnerT = new JLabel("당첨자수 : ");
		lblWinnerT.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		lblWinner = new JLabel("0");
		lblWinner.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		
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
		
	}
	
	public void searchRound() {
		int round = cbList.getSelectedIndex() + 1;
		for (int i = 0; i < 7; i++) {
			int num = winNums.get(round).get(i);
			String name = "balls/ball" + num + ".png"; 
			lblWinNums[i].setIcon(convertToIcon(name, 40, 40));
			lblBonusNum.setIcon(convertToIcon(name, 40, 40));
		}
		DecimalFormat formatter = new DecimalFormat("###,###");
		int price = winPrice.get(round - 1);
		lblWinPrice.setText(formatter.format(price));
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

	}
}
