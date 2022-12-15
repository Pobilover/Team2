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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ClickMe3 extends JPanel {
	private JButton start;
	private JButton chunsik;
	private JButton jayG;
	private JButton ryan;
	private JButton restart;
	private JLabel clearPage;
	private JLabel losePage;
	private Timer timer;
	private JLabel lbltime;
	private int time = 30;
	private boolean gameStart = false;

	public ClickMe3() {

		ImageIcon startImage = convertToIcon("게임설명.png", 800, 600);
		ImageIcon chunsikImage = convertToIconGIF("춘식이.gif");
		ImageIcon jayGImage = convertToIconGIF("제이지.gif");
		ImageIcon ryanImage = convertToIconGIF("라이언.gif");
		ImageIcon restartImage = convertToIcon("다시.png", 150, 60);
		ImageIcon clearPageImage = convertToIcon("성공.png", 800, 600);
		ImageIcon losePageImage = convertToIcon("실패.png", 800, 600);

		lbltime = new JLabel();
		clearPage = new JLabel(clearPageImage);
		losePage = new JLabel(losePageImage);
		lbltime.setBounds(25, 20, 50, 50); // 시계 위치

		ImagePanel pnl = new ImagePanel(new Methods().convertToIcon("겨울배경.png", 800, 600).getImage());
		add(pnl);

		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(800, 600));
		start = new JButton();
		chunsik = new JButton();
		jayG = new JButton();
		ryan = new JButton();
		restart = new JButton();
		restart.setBackground(new Color(0, 0, 0, 0));
		chunsik.setOpaque(false);
		restart.setBorderPainted(false);
		restart.setOpaque(false);
		jayG.setOpaque(false);
		ryan.setOpaque(false);
		start.setIcon(startImage);
		chunsik.setIcon(chunsikImage);
		jayG.setIcon(jayGImage);
		ryan.setIcon(ryanImage);
		restart.setIcon(restartImage);

		clearPage.setBounds(0, 0, 800, 600);
		losePage.setBounds(0, 0, 800, 600);
		chunsik.setBackground(new Color(255, 0, 0, 0));
		jayG.setBackground(new Color(255, 0, 0, 0));
		ryan.setBackground(new Color(255, 0, 0, 0));
		chunsik.setBorderPainted(false);
		chunsik.setFocusPainted(false);
		jayG.setBorderPainted(false);
		jayG.setFocusPainted(false);
		ryan.setBorderPainted(false);
		ryan.setFocusPainted(false);
		restart.setBorderPainted(false); // 테두리 없애기
		restart.setFocusPainted(false); // 띠 없애기
		start.setBounds(0, 0, 800, 600);
		chunsik.setBounds(400, 400, 120, 120);
		jayG.setBounds(400, 400, 120, 120);
		ryan.setBounds(400, 400, 120, 120);
		restart.setBounds(550, 450, 150, 60);
		pnl.add(lbltime);
		pnl.add(start);
		pnl.add(chunsik);
		pnl.add(jayG);
		pnl.add(ryan);
		pnl.add(restart);
		pnl.add(clearPage);
		pnl.add(losePage);
		lbltime.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		start.setVisible(true);
		jayG.setVisible(false);
		ryan.setVisible(false);
		restart.setVisible(false);
		clearPage.setVisible(false);
		losePage.setVisible(false);
		lbltime.setVisible(false);

		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lbltime.setVisible(true);
				gameStart = true;
				start.setVisible(false);
				timer.start();
				jayG.setVisible(true);
				ryan.setVisible(true);
			}

		});

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gameStart) {

				} else {
					lbltime.setText(String.valueOf(time));
					time--;

				}
				if (time <= 0) {
					timer.stop();
					System.out.println("게임오버");
					losePage.setVisible(true);
					lbltime.setVisible(false);
					restart.setVisible(true);
					chunsik.setVisible(false);
					jayG.setVisible(false);
					ryan.setVisible(false);

				}
			}
		});
		timer.start();

		Timer jayGMove = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				jayG.setLocation(x, y);
			}
		});
		jayGMove.start();

		jayG.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				time -= 3;
			}

		});

		Timer ryanMove = new Timer(300, new ActionListener() {
						@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int x = r.nextInt(800);
				int y = r.nextInt(800);
				ryan.setLocation(x, y);
			}
		});
		ryanMove.start();

		ryan.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				time += 1;

			}

		});

		chunsik.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Random r = new Random();
				int x = r.nextInt(430);
				int y = r.nextInt(430);
				chunsik.setLocation(x, y); //----------------------------------------------------------------------------------------

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				chunsik.setVisible(false);
				restart.setVisible(true);
				clearPage.setVisible(true);
				lbltime.setVisible(false);
				jayG.setVisible(false);
				ryan.setVisible(false);
			}

		});

		restart.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				restart.setIcon(convertToIcon("다시깜빡.png", 150, 60));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				restart.setIcon(convertToIcon("다시.png", 150, 60));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				time = 30;
				timer.stop();
				chunsik.setVisible(true);
				restart.setVisible(false);
				clearPage.setVisible(false);
				lbltime.setVisible(true);
				start.setVisible(true);
				losePage.setVisible(false);
				jayG.setVisible(true);
				ryan.setVisible(true);
			}

		});

		add(pnl);

		setSize(800, 600);
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
