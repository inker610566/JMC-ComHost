package com.inker.jmc.device;

import java.io.IOException;

import com.inker.jmc.image.IMImage;


/*
 * This class represent a real device(e.g. phone, tablet) and will handle
 * multiscreen problem for you
 */
public interface IMDevice {
	IMImage screenshot();
	
	void touchDown(int x, int y);
	
	void touchMove(int x, int y);
	
	void touchUp(int x, int y);
	
	/*
	 * close connection
	 */
	void close();
}