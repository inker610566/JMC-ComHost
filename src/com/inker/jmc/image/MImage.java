package com.inker.jmc.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.android.chimpchat.core.IChimpImage;
import com.inker.jmc.image.match_result.MImageMatchResult;

/*
 * Wrapper of Monkey IChimpImage
 */
public class MImage extends MImageAbstract{
	BufferedImage img;
	IChimpImage iimg;
	public MImage(IChimpImage iimg){
		this.iimg = iimg;
		this.img = this.iimg.getBufferedImage();
	}
	
	/*
	 * @param	filename	path + filename e.g. ./test/filename.png
	 */
	public void savePNG(String filename){
		iimg.writeToFile(filename, "png");
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
		return (byte)((iimg.getPixel(x, y)>>16)&0x000000ff);
	}
	@Override
	public byte g(int x, int y) {
		return (byte)((iimg.getPixel(x, y)>>8)&0x000000ff);
	}
	@Override
	public byte b(int x, int y) {
		return (byte)((iimg.getPixel(x, y)>>0)&0x000000ff);
	}
	
}
