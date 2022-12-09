import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


class Purchase extends JDialog implements MouseListener {
	private JLabel[] lottoNums;
	private JPanel pnlNum;
	private String name;
	private JLabel[] lblChoNums;
	private ImageIcon choiceImage;

	public Purchase() {
		setModal(true); // 부모 창이랑 상호작용 못하게 막음
		
		// 제일 큰 패널
		JPanel pnlBuy = new JPanel();
		
		// 로또번호 선택가능한 패널
		JPanel pnlWest = new JPanel();
		pnlWest.setBounds(50, 100, 250, 400);
		pnlWest.setBorder(new LineBorder(Color.black));
//		pnlNum.setBackground(Color.WHITE);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		
		// 로또번호 선택창 이미지 패널
		JPanel pnlImage = new JPanel();
		JLabel lottoImage = new JLabel();
		ImageIcon lottoIcon = getIcon("lotto123.png", 230, 80);
		lottoImage.setIcon(lottoIcon);
		pnlImage.add(lottoImage);
		
		pnlNum = new JPanel();
		pnlNum.setPreferredSize(new Dimension(250, 250));
		lottoNums = new JLabel[45];
		pnlNum.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < lottoNums.length; i++) {
			name = "num" + (i + 1) + ".png";
			lottoNums[i] = new JLabel(getIcon("numbers/" + name, 30, 30));
			pnlNum.add(lottoNums[i]);
			lottoNums[i].addMouseListener(this);
		}
		
		
		// 로또번호 선택기능 패널
		JPanel pnlNumSkill1 = new JPanel();
		pnlNumSkill1.setLayout(new FlowLayout());
		JButton reset = new JButton("초기화");
		JButton auto = new JButton("자동선택");
		pnlNumSkill1.add(reset);
		pnlNumSkill1.add(Box.createHorizontalStrut(20));
		pnlNumSkill1.add(auto);
		
		// 로또번호 수량선택 패널
		JPanel pnlNumSkill2 = new JPanel();
		pnlNumSkill2.setLayout(new FlowLayout());
		JLabel count = new JLabel("수량");
		String list[] = {"1", "2", "3", "4", "5"};
		JComboBox cList = new JComboBox(list);
		JButton ok = new JButton("확인");
		pnlNumSkill2.add(count);
		pnlNumSkill2.add(cList);
		pnlNumSkill2.add(Box.createHorizontalStrut(30));
		pnlNumSkill2.add(ok);
		
		pnlWest.add(pnlImage);
		pnlWest.add(pnlNum);
		pnlWest.add(pnlNumSkill1);
		pnlWest.add(pnlNumSkill2);
		
		pnlBuy.add(pnlWest);
		
		
		// 로또번호 선택 보이는 큰 패널
		JPanel pnlEast = new JPanel();
		pnlEast.setBounds(300, 20, 300, 400);
		pnlEast.setBorder(new LineBorder(Color.black));
//		pnlChoiceNum.setBackground(Color.WHITE);
		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		
		// 전체 초기화기능 패널
		JPanel pnlReset = new JPanel();
		JLabel lblConfirm = new JLabel("선택번호 확인");
		JButton allReset = new JButton("초기화");
		pnlReset.add(lblConfirm);
		pnlReset.add(Box.createHorizontalStrut(170));
		pnlReset.add(allReset);
		
		// 선택한 번호 보이는 패널
		JPanel pnlChoNum = new JPanel();
		pnlChoNum.setLayout(new BoxLayout(pnlChoNum, BoxLayout.Y_AXIS));
		
		int j = 0;
		while(j < 5) {
			j++;
			JPanel pnlChoSet = new JPanel();
			pnlChoSet.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			String choCount = String.valueOf(j);
			JLabel lblChoConut = new JLabel(choCount);
			JLabel lblChoAuto = new JLabel("자동");
			lblChoNums = new JLabel[6];
			for (int i = 0; i < lblChoNums.length ; i++) {
				choiceImage = getIcon("balls/" + "ball1.png", 30, 30);
				lblChoNums[i] = new JLabel(choiceImage);
			}
			
			JButton choReset = new JButton("수정");
			JButton choDelete = new JButton("삭제");
			
			pnlChoSet.add(lblChoConut);
			pnlChoSet.add(lblChoAuto);
			pnlChoSet.add(Box.createHorizontalStrut(5));
			for (int i = 0; i < lblChoNums.length; i++) {
				pnlChoSet.add(lblChoNums[i]);
			}
			pnlChoSet.add(Box.createHorizontalStrut(5));
			pnlChoSet.add(choReset);
			pnlChoSet.add(choDelete);
			pnlChoNum.add(pnlChoSet);
		}
		
		// 금액과 구매버튼 패널
		JPanel pnlPurchase = new JPanel();
		pnlPurchase.setLayout(new FlowLayout());
		JLabel lblSheet = new JLabel("장");
		JLabel lbltotal = new JLabel("결제 금액 :      원");
		JButton btnPurchase = new JButton("구매");
		pnlPurchase.add(lblSheet);
		pnlPurchase.add(Box.createHorizontalStrut(20));
		pnlPurchase.add(lbltotal);
		pnlPurchase.add(Box.createHorizontalStrut(150));
		pnlPurchase.add(btnPurchase);
		
		pnlEast.add(pnlReset);
		pnlEast.add(pnlChoNum);
		pnlEast.add(pnlPurchase);
		pnlBuy.add(pnlEast);
		
		add(pnlBuy);

		setSize(700, 500);
		setVisible(true);
		
	}
	
	public ImageIcon getIcon(String name, int width, int height) {
		String imageName = name;
		int w = width;
		int h = height;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Image image = kit.getImage(classLoader.getResource(imageName));
			image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			// 이미지크기조절
			ImageIcon icon = new ImageIcon(image);
			return icon;	
		} catch(NullPointerException e) {
			System.out.println(imageName + "이 없습니다.");
		}
		return null;
	 }

	@Override
	public void mouseClicked(MouseEvent e) {
		Object after = e.getSource();
		for (int i = 0; i < lottoNums.length; i++) {
			name = "num" + (i + 1) + ".png";
			ImageIcon icon1 = getIcon("numbers/" + name, 30, 30);
			ImageIcon icon2 = getIcon("afterNumbers/" + name, 30, 30);			
			if (after == lottoNums[i]) {
				System.out.println("클릭");
				if (lottoNums[i].getIcon() == icon1) {
					lottoNums[i].setIcon(icon2);
				} else if (lottoNums[i].getIcon() == icon2) {
					lottoNums[i].setIcon(icon1);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}