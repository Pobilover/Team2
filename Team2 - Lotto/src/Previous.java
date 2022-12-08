import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class Previous extends JDialog {
	public Previous() {	
		// 주요패널 선언
		MyImageBackgroundPanel pnl = new MyImageBackgroundPanel(backgroud("배경.png"));
		pnl.setBackground(Color.WHITE);
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 0, 0, 0));

		// 주요패널 레이아웃 선택
		pnl.setLayout(new BorderLayout());
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		// pnlNorth에 들어갈 component
		JLabel lblRound = new JLabel("1");
		lblRound.setFont(new Font("휴먼편지체", Font.BOLD, 35));
		lblRound.setForeground(Color.ORANGE);
		JLabel lblRoundT = new JLabel("회");
		lblRoundT.setFont(new Font("휴먼편지체", Font.BOLD, 35));
		lblRoundT.setForeground(Color.ORANGE);
		JLabel lblResultT = new JLabel("당첨결과");
		lblResultT.setFont(new Font("휴먼편지체", Font.BOLD, 25));
		JLabel lblSelectT = new JLabel("회차 바로가기");
		lblSelectT.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		String round[] = {"1", "2", "3", "4", "5"}; // 임시적인 배열
		JComboBox cbList = new JComboBox(round);
		cbList.setPreferredSize(new Dimension(100, 25));
		JButton btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		
		// pnlNorth에 들어갈 패널
		JPanel pnlN1 = new JPanel();
		pnlN1.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlN2 = new JPanel();
		pnlN2.setBackground(new Color(255, 0, 0, 0));
		
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
		
		// pnlWest에 들어갈 component
		JButton btnPre = new JButton("이전회");
		btnPre.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		// pnlWest에 component 추가
		pnlWest.add(Box.createVerticalStrut(80));
		pnlWest.add(btnPre);		
		
		// pnlEast에 들어갈 component
		JButton btnNext = new JButton("다음회");	
		btnNext.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		// pnlEast에 component 추가
		pnlEast.add(Box.createVerticalStrut(80));
		pnlEast.add(btnNext);
		pnlEast.add(Box.createHorizontalStrut(10));
		
		// pnlCenter에 들어갈 component
		JLabel[] lblWinNum = new JLabel[7];
		for (int i = 0; i < lblWinNum.length; i++) {
			ImageIcon icon = convertToIcon("balls/ball1.png", 40, 40);
			lblWinNum[i] = new JLabel(icon);
		}			
		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("휴먼편지체", Font.BOLD, 25));
		JLabel lblBonusNum = new JLabel();
		lblBonusNum = new JLabel(convertToIcon("balls/ball1.png", 40, 40));			
		JLabel lblWinNumT = new JLabel("당첨번호");
		lblWinNumT.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		JLabel lblBonusNumT = new JLabel("보너스");
		lblBonusNumT.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		
		// pnlCenter에 들어갈 Panel
		JPanel pnlC1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnlC1.setBackground(new Color(255, 0, 0, 0));
		pnlC1.setPreferredSize(new Dimension(0, 50));		
		JPanel pnlC2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 183, 10));
		pnlC2.setBackground(new Color(255, 0, 0, 0));
		
		// pnlC1에 component 추가
//		pnlC1.add(Box.createVerticalStrut(120));
		for (int i = 0; i < lblWinNum.length - 1; i++) {
			pnlC1.add(lblWinNum[i]);			
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
		JLabel lblWinPrice = new JLabel("0");
		lblWinPrice.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		JLabel lblWinnerT = new JLabel("당첨자수 : ");
		lblWinnerT.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		JLabel lblWinner = new JLabel("0");
		lblWinner.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		
		// pnlSouth에 들어갈 패널
		JPanel pnlS1 = new JPanel();
		pnlS1.setBackground(new Color(255, 0, 0, 0));
		JPanel pnlS2 = new JPanel();
		pnlS2.setBackground(new Color(255, 0, 0, 0));
		
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
		
		setSize(700, 500);
		setTitle("이전회차 조회");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
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
	
	public BufferedImage backgroud(String name) {
		ClassLoader loader = getClass().getClassLoader();	
		URL imageURL = loader.getResource(name);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
