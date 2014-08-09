package com.inker.jmc;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import com.inker.jmc.device.MDevice;
import com.inker.jmc.device.exception.ConnectException;
import com.inker.jmc.image.FileImage;
import com.inker.jmc.image.MImage;
import com.inker.jmc.image.MImageClip;
import com.inker.jmc.image.match_result.MImageMatchResult;

public class Main {
	public static void main(String[] args) {
		MDevice device = null;
		try {
			device = new MDevice("/Users/kuoin/adt-bundle-mac-x86_64-20140321/sdk/platform-tools/adb");
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't Connecect!");
		}
		
		
		if(device != null) {
			//((MImage)device.screenshot()).savePNG("123.png");
			
			MImage tar = (MImage) device.screenshot();
			
			FileImage pat = null;
			
			try {
				pat = new FileImage("heart.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Read at error");
				e.printStackTrace();
			}
			if(pat != null) {
				Rectangle range = new Rectangle((pat.width()-40)/2, (pat.height()-40)/2, 40, 40);
				MImageClip pc = new MImageClip(pat, range);
				ArrayList<MImageMatchResult> r = pat.match(tar, 30, 50, 50);
				System.out.println("Result:");
				for(int i = 0 ; i < r.size() ; i ++){
					System.out.println("(x,y,dist)="+Integer.toString(r.get(i).x)+","+Integer.toString(r.get(i).y)+","+Integer.toString(r.get(i).mval));
				}
			}
		}
		System.out.println("Finish!");
		device.close();
	}
}
