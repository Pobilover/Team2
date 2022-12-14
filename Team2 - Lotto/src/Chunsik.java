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
	private JButton bt; // 설명버튼
	private JButton btn; // 춘식이
	private JButton btn2; // 안보이는 버튼
	private JLabel lbltime;
	private JLabel sign1;
	private JLabel sign2;
	private int time = 30;
	private Timer timer;
	private boolean gameStart = false;
	private JButton btn3;

	public ClickMe3() {
		
		ImageIcon hi = convertToIcon("게임설명.png", 800, 600);
		ImageIcon icon = convertToIconGIF("춘식이.gif");
//		ImageIcon icon1 = convertToIcon("시작.png", 100, 40);
		ImageIcon icon2 = convertToIcon("다시3.png", 100, 40);
		ImageIcon icon3 = convertToIcon("성공.png", 800, 600);
		ImageIcon icon4 = convertToIcon("실패.png", 800, 600);
		ImageIcon icon5 = convertToIconGIF("제이지.gif");
		
		lbltime = new JLabel();
		sign1 = new JLabel(icon3);
		sign2 = new JLabel(icon4);
		lbltime.setBounds(25, 20, 50, 50); // 시계 위치

		MyImageBackgroundPanel pnl = new MyImageBackgroundPanel(new Methods().backgroud("겨울배경.png"));
		add(pnl); // 백그라운드 이미지 추가
		

		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(800, 600));
		bt = new JButton();
		btn = new JButton();
		btn2 = new JButton("다시하기");
		btn2.setBackground(new Color(0, 0, 0, 0));
		btn3 = new JButton();
		btn.setOpaque(false);
		btn2.setBorderPainted(false);
		btn2.setOpaque(false);
		btn3.setOpaque(false);
		bt.setIcon(hi); // 게임설명
		btn.setIcon(icon); // 춘식이
		btn2.setIcon(icon2); // 다시하기
		btn3.setIcon(icon5);
		

		
		sign1.setBounds(0, 0, 800, 600);
		sign2.setBounds(0, 0, 800, 600);
		btn.setBackground(new Color(255, 0, 0, 0));
		btn3.setBackground(new Color(255, 0, 0, 0));
		btn.setBorderPainted(false); // 테두리 없애기
		btn3.setBorderPainted(false);
		btn.setFocusPainted(false); // << 얘뭐임? ----------------------------------------------------궁금함
		btn3.setFocusPainted(false); // 띠 없애기
		bt.setBounds(0, 0, 800, 600); // 게임 설명으로 시작
		btn.setBounds(280, 180, 120, 120); // 춘식
		btn2.setBounds(650, 500, 130, 50); // 다시하기 버튼
		btn3.setBounds(100, 100, 120, 120);
		pnl.add(lbltime);
		pnl.add(bt);
		pnl.add(btn);
		pnl.add(btn2);
		pnl.add(btn3);
		pnl.add(sign1);
		pnl.add(sign2);
		lbltime.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		bt.setVisible(true);
		btn2.setVisible(false); // 다시하기 감추기
		btn3.setVisible(false);
		sign1.setVisible(false);
		sign2.setVisible(false);
		lbltime.setVisible(false);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gameStart) {
//					JOptionPane.showMessageDialog(null, "노는게 좋은 춘식이를 잡아주세요.", "춘식이는 배가 고파요.", JOptionPane.QUESTION_MESSAGE);
				} else {
					lbltime.setText(String.valueOf(time));
					time--;
					
				}
				if (time <= 0) {
					timer.stop();
					System.out.println("게임오버");
					sign2.setVisible(true);
					btn.setVisible(false);
					lbltime.setVisible(false);
					btn2.setVisible(true);
					btn3.setVisible(false);

				}
			}
		});
		timer.start();
		
		Timer timer2 =new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				btn3.setLocation(x, y);
			}
		});
		timer2.start();

		bt.addMouseListener(new MouseAdapter() { // 시작화면 -------------------------------------

			@Override
			public void mouseClicked(MouseEvent e) {
				lbltime.setVisible(true);
				gameStart = true;
				bt.setVisible(false);
				timer.start();
				btn3.setVisible(true);
			}

		});
		
		
		
		
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Random r = new Random();
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				btn.setLocation(400, 400); // 춘식이 위치 이동 ----------------------------------------------------
				
//				int num = r.nextInt(5);
//				 String name = "chunsic/" + "춘식" + num + ".png";
//				 btn.setIcon(convertToIcon(name, 80, 80));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				btn.setVisible(false);
				btn2.setVisible(true);
				sign1.setVisible(true);
				lbltime.setVisible(false);
				btn3.setVisible(false);
			}

		});
		
		btn2.addMouseListener(new MouseAdapter() { // 다시하기 다시하기 다시하기 다시하기 다시하기 다시하기 다시하기 다시하기 

			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setIcon(convertToIcon("다시3.png", 100, 40));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btn2.setIcon(convertToIcon("다시3.png", 100, 40));
			}

			@Override
			public void mouseClicked(MouseEvent e) { // 다시하기 누를때
				time = 30;
				timer.stop();
				btn.setVisible(true); // 춘식이
				btn2.setVisible(false); // 다시하기버튼
				sign1.setVisible(false); // 게임 설명
				lbltime.setVisible(true); // 시간
				bt.setVisible(true); // 
				sign2.setVisible(false);
				btn3.setVisible(true);
			}

		});
		
		btn3.addMouseListener(new MouseAdapter() { // 제이지

			@Override
			public void mouseClicked(MouseEvent e) { // 시간을 깎음
				time -= 10;
			}

		});

		add(pnl);

		pack();
		setSize(800, 600);
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
		ClickMe3 frame = new ClickMe3(); // 창이 나옴
	}
}

