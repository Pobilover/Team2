import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

// Method 사용법!!
// new Methods().사용할 메소드
// 사용예제 : new Methods().convertToIcon("이미지.png", 100, 100);

public class Methods {
	
	// 이미지 파일을 넣으면 Icon으로 반환
	// 파라미터 : 이미지파일이름, 출력할 가로크기, 출력할 세로크기
	// 이미지가 폴더에 있다면 "폴더명/이미지명"으로 입력
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
	
	// 창 전체 폰트 설정하는 메소드
	// 이 메소드를 복사하여 적용시킬 클래스에 붙여넣기
	// 파라미터에는 new FontUIResource(new Font("폰트이름", 스타일, 크기)) 입력
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
