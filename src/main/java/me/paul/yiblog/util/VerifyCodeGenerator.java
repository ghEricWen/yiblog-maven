package me.paul.yiblog.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class VerifyCodeGenerator {
	
	private BufferedImage image;
	
	public VerifyCodeGenerator() {
		paint();
	}
			
	public void paint(){
		Graphics g = image.getGraphics();
		g.drawString("1234",0,0);
	}
	
	public BufferedImage getImage() {
		return image;
	}

}
