import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MiniGame extends JDialog {
	private JPanel pnlGame;
	private JButton[] btn;

	public MiniGame() {
		setModal(true);
		
		pnlGame = new JPanel();
		pnlGame.setLayout(new GridLayout(6, 8));
		
		btn = new JButton[48];
		for (int i = 0; i < btn.length; i++) {
			String image = "b\\b" + (i + 1) +".png";
			btn[i] = new JButton(convertToIcon(image, 100, 100));
			btn[i].setBorder(new LineBorder(Color.DARK_GRAY,(int) 0.5));
			btn[i].setBackground(Color.LIGHT_GRAY);
			btn[i].addMouseListener(adapter);
			pnlGame.add(btn[i]);
		}
		
		
		add(pnlGame);
		
		setSize(800, 600);
		setVisible(true);
	}

	MouseAdapter adapter = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Object command = e.getSource();
			int count = 0;
			boolean[] click = new boolean[btn.length];
			for (int i = 0; i < btn.length; i++) {
				if(command == btn[i]) {
					ImageIcon imgName = convertToIcon("e/e" + (i + 1) +".png", 100, 100);
					btn[i].setIcon(imgName);
					click[i] = true;
				}
			}
			for(int i = 0; i < btn.length; i++) {
				if (click[i] == true) {
					count++;
					System.out.println(count);
				}
				if(count == btn.length) {
				}
			}
		}
	};

	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		return icon;
	}
	
	public static void main(String[] args) {
		new MiniGame();
	}

}
