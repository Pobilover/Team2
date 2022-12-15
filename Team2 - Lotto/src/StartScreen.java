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
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class StartScreen extends JFrame implements ActionListener {
	
	// Buttons
	private RoundedButton round1;
	private RoundedButton round2;
	private RoundedButton round3;
	private RoundedButton round4;

	private Purchase purchase;
	private resultScreenSet rsScreen;
	private Previous previous;	

	private Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
	private Map<Integer, Map<Integer, String>> sheetTypes = new TreeMap<>();
	private Map<Integer, List<Integer>> winNums = new TreeMap<>();
	private List<Integer> winPrice = new ArrayList<>();
//	private Map<Integer, List<Integer>> winPrice = new ArrayList<>();
//	이렇게 통일하는게 나을지도?
	
	private int gameRound = 0;
	private boolean nextRound = false;
	private boolean leadOff = false;	

	public StartScreen() {
		// 전체 폰트 설정
		new Methods().setUIFont(new FontUIResource(new Font("휴먼편지체", 0, 20)));
		
		// 사용할 패널 선언 및 설정
		ImagePanel pnl = new ImagePanel(new Methods().convertToIcon("로또기계.png", 700, 500).getImage());
		pnl.setLayout(new BorderLayout());
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 30));
		pnlSouth.setPreferredSize(new Dimension(650, 100));
		pnlSouth.setBackground(new Color(100, 100, 100, 50));

		// pnlSouth에 들어갈 Buttons
		round1 = new RoundedButton("구매하기");
		round2 = new RoundedButton("당첨확인");
		round3 = new RoundedButton("이전회차");
		round4 = new RoundedButton("미니게임");
		round1.addActionListener(this);
		round2.addActionListener(this);
		round3.addActionListener(this);
		round4.addActionListener(this);
		
		// pnlSouth에 buttons 추가
		pnlSouth.add(round1);
		pnlSouth.add(round2);
		pnlSouth.add(round3);
		pnlSouth.add(round4);
		
		// pnl에 pnlSouth 추가
		pnl.add(pnlSouth, "South");
		
		// 프레임에 pnl 추가
		add(pnl);

		setSize(700, 500);
		setTitle("나눔 Lotto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showGUI() {
		setVisible(true);
	}
	
	public static void main(String[] args) {
		System.out.println("프로그램 실행");
		new StartScreen().showGUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		
		if (command == round1) {
			if (!nextRound && !leadOff) {
				purchase = new Purchase(gameRound);
				purchase.showGUI();
				leadOff = true;
			} else if (!nextRound) {
				purchase.showGUI();
			} else if (nextRound){
				purchase = new Purchase(gameRound);
				purchase.showGUI();
				nextRound = false;
			}
		}
		if (command == round2) {
			try {
				if (purchase.getSheets().get(0) != null && !nextRound) {
					this.sheets = purchase.getSheets();
					this.sheetTypes = purchase.getSheetTypes();				
					gameRound++;
					nextRound = true;
					rsScreen = new resultScreenSet(sheets, sheetTypes, gameRound);
					rsScreen.showGUI();
				} else if (purchase.getSheets().get(0) == null){
					JOptionPane.showMessageDialog(null, gameRound + 1 + "회차 1개 이상의 게임을 구매 후 확인 가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NullPointerException error) {
				JOptionPane.showMessageDialog(null, "1개 이상의 게임을 구매 후 확인 가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (command == round3) {
			if (gameRound > 0) {
				winNums.putAll(rsScreen.getWinNums());
				winPrice.addAll(rsScreen.getWinPrice());
				Previous previous = new Previous(gameRound, winNums, winPrice);
				previous.showGUI();
			} else {
				JOptionPane.showMessageDialog(null, "1번 이상의 당첨확인 후 열람 가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (command == round4) {
			new MiniGames().showGUI();
		}
	}

}
