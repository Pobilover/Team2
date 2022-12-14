import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

public class MiniGames extends JDialog {
	public MiniGames(){
		
		JTabbedPane jTab = new JTabbedPane();
		MiniGame2 miniGame = new MiniGame2();
		ClickMe ClickMe = new ClickMe();
		
		jTab.addTab("춘식이를 잡아라~!", ClickMe);
		jTab.addTab("퍼즐 - 같은 그림을 두번 두르면 정답이미지가 보입니다.", miniGame);
		ClickMe.setPreferredSize(new Dimension(700, 500));
		miniGame.setPreferredSize(new Dimension(820, 600));
		
		add(jTab);
		
		setTitle("미니게임");
		setSize(830, 680);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void showGUI() {
		setVisible(true);
	}
}
