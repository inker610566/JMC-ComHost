package com.inker.jmc.image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.inker.jmc.image.match_result.MImageMatchResult;

/*
 * A clip of an image into the rectangle shape
 * TODO: If it can clip twice?
 */
public class MImageClip extends MImageAbstract{
	Rectangle range;
	IMImage img;
	
	public MImageClip(IMImage targetImage, Rectangle range){
		this.img = targetImage;
		this.range = range;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return range.height;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return range.width;
	}

	@Override
	public byte r(int x, int y) {
		// TODO Auto-generated method stub
		return img.r(range.x + x, range.y + y);
	}

	@Override
	public byte g(int x, int y) {
		// TODO Auto-generated method stub
		return img.g(range.x + x, range.y + y);
	}

	@Override
	public byte b(int x, int y) {
		// TODO Auto-generated method stub
		return img.b(range.x + x, range.y + y);
	}
	
}
