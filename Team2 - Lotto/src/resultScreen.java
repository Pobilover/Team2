import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

class resultScreenSet extends JDialog{
	public resultScreenSet() {
		JPanel pnlBox = new JPanel();
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		JPanel pnl5 = new JPanel();
		
		pnlBox.setLayout(new BoxLayout(pnlBox, BoxLayout.Y_AXIS));
		
		JLabel turn = new JLabel("-");
		JLabel title = new JLabel("회 당첨결과");
		turn.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		title.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		pnl1.add(turn);
		pnl1.add(title);
		
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
		JLabel[] lblWinNum = new JLabel[7];
		for (int i = 0; i < lblWinNum.length; i++) {
			ImageIcon icon = convertToIcon("balls/ball1.png", 40, 40);
			lblWinNum[i] = new JLabel(icon);
		}
		JLabel plus = new JLabel("+");
		plus.setFont(new Font("바탕", Font.BOLD, 20));
		JLabel bonus = new JLabel();
		bonus.setIcon(convertToIcon("balls/ball7.png", 40, 40));
		for (int i = 0; i < lblWinNum.length - 1; i++) {
			pnl2_1.add(lblWinNum[i]);			
		}
		pnl2_1.add(plus);
		pnl2_1.add(bonus);
		pnl2_1.setPreferredSize(new Dimension(0, 40));
		pnl2_2.setPreferredSize(new Dimension(0, 10));
		pnl2.add(pnl2_1);
		pnl2.add(pnl2_2);
		//panel 크기 확인용
		//pnl2_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		
		JPanel pnl3_1 = new JPanel();
		pnl3_1.setPreferredSize(new Dimension(450, 60));
		pnl3_1.setBackground(Color.LIGHT_GRAY);
		JLabel resultWord = new JLabel("축하합니다! 총 ");
		JLabel resultWord2 = new JLabel("원 당첨입니다!");
		JLabel resultMoney = new JLabel("-");
		resultWord.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		resultWord2.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		resultMoney.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		resultWord.setForeground(Color.white);
		resultWord2.setForeground(Color.white);
		resultMoney.setForeground(Color.white);
		pnl3_1.add(Box.createVerticalStrut(50));
		pnl3_1.add(resultWord);
		pnl3_1.add(resultMoney);		
		pnl3_1.add(resultWord2);
		pnl3.add(Box.createHorizontalStrut(5));
		pnl3.add(pnl3_1);
		
		JButton before = new JButton("<<<");
		JButton after = new JButton(">>>");	
		
		JPanel pnl4_1 = new JPanel(new GridLayout(5, 9));		
		JLabel lblA = new JLabel("A");
		JLabel lblB = new JLabel("B");
		JLabel lblC = new JLabel("C");
		JLabel lblD = new JLabel("D");
		JLabel lblE = new JLabel("E");
		JLabel[] lblType = new JLabel[5];
		for (int i = 0; i < lblType.length; i++) {
			lblType[i] = new JLabel("자동");
		}
		JLabel[] lblRank = new JLabel[5];
		for (int i = 0; i < lblRank.length; i++) {
			lblRank[i] = new JLabel("1등");
		}
		JLabel[][] lblUserNumbers = new JLabel[5][6];
		for (int i = 0; i < lblUserNumbers.length; i++) {
			for (int j = 0; j < lblUserNumbers[i].length; j++) {
				int number = 0;
				String imgName = "balls/" + number + ".png" ;
				lblUserNumbers[i][j] = new JLabel(convertToIcon("balls/ball1.png", 30, 30));
			}
		}
		pnl4_1.add(lblA);
		pnl4_1.add(lblType[0]);
		pnl4_1.add(lblRank[0]);
		
		
//		JTable resultTable = new JTable(5,4);		
//		//table 값 만들기
//		DefaultTableModel model = (DefaultTableModel)resultTable.getModel();
//		String[] record = new String[4];
//		record[0] = "A";
//		record[1] = "자동";
//		record[2] = "1등";
//		record[3] = "2 3 5 6 7 9";
//		model.addRow(record);
		
		before.setPreferredSize(new Dimension(55,40));
		before.setBackground(Color.LIGHT_GRAY);
		after.setPreferredSize(new Dimension(55,40));
		after.setBackground(Color.LIGHT_GRAY);
//		resultTable.setPreferredSize(new Dimension(300,150));
//		resultTable.setRowHeight(30);
		pnl4.add(before);
		pnl4.add(Box.createHorizontalStrut(30));
//		pnl4.add(resultTable);
		pnl4.add(Box.createHorizontalStrut(30));
		pnl4.add(after);
		
		JLabel lbl1 = new JLabel("구매: ");
		JLabel lbl2 = new JLabel("장");
		JLabel lbl2_1 = new JLabel(", ");		
		JLabel lbl3 = new JLabel("게임: ");
		JLabel lbl4 = new JLabel("게임");
		pnl5.add(lbl1);
		pnl5.add(lbl2);
		pnl5.add(lbl2_1);
		pnl5.add(lbl3);
		pnl5.add(lbl4);
		
		pnlBox.add(pnl1);
		pnlBox.add(pnl2);
		pnlBox.add(pnl3);
		pnlBox.add(pnl4);
		pnlBox.add(pnl5);
		
		add(pnlBox);
		setModal(true);
		setSize(700,500);
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
}