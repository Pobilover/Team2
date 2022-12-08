import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Methods {
	
	// 이미지 파일을 넣으면 Icon으로 반환
	// 파라미터 : 이미지파일이름, 출력할 가로크기, 출력할 세로크기
	// 이미지가 폴더에 있다면 "폴더명/이미지명"으로 입력
	public ImageIcon convertToIcon(String name, int width, int height) {
		String imageName = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource(imageName));
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		return icon;
	}
	
	// 패널에 배경을 넣기위한 메소드
	// 파라미터에는 이미지 파일이름
	// 배경을 넣을 패널은 JPanel이 아닌 MyImageBackgroundPanel로 선언
	// 사용예시 : MyImageBackgroundPanel pnl = new MyImageBackgroundPanel(backgroud("배경.png"));
	public BufferedImage backgroud(String name) {
		ClassLoader loader = getClass().getClassLoader();	
		URL imageURL = loader.getResource(name);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
