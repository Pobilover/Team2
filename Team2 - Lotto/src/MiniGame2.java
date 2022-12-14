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
	
	public MiniGame2() {
		
		pnlGame = new JPanel();
		pnlGame.setPreferredSize(new Dimension(800, 600));
		pnlGame.setLayout(new GridLayout(6, 8));
		btn = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			numbers.add(i + 1);
		}
		Collections.shuffle(numbers);
		System.out.println(numbers);
		
		for (int i = 0; i < 48; i++) {
			String image = "e\\e" + numbers.get(i) +".png";
			btn.add(new JButton());
			btn.get(i).setIcon(convertToIcon(image, 100, 100));
			btn.get(i).setBorder(new LineBorder(Color.DARK_GRAY, 1));
			btn.get(i).setBackground(Color.LIGHT_GRAY);
			btn.get(i).addActionListener(this);
			pnlGame.add(btn.get(i));
		}
		
		add(pnlGame);
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
			if (icon1 == icon2) {
				new ShowImage();
			} else {
				btn.get(beforeBtnNum).setIcon(icon2);
				btn.get(afterBtnNum).setIcon(icon1);
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
