package com.inker.jmc.image;

public class UTestImage extends MImageAbstract{

	int h, w;
	int[][] r,g,b;
	
	public UTestImage(int[][] R, int[][] G, int[][] B) {
		r = R;
		g = G;
		b = B;
		h = R.length;
		w = R[0].length;
	}
	
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return h;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return w;
	}

	@Override
	public byte r(int x, int y) {
		// TODO Auto-generated method stub
		return (byte) r[y][x];
	}

	@Override
	public byte g(int x, int y) {
		// TODO Auto-generated method stub
		return (byte) g[y][x];
	}

	@Override
	public byte b(int x, int y) {
		// TODO Auto-generated method stub
		return (byte) b[y][x];
	}

}
