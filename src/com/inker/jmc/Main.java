package com.inker.jmc;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import com.inker.jmc.device.MDevice;
import com.inker.jmc.device.exception.ConnectException;
import com.inker.jmc.image.FileImage;
import com.inker.jmc.image.MImage;
import com.inker.jmc.image.MImageClip;
import com.inker.jmc.image.UTestImage;
import com.inker.jmc.image.match_result.MImageMatchResult;
import com.inker.jmc.utils.Complex;
import com.inker.jmc.utils.FFT;

public class Main {
	public static void main(String[] args) {
		
		/*UTestImage tar = new UTestImage(
				new int[][]{new int[]{1,2,3},new int[]{4,5,6}},
				new int[][]{new int[]{0,0,0},new int[]{0,0,0}},
				new int[][]{new int[]{0,0,0},new int[]{0,0,0}}),
				pat = new UTestImage(
				new int[][]{new int[]{7,8},new int[]{9,10}},
				new int[][]{new int[]{0,0,0},new int[]{0,0,0}},
				new int[][]{new int[]{0,0,0},new int[]{0,0,0}});*/
		/*UTestImage tar = new UTestImage(
				new int[][]{new int[]{1,2},new int[]{3,4}, new int[]{5,6}},
				new int[][]{new int[]{0,0},new int[]{0,0},new int[]{0,0}},
				new int[][]{new int[]{0,0},new int[]{0,0},new int[]{0,0}}),
				pat = new UTestImage(
				new int[][]{new int[]{7,8},new int[]{9,10}},
				new int[][]{new int[]{0,0},new int[]{0,0},new int[]{0,0}},
				new int[][]{new int[]{0,0},new int[]{0,0},new int[]{0,0}});*/
		UTestImage tar = new UTestImage(
		new int[][]{new int[]{1,2,3},new int[]{3,4,5}, new int[]{5,6,7}},
		new int[][]{new int[]{1,2,3},new int[]{3,4,5}, new int[]{5,6,7}},
		new int[][]{new int[]{1,2,3},new int[]{3,4,5}, new int[]{5,6,7}}),
		pat = new UTestImage(
		new int[][]{new int[]{4,3},new int[]{2,1}},
		new int[][]{new int[]{4,3},new int[]{2,1}},
		new int[][]{new int[]{4,3},new int[]{2,1}});
		pat.match(tar, 0, 0, 0);
		/*Complex[] a = new Complex[]{new Complex(1, 0), new Complex(2, 0), new Complex(3, 0),new Complex(4, 0),};
		Complex[] b = new Complex[]{new Complex(5, 0), new Complex(6, 0), new Complex(7, 0),new Complex(8, 0),};
		Complex[] c = FFT.convolve(a, b);*/

		/*for(int i = 0 ; i < c.length ; i ++) {
			System.out.println(Double.toString(c[i].re())+","+Double.toString(c[i].im()));
		}*/
		
		/*MDevice device = null;
		try {
			device = new MDevice("/Users/kuoin/adt-bundle-mac-x86_64-20140321/sdk/platform-tools/adb");
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't Connecect!");
		}
		System.out.println("Get Devices");
		
		
		if(device != null) {
			//((MImage)device.screenshot()).savePNG("123.png");
			
			MImage tar = (MImage) device.screenshot();
			tar.savePNG("tmp.png");
			FileImage pat = null;
			
			try {
				pat = new FileImage("heart.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Read at error");
				e.printStackTrace();
			}
			System.out.println("Read File finish");
			if(pat != null) {
				Rectangle range = new Rectangle((pat.width()-40)/2, (pat.height()-40)/2, 40, 40);
				MImageClip pc = new MImageClip(pat, range);
				ArrayList<MImageMatchResult> r = pat.match(tar, 30, 50, 50);
				System.out.println("Result:");
				for(int i = 0 ; i < r.size() ; i ++) {
					System.out.println("(x,y,dist)="+Integer.toString(r.get(i).x)+","+Integer.toString(r.get(i).y)+","+Integer.toString(r.get(i).mval));
				}
			}
			else{
				System.out.println("No");
			}
		}
		System.out.println("Finish!");
		device.close();*/
	}
}
