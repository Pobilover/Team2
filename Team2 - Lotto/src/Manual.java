import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;

public class Manual extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Manual dialog = new Manual();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Manual() {
		setBackground(Color.WHITE);
		setModal(true);
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon icon = convertToIcon("manual2.png", 480, 410);
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(18, 15, 485, 410);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1. 로또선택 방법 선택");
		lblNewLabel_1.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel_1.setBounds(538, 14, 191, 18);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("6개의 번호를 수동, 반자동, 자동으로 입력가능합니다.");
		lblNewLabel_2.setBounds(538, 42, 354, 29);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("선택수량 자동선택을 체크하고 확인을 누르면");
		lblNewLabel_6.setBounds(538, 69, 354, 18);
		contentPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("선택한 수량만큼 자동입력 됩니다.\r\n");
		lblNewLabel_7.setBounds(538, 89, 244, 18);
		contentPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_4 = new JLabel("2. 번호선택 영역");
		lblNewLabel_4.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel_4.setBounds(538, 165, 254, 18);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("6개의 로또번호를 클릭하면 해당 번호에 색상이 체크가됩니다.");
		lblNewLabel_5.setBounds(538, 193, 418, 18);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_10 = new JLabel("번호를 부분적으로 선택한 후 자동선택을 하면,");
		lblNewLabel_10.setBounds(538, 213, 354, 18);
		contentPanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("나머지 번호는 자동으로 번호가 발급됩니다.");
		lblNewLabel_11.setBounds(538, 232, 354, 18);
		contentPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_8 = new JLabel("3. 번호확인\r\n");
		lblNewLabel_8.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel_8.setBounds(538, 260, 132, 18);
		contentPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("선택한 번호에 대해 구매할 수량을 선택(최대 5개)한 후");
		lblNewLabel_9.setBounds(538, 111, 354, 18);
		contentPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_12 = new JLabel("확인 버튼을 클릭합니다.");
		lblNewLabel_12.setBounds(538, 133, 354, 18);
		contentPanel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("선택한 번호에 대해 확인하는 영역입니다.");
		lblNewLabel_13.setBounds(538, 288, 354, 18);
		contentPanel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_3 = new JLabel("자동 : 시스템에서 자동으로 발급하는 방식을 선택한 것으로,");
		lblNewLabel_3.setBounds(538, 313, 430, 18);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_14 = new JLabel("구매 후 번호 확인이 가능합니다.");
		lblNewLabel_14.setBounds(580, 331, 292, 18);
		contentPanel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("수동 : 번호를 직접 선택한 방식으로, 구매 시 해당 번호로 발행됩니다.");
		lblNewLabel_15.setBounds(538, 354, 442, 18);
		contentPanel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("반자동 : 번호를 부분적으로 선택한 방식으로, ");
		lblNewLabel_16.setBounds(538, 377, 308, 18);
		contentPanel.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("자동으로 선택한 부분은 구매 후 번호 확인이 가능합니다.");
		lblNewLabel_17.setBounds(594, 396, 364, 18);
		contentPanel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("4. 선택번호 추가기능");
		lblNewLabel_18.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel_18.setBounds(538, 432, 187, 18);
		contentPanel.add(lblNewLabel_18);
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
