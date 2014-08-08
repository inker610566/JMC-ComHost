package com.inker.jmc.device;

import java.io.IOException;

import com.inker.jmc.image.IMImage;


/*
 * This class represent a real device(e.g. phone, tablet) and will handle
 * multiscreen problem for you
 */
public interface IMDevice {
	IMImage screenshot();
	
	void touchDown(int x, int y) throws IOException;
	
	void touchMove(int x, int y) throws IOException;
	
	void touchUp(int x, int y) throws IOException;
}