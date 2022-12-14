import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.tools.DiagnosticCollector;

public class StartScreen extends JFrame implements ActionListener {
	
	private RoundedButton round1;
	private RoundedButton round2;
	private RoundedButton round3;
	private RoundedButton round4;
	private int gameRound = 0;
	private boolean nextRound = false;
	private boolean leadoff = false;
	Purchase purchase;
	Previous previous = new Previous();
	private Map<Integer, Map<Integer, List<Integer>>> sheets = new TreeMap<>();
	private Map<Integer, Map<Integer, String>> sheetTypes = new TreeMap<>();

	public StartScreen() {
		ImagePanel pnl = new ImagePanel(new Methods().convertToIcon("로또기계.png", 700, 500).getImage());
		pnl.setLayout(new BorderLayout());
		JPanel under = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 30));
		under.setBackground(new Color(255, 0, 0, 0));
		round1 = new RoundedButton("구매하기");
		round2 = new RoundedButton("당첨확인");
		round3 = new RoundedButton("이전회차");
		round4 = new RoundedButton("미니게임");
		round1.addActionListener(this);
		round2.addActionListener(this);
		round3.addActionListener(this);
		round4.addActionListener(this);
		under.setBackground(new Color(100, 100, 100, 50));
		under.add(round1);
		under.add(round2);
		under.add(round3);
		under.add(round4);
		pnl.add(under, "South");
		round1.setFont(new Font("빙그레체", Font.PLAIN, 20));
		round2.setFont(new Font("빙그레체", Font.PLAIN, 20));
		round3.setFont(new Font("빙그레체", Font.PLAIN, 20));
		round4.setFont(new Font("빙그레체", Font.PLAIN, 20));
		under.setPreferredSize(new Dimension(650, 100));
		add(pnl);

		setSize(700, 500);
		setTitle("나눔 Lotto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showGUI() {
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

	public class RoundedButton extends JButton {
		public RoundedButton() {
			super();
			decorate();
		}

		public RoundedButton(String text) {
			super(text);
			decorate();
		}

		public RoundedButton(Action action) {
			super(action);
			decorate();
		}

		public RoundedButton(Icon icon) {
			super(icon);
			decorate();
		}

		public RoundedButton(String text, Icon icon) {
			super(text, icon);
			decorate();
		}

		protected void decorate() {
			setBorderPainted(false);
			setOpaque(false);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();

		    Graphics2D graphics = (Graphics2D) g;

		    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		    if (getModel().isArmed()) {
		        graphics.setColor(getBackground().darker());
		    } else if (getModel().isRollover()) {
		        graphics.setColor(getBackground().brighter());
		    } else {
		        graphics.setColor(getBackground());
		    }

		    graphics.fillRoundRect(0, 0, width, height, 10, 10);

		    FontMetrics fontMetrics = graphics.getFontMetrics();
		    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		    int textX = (width - stringBounds.width) / 2;
		    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

		    graphics.setColor(getForeground());
		    graphics.setFont(getFont());
		    graphics.drawString(getText(), textX, textY);
		    graphics.dispose();
		    

		    super.paintComponent(g);
		}
	}

	public static void main(String[] args) {
		System.out.println("프로그램 실행");
		new StartScreen().showGUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		
		if (command == round1) {
			if (!nextRound && !leadoff) {
				purchase = new Purchase();
				purchase.showGUI();
				leadoff = true;
			} else if (!nextRound) {
				purchase.showGUI();
			} else {
				purchase = new Purchase();
				purchase.showGUI();
			}
		}
		if (command == round2) {
			this.sheets = purchase.getSheets();
			this.sheetTypes = purchase.getSheetTypes();
			if (sheets.get(0) != null) {
				gameRound++;
				new resultScreenSet(sheets, sheetTypes).showGUI();
			} else {
				JOptionPane.showMessageDialog(null, "1개 이상의 게임을 구매 후 확인 가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (command == round3) {
			previous.showGUI();
		}
		if (command == round4) {
			new MiniGames().showGUI();
		}
	}

}
