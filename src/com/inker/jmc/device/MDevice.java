package com.inker.jmc.device;

import java.awt.color.CMMException;
import java.io.IOException;
import java.util.TreeMap;

import org.omg.CORBA.TIMEOUT;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.ChimpManager;
import com.android.chimpchat.core.IChimpDevice;
import com.inker.jmc.device.exception.ConnectException;
import com.inker.jmc.image.IMImage;
import com.inker.jmc.image.MImage;

public class MDevice implements IMDevice{

	private ChimpChat mChimpChat;
	private IChimpDevice mDevice;
	private ChimpManager cm;
	private final int TIMEOUT = 10000;
	
	/*
	 * If it don't work(blocked or failed with error message), try
	 * 		$ adb kill-server.
	 * 		$ adb devices
	 * Or unplug your usb to com and replug again.
	*/
	public MDevice(String adbPath) throws ConnectException {
		// create chimpchat connection
		System.out.println("create chimpchat connection");
		TreeMap<String, String> options = new TreeMap<>();
		options.put("backend", "adb");
		options.put("adbLocation", adbPath);
		mChimpChat = ChimpChat.getInstance(options);
		System.out.println("finish");
		// try connection
		System.out.println("try connect");
		mDevice = mChimpChat.waitForConnection(TIMEOUT, ".*");
		if (mDevice == null) {
			throw new ConnectException();
		}
		System.out.println("finish");
		mDevice.wake();
		// get ChimpManger
		System.out.println("get ChimpManger");
		cm = mDevice.getManager();
		System.out.println("finish");
	}
	
	@Override
	public IMImage screenshot() {
		try {
			return new MImage(mDevice.takeSnapshot());			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void touchDown(int x, int y) {
		try {
			cm.touchDown(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void touchMove(int x, int y) {
		// TODO Auto-generated method stub
		try {
			cm.touchMove(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void touchUp(int x, int y) {
		try {
			cm.touchUp(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		mDevice.dispose();
	}
}
