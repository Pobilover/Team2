import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class MyImageBackgroundPanel extends JPanel {
	private BufferedImage backgroundImage;
	
	public MyImageBackgroundPanel(BufferedImage image) {
		setOpaque(false);
		this.backgroundImage = image;
		setPreferredSize(new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(backgroundImage, 0, 0, this);
		g2.dispose();
	}
	
}