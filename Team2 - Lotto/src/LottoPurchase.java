import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.tools.DiagnosticCollector;


class Purchase extends JDialog{
	public Purchase() {
		setModal(true); // 부모 창이랑 상호작용 못하게 막음
		
		JPanel pnlBuy = new JPanel(new BorderLayout());
		
		JPanel pnlNum = new JPanel();
		pnlNum.setBackground(Color.WHITE);
		pnlNum.setLayout(new BoxLayout(pnlNum, BoxLayout.Y_AXIS));
		
		JPanel pnlNum1 = new JPanel();
		JLabel lottoImage = new JLabel();
		ImageIcon lottoIcon = convertToIcon("lotto.png", 230, 50);
		lottoImage.setIcon(lottoIcon);
		pnlNum1.add(lottoImage);
		
		JPanel pnlNum2 = new JPanel();
		JLabel[] lottoNumber = new JLabel[45];
		pnlNum2.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < lottoNumber.length; i++) {
			String name = "num" + (i + 1) + ".png";
			lottoNumber[i] = new JLabel(convertToIcon("numbers/" + name, 30, 30));
			pnlNum2.add(lottoNumber[i]);
		}

		JPanel pnlNum3 = new JPanel();
		pnlNum3.setLayout(new FlowLayout());
		JButton reset = new JButton("초기화");
		JButton auto = new JButton("자동선택");
		pnlNum3.add(reset);
		pnlNum3.add(auto);
		
		JPanel pnlNum4 = new JPanel();
		pnlNum4.setLayout(new FlowLayout());
		JLabel count = new JLabel("수량");
		String list[] = {"1", "2", "3", "4", "5"};
		JComboBox cList = new JComboBox(list);
		JButton ok = new JButton("확인");
		pnlNum4.add(count,"Center");
		pnlNum4.add(cList);
		pnlNum4.add(ok);
		
		pnlNum.add(pnlNum1);
		pnlNum.add(pnlNum2);
		pnlNum.add(pnlNum3);
		pnlNum.add(pnlNum4);
		
		pnlBuy.add(pnlNum, BorderLayout.WEST);
		
		
		JPanel pnlChoiceNum = new JPanel();
		pnlChoiceNum.setBackground(Color.WHITE);
		pnlChoiceNum.setLayout(new BoxLayout(pnlChoiceNum, BoxLayout.Y_AXIS));
		
		JPanel pnlCho1 = new JPanel();
		JLabel lbl = new JLabel("선택번호 확인");
		JButton allReset = new JButton("초기화");
//		pnlCho1.add(Box.createHorizontalStrut(500));
		pnlCho1.add(lbl);
		pnlCho1.add(allReset);
		
		
		JPanel pnlCho2 = new JPanel();
		pnlCho2.setLayout(new BoxLayout(pnlCho2, BoxLayout.Y_AXIS));
		
		JPanel pnlChoSet = new JPanel();
		pnlChoSet.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel lblChoC = new JLabel("1");
		JLabel[] lblChoNum = new JLabel[6];
		for (int i = 0; i < lblChoNum.length ; i++) {
			ImageIcon choiceImage = convertToIcon("chnum1.png", 30, 30);
			lblChoNum[i] = new JLabel(choiceImage);
		}
		
		JButton choReset = new JButton("수정");
		JButton choDelete = new JButton("삭제");
		
		pnlChoSet.add(lblChoC);
		for (int i = 0; i < lblChoNum.length; i++) {
			pnlChoSet.add(lblChoNum[i]);
		}
		
		pnlChoSet.add(choReset);
		pnlChoSet.add(choDelete);
		pnlCho2.add(pnlChoSet);
		
		JPanel pnlCho3 = new JPanel();
		pnlCho3.setLayout(new FlowLayout());
		JLabel total = new JLabel("원");
		JButton purchase = new JButton("구매");
		pnlCho3.add(total);
		pnlCho3.add(purchase);
		
		
		pnlChoiceNum.add(pnlCho1);
		pnlChoiceNum.add(pnlCho2);
		pnlChoiceNum.add(pnlCho3);
		pnlBuy.add(pnlChoiceNum, BorderLayout.EAST);
		
//		pnlChoiceNum.setPreferredSize(new Dimension(700, 0));
//		
		
		add(pnlBuy);
		

		setSize(700, 500);
		setVisible(true);
		
	}
	
	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		// 이미지크기조절
		ImageIcon icon = new ImageIcon(image);
		return icon;
	 }
}