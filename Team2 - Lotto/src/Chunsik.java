import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

class ClickMe3 extends JDialog {
	private Image background=new ImageIcon(Chunsik.class.getResource("chunsic/background.png")).getImage();
	private JButton btn;

	public ClickMe3() {
		ImageIcon icon = convertToIcon("나눔로또.png", 80, 80);
		ImageIcon icon2 = convertToIcon("돈주머니.png", 80, 80);
				
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(500, 500));
		btn = new JButton();
		btn.setIcon(icon);
		btn.setBackground(new Color(255, 0, 0, 0));
		btn.setBorderPainted(false);
		btn.setBounds(150, 150, 100, 100);
		pnl.add(btn);
		
		btn.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Random r = new Random();
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				btn.setLocation(x, y);
				int num = r.nextInt(5);
				String name = "chunsic/" + "춘식" + num + ".png";
				btn.setIcon(convertToIcon(name, 80, 80));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btn.setIcon(icon2);
				super.mouseClicked(e);
			}

			
			
		});
		

		add(pnl);
		
		pack();
		setTitle("춘식이를 잡아라~!");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void showGUI() {
		setVisible(true);
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
}
public class Chunsik {
	public static void main(String[] args) {
		ClickMe3 frame = new ClickMe3();
	}
}

