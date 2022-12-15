import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MiniGame2 extends JPanel implements ActionListener {
	private JPanel pnlGame;
	private List<JButton> btn;
	private boolean select = false;
	private int beforeBtnNum = 0;
	private int afterBtnNum;
	private JButton temp;
	private Icon icon1;
	private Icon icon2;
	private List<ImageIcon> icon;
	private List<Integer> numbers;
	private int count = 0;
	private RoundedButton restart;
	private ImagePanel pnlEnd;
	private ShowImage showImage = new ShowImage();
	
	public MiniGame2() {
		
		pnlEnd = new ImagePanel(new Methods().convertToIcon("잘했어요.jpg", 800, 600).getImage());
		restart = new RoundedButton("다시하기");
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setBounds(650, 550, 100, 40);
		restart.addActionListener(this);
		pnlEnd.add(restart);
		
		pnlGame = new JPanel();
		pnlGame.setPreferredSize(new Dimension(800, 600));
		pnlGame.setLayout(new GridLayout(6, 8));
		icon = new ArrayList<>();
		
		btn = new ArrayList<>();
		numbers = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			numbers.add(i + 1);
		}
		
		for (int i = 0; i < 48; i++) {
			String image = "e/e" + numbers.get(i) +".png";
			icon.add(convertToIcon(image, 100, 100));
			btn.add(new JButton());
		}
		
		Collections.shuffle(numbers);
		
		for (int i = 0; i < 48; i++) {
			btn.get(i).setIcon(icon.get(numbers.get(i) - 1));
			btn.get(i).setBorder(new LineBorder(Color.DARK_GRAY, 1));
			btn.get(i).setBackground(Color.LIGHT_GRAY);
			btn.get(i).addActionListener(this);
			pnlGame.add(btn.get(i));
		}
		
		add(pnlGame);
		add(pnlEnd);
		pnlEnd.setVisible(false);
		setName("퍼즐 - hint : 같은 이미지를 누번 누르면 정답 이미지를 볼 수 있습니다.");
		setSize(800, 600);
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
	
	public static void main(String[] args) {
		new MiniGame2();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		if (btn.contains(command) && !select) {
			select = true;
			beforeBtnNum = btn.indexOf(command);
			icon1 = btn.get(beforeBtnNum).getIcon();
			btn.get(beforeBtnNum).setBorder(new LineBorder(Color.red, 4));
		} else if (btn.contains(command) && select) {
			select = false;
			btn.get(beforeBtnNum).setBorder(new LineBorder(Color.DARK_GRAY, 1));
			afterBtnNum = btn.indexOf(command);
			icon2 = btn.get(afterBtnNum).getIcon();
			if (icon1 == icon2 && !showImage.isVisible()) {
				showImage.setVisible(true); 
			} else if (icon1 != icon2) {
				btn.get(beforeBtnNum).setIcon(icon2);
				btn.get(afterBtnNum).setIcon(icon1);
			}
		}
		if (command == restart) {
			Collections.shuffle(numbers);
			for (int i = 0; i < 48; i++) {
				btn.get(i).setIcon(icon.get(numbers.get(i) - 1));
			}
			pnlGame.setVisible(true);
			pnlEnd.setVisible(false);			
		}
		count = 0;
		for (int i = 0; i < 48; i++) {
			if (i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 7 ||
					i == 33 || i == 41 || i == 42 || i == 43 || i == 44 || i == 45 || i == 46 || i ==47) {
				
			} else if (btn.get(i).getIcon() == icon.get(i)) {
				count++;
			}
			if (count == 34) {
				pnlGame.setVisible(false);
				pnlEnd.setVisible(true);
			}
		}
		
	}

class ShowImage extends JDialog {
	public ShowImage() {
		JLabel image = new JLabel(convertToIcon("퍼즐.png", 300, 200));
		add(image);
		setSize(350, 250);
		setTitle("정답 이미지");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(785, 0);
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
}
}
