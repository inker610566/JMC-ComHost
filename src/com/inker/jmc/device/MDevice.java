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
	
	public MDevice(String adbPath) throws ConnectException {
		// create chimpchat connection
		TreeMap<String, String> options = new TreeMap<>();
		options.put("backend", "adb");
		options.put("adbLocation", adbPath);
		mChimpChat = ChimpChat.getInstance(options);
		// try connection
		mDevice = mChimpChat.waitForConnection(TIMEOUT, ".*");
		if (mDevice == null) {
			throw new ConnectException();
		}
		mDevice.wake();
		// get ChimpManger
		cm = mDevice.getManager();
	}
	
	@Override
	public IMImage screenshot() {
		return new MImage(mDevice.takeSnapshot());
	}

	@Override
	public void touchDown(int x, int y) throws IOException {
		cm.touchDown(x, y);
	}

	@Override
	public void touchMove(int x, int y) throws IOException {
		// TODO Auto-generated method stub
		// need test
	}

	
	@Override
	public void touchUp(int x, int y) throws IOException {
		cm.touchUp(x, y);
	}
}
