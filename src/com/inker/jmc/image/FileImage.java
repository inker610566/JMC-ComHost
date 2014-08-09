package com.inker.jmc.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileImage extends MImageAbstract{

	BufferedImage img;
	
	public FileImage(String filename) throws IOException {
		img = ImageIO.read(new File(filename));
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
		return (byte) ((img.getRGB(x, y)>>16)&0x000000ff);
	}

	@Override
	public byte g(int x, int y) {
		return (byte) ((img.getRGB(x, y)>>8)&0x000000ff);
	}

	@Override
	public byte b(int x, int y) {
		return (byte) ((img.getRGB(x, y)>>0)&0x000000ff);
	}

}
