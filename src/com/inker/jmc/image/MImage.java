package com.inker.jmc.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.android.chimpchat.core.IChimpImage;
import com.inker.jmc.image.match_result.MImageMatchResult;

/*
 * Wrapper of Monkey IChimpImage
 */
public class MImage extends MImageAbstract{
	BufferedImage img;
	public MImage(IChimpImage iimg) throws IOException{
		iimg.writeToFile("screenshot.png", "png");
		this.img = ImageIO.read(new File("screenshot.png"));
	}
	
	/*
	 * @param	filename	path + filename e.g. ./test/filename.png
	 */
	public void savePNG(String filename){
		//img..writeToFile(filename, "png");
	}
	
	@Override
	public int height() {
		return img.getHeight();
	}
	@Override
	public int width() {
		return img.getWidth();
	}
	@Override
	public byte r(int x, int y) {
		return (byte)((img.getRGB(x, y)>>16)&0x000000ff);
	}
	@Override
	public byte g(int x, int y) {
		return (byte)((img.getRGB(x, y)>>8)&0x000000ff);
	}
	@Override
	public byte b(int x, int y) {
		return (byte)((img.getRGB(x, y)>>0)&0x000000ff);
	}
	
}
