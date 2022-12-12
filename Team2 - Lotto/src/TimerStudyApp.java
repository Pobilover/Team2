import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerStudyApp extends JFrame {
	private JPanel panel;
	private JLabel label;
	
	public TimerStudyApp() {
		setTitle("");
		setSize(700, 500);
		
		panel = new JPanel();
		label = new JLabel("");
		ImageIcon icon = new ImageIcon("가즈아.gif");
		label.setIcon(icon);
		panel.add(label);
		add(panel);
		
		ActionListener listener = new TimerStudy(TimerStudyApp.this);
		Timer t=new Timer(2000, listener);
		t.start();
//		for(int i=0;i<15;i++)
//		{
//			try{
//				System.out.println("반복문"+i);
//				Thread.sleep(1000);
//				setVisible(true);
//			}
//			catch(InterruptedException e){
//				setVisible(false);
//			}
//		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TimerStudyApp().setVisible(true);
		
//		System.out.println("메인쓰레드 START");
		
		
		
//		System.out.println("메인쓰레드 END");
	}
}