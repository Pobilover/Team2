import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


//import java.util.Date;
//
public class TimerStudy implements ActionListener
{
	private JFrame hhh;
	public TimerStudy(JFrame hhh) {
		super();
		this.hhh=hhh;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 열려있는 창을 닫아야함.
		System.out.println("2초가 지났음");
		hhh.setVisible(false);
		
	}
////	int n = 0;
//	
//	public void actionPerformed(ActionEvent e) {
////		Date date = new Date();
////		n++;
////		System.out.println(n+"번째 호출: " + date.toString());
//	}
}
