import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

class ClickMe3 extends JFrame {
	private JButton btn; // 춘식이
	private JButton btn2; // 안보이는 버튼
	private JLabel lbltime;
	private JLabel sign1;
	private JLabel sign2;
	private int time = 30;
	private Timer timer;

	public ClickMe3() {
		
		ImageIcon icon = convertToIconGIF("춘식이.gif");
		ImageIcon icon2 = convertToIcon("돈주머니.png", 80, 80); // 사이즈 지정 안하면 원본?
		ImageIcon icon3 = convertToIcon("성공.png", 700, 500);
		ImageIcon icon4 = convertToIcon("실패.png", 700, 500);
		lbltime = new JLabel();
		sign1 = new JLabel(icon3);
		sign2 = new JLabel(icon4);
		lbltime.setBounds(25, 20, 50, 50); // 시계 위치

		MyImageBackgroundPanel pnl = new MyImageBackgroundPanel(new Methods().backgroud("모래사장.png"));
		add(pnl); // 백그라운드 이미지 추가
		setVisible(true);

		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(500, 500));
		btn = new JButton();
		btn.setOpaque(false);
		btn.setIcon(icon); // 춘식이
		sign1.setBounds(0, 0, 700, 500);
		sign2.setBounds(0, 0, 700, 500);
		btn.setBackground(new Color(255, 0, 0, 0));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false); // 테두리 없애기
		btn.setBounds(280, 180, 120, 120); // 춘식
		pnl.add(lbltime);
		pnl.add(btn);
		pnl.add(sign1);
		pnl.add(sign2);
		lbltime.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		sign1.setVisible(false);
		sign2.setVisible(false);
		lbltime.setVisible(true);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (time == 30) {
					JOptionPane.showMessageDialog(null, "노는게 좋은 춘식이를 잡아주세요.", "춘식이는 배가 고파요.", JOptionPane.QUESTION_MESSAGE);
				}
				lbltime.setText(String.valueOf(time));
				time--;
				if (time == 0) {
					timer.stop();
					System.out.println("게임오버");
					sign2.setVisible(true);
					btn.setVisible(false);
					lbltime.setVisible(false);

				}
			}
		});
		timer.start();

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Random r = new Random();
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				 btn.setLocation(x, y); // 춘식이 위치 이동
				int num = r.nextInt(5);
//				 String name = "chunsic/" + "춘식" + num + ".png";
//				 btn.setIcon(convertToIcon(name, 80, 80));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btn.setIcon(icon2);
				timer.stop();
				btn.setVisible(false);
				sign1.setVisible(true);
				lbltime.setVisible(false);
			}

		});

		add(pnl);

		pack();
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true); // 창이 보인다
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

	public ImageIcon convertToIconGIF(String name) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Image image = kit.getImage(classLoader.getResource(imageName));
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
