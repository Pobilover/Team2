import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JFrame implements ActionListener {
	
	private RoundedButton round1;
	private RoundedButton round2;
	private RoundedButton round3;

	public void ChangeImageSize() {
		System.out.println("결과 확인 ");
		ImageIcon icon = new ImageIcon("128x128.png");

		Image img = icon.getImage();
		// 창의 사이즈인 700,500에 맞춰서 이미지를 변경
		//Image changeImg = img.getScaledInstance(700,500, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(img);
		JLabel lbl = new JLabel(changeIcon);

		// SCALE_DEFAULT, SCALE_FAST, SCALE_SMOOTH, SCALE_REPLICATE,
		// SCALE_AREA_AVERAGING\
		JPanel pnl = new JPanel();
		pnl.add(lbl);
		add(pnl); //
		setTitle("Lotto");
		setSize(700, 500);
		setVisible(true);

	}

	public StartScreen() {
		ChangeImageSize();
		JPanel under = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 40));
		round1 = new RoundedButton("구매하기");
		round2 = new RoundedButton("당첨확인");
		round3 = new RoundedButton("이전회차");
		round1.addActionListener(this);
		round2.addActionListener(this);
		round3.addActionListener(this);
		under.setBackground(Color.red);
		under.add(round1);
		under.add(round2);
		under.add(round3);
		add(under, "South");

		setSize(700, 500);
		setTitle("나눔 Lotto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showGUI() {
		setVisible(true);
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
			new Purchase();
		}
		if (command == round2) {
			new resultScreenSet();
		}
		if (command == round3) {
			new Previous();
		}
	}

}
