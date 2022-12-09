import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

class Purchase extends JDialog implements MouseListener {
	private JLabel[] lottoNums;
	private JPanel pnlNum;
	private String name;
	private JLabel[] lblChoNums;
	private ImageIcon choiceImage;
	private boolean[] lottoNumsIcon;
	
	public Purchase() {
		setModal(true); // 부모 창이랑 상호작용 못하게 막음
		
		setUIFont(new FontUIResource(new Font("휴먼편지체", 0, 15)));
		
		// 제일 큰 패널
		MyImageBackgroundPanel pnlBuy = new MyImageBackgroundPanel(new Methods().backgroud("배경.png"));
		
		// 로또번호 선택가능한 패널
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(Color.WHITE);
		pnlWest.setBounds(50, 100, 250, 400);
		pnlWest.setBorder(new LineBorder(Color.black, 2, true));
//		pnlNum.setBackground(Color.WHITE);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		
		// 로또번호 선택창 이미지 패널
		JPanel pnlImage = new JPanel();
		pnlImage.setBackground(Color.WHITE);
		JLabel lottoImage = new JLabel();
		ImageIcon lottoIcon = getIcon("lotto123.png", 230, 80);
		lottoImage.setIcon(lottoIcon);
		pnlImage.add(lottoImage);
		
		pnlNum = new JPanel();
		pnlNum.setBackground(Color.WHITE);
		lottoNums = new JLabel[45];
		lottoNumsIcon = new boolean[45];
		pnlNum.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < lottoNums.length; i++) {
			String name = "num" + (i + 1) + ".png";
			lottoNums[i] = new JLabel(getIcon("numbers/" + name, 30, 30));
			lottoNums[i].addMouseListener(this);
			pnlNum.add(lottoNums[i]);
			lottoNumsIcon[i] = false;
		}
		
		
		
		// 로또번호 선택기능 패널
		JPanel pnlNumSkill1 = new JPanel();
		pnlNumSkill1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNumSkill1.setBackground(Color.WHITE);
		JButton reset = new JButton("초기화");
		JButton auto = new JButton("자동선택");
		pnlNumSkill1.add(reset);
		pnlNumSkill1.add(Box.createHorizontalStrut(10));
		pnlNumSkill1.add(auto);
		
		// 로또번호 수량선택 패널
		JPanel pnlNumSkill2 = new JPanel();
		pnlNumSkill2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNumSkill2.setBackground(Color.WHITE);
		JLabel count = new JLabel("적용수량");
		String list[] = {"1", "2", "3", "4", "5"};
		JComboBox cList = new JComboBox(list);
		cList.setPreferredSize(new Dimension(80, 20));
		JButton ok = new JButton("확인");
		pnlNumSkill2.add(count);
		pnlNumSkill2.add(cList);
		pnlNumSkill2.add(Box.createHorizontalStrut(5));
		pnlNumSkill2.add(ok);
		
		pnlWest.add(pnlImage);
		pnlWest.add(pnlNum);
		pnlWest.add(pnlNumSkill1);
		pnlWest.add(pnlNumSkill2);
		
		pnlBuy.add(pnlWest);
		
		
		// 로또번호 선택 보이는 큰 패널
		JPanel pnlEast = new JPanel();
		pnlEast.setPreferredSize(new Dimension(400, 380));
		pnlEast.setBorder(new LineBorder(Color.black, 2, true));
//		pnlChoiceNum.setBackground(Color.WHITE);
		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		
		// 전체 초기화기능 패널
		JPanel pnlReset = new JPanel();
		JLabel lblConfirm = new JLabel("선택번호 확인");
		RoundedButton allReset = new RoundedButton("초기화");
		allReset.setBackground(new Color(128, 128, 128));
		allReset.setForeground(Color.white);
		pnlReset.add(lblConfirm);
		pnlReset.add(Box.createHorizontalStrut(197));
		pnlReset.add(allReset);
		
		// 선택한 번호 보이는 패널
		JPanel pnlChoNum = new JPanel();
		pnlChoNum.setBackground(Color.WHITE);
		pnlChoNum.setLayout(new BoxLayout(pnlChoNum, BoxLayout.Y_AXIS));
		JPanel[] pnlChoSet = new JPanel[5];
		for (int j = 0; j < pnlChoSet.length; j++) {
			pnlChoSet[j] = new JPanel();
			pnlChoSet[j].setBackground(Color.WHITE);
			pnlChoSet[j].setBorder(new LineBorder(Color.black, 1, true));
			pnlChoSet[j].setLayout(new FlowLayout(FlowLayout.CENTER));
			pnlChoSet[j].addMouseListener(this);
			
			String choCount = String.valueOf(j + 1);
			JLabel lblChoConut = new JLabel(choCount);
			JLabel[] lblChoNum = new JLabel[6];
			for (int i = 0; i < lblChoNum.length ; i++) {
				ImageIcon choiceImage = getIcon("balls/" + "ball1.png", 30, 30);
				lblChoNum[i] = new JLabel(choiceImage);
			}
			
			JButton choReset = new JButton("수정");
			JButton choDelete = new JButton("삭제");
			
			pnlChoSet[j].add(lblChoConut);
			pnlChoSet[j].add(Box.createHorizontalStrut(5));
			for (int i = 0; i < lblChoNum.length; i++) {
				pnlChoSet[j].add(lblChoNum[i]);
			}
			pnlChoSet[j].add(Box.createHorizontalStrut(5));
			pnlChoSet[j].add(choReset);
			pnlChoSet[j].add(choDelete);
			pnlChoNum.add(pnlChoSet[j]);
		}
		
		// 금액과 구매버튼 패널
		JPanel pnlPurchase = new JPanel();
		pnlPurchase.setLayout(new FlowLayout());
		JLabel lblSheetT = new JLabel("구매수 : ");
		JLabel lblSheet = new JLabel("0");
		JLabel lblSheetTT = new JLabel("장");
		JLabel lbltotalT = new JLabel("결제 금액 : ");
		JLabel lbltotal = new JLabel("0");
		JLabel lblTotal = new JLabel("원");
		
		JButton btnPurchase = new JButton("구매");
		pnlPurchase.add(lblSheetT);		
		pnlPurchase.add(lblSheet);
		pnlPurchase.add(lblSheetTT);
		pnlPurchase.add(Box.createHorizontalStrut(20));
		pnlPurchase.add(lbltotalT);
		pnlPurchase.add(lbltotal);
		pnlPurchase.add(lblTotal);
		pnlPurchase.add(Box.createHorizontalStrut(20));
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
		Object command = e.getSource();
		for (int i = 0; i < lottoNums.length; i++) {
			name = "num" + (i + 1) + ".png";		
			if (command == lottoNums[i]) {
				if (lottoNumsIcon[i] == false) {
					lottoNums[i].setIcon(getIcon("afterNumbers/"+name, 25, 25));
					lottoNumsIcon[i] = true;
				} else {
					lottoNums[i].setIcon(getIcon("numbers/"+name, 30, 30));
					lottoNumsIcon[i] = false;					
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
	
    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }	
}

public class LottoPurchase {
	public static void main(String[] args) {
		new Purchase();
	}
}
