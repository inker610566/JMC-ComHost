package com.inker.jmc.image;

import java.util.ArrayList;

import com.inker.jmc.image.match_result.MImageMatchResult;
import com.inker.jmc.utils.Complex;
import com.inker.jmc.utils.FFT;
/*
 * This class provide a default implementation of match method
 */
public abstract class MImageAbstract implements IMImage{
	
	protected int matchAtXY(IMImage target, int x, int y) {
		int ret = 0;
		for(int i = 0, u = y; i < height() ; i ++, u ++)
			for(int j = 0, v = x; j < width() ; j ++, v ++)
				ret += ((int)r(j,i)) - (target.r(v,u))*((int)r(j,i)) - (target.r(v,u)) +
					   ((int)g(j,i)) - (target.g(v,u))*((int)g(j,i)) - (target.g(v,u)) +
					   ((int)b(j,i)) - (target.b(v,u))*((int)b(j,i)) - (target.b(v,u));
		return ret;
	}
	
	
	
	
	public ArrayList<MImageMatchResult> match(IMImage target, int mnumber, int nearX, int nearY)
	{
		int len = 1,
			padlen = target.width()-width(),
			rh = target.height()-height()+1,
			rw = target.width()-width()+1;
//		System.out.println("plen: "+Integer.toString(padlen));
//		System.out.println("rh: "+Integer.toString(rh));
//		System.out.println("rw: "+Integer.toString(rw));
		int[][] sres = new int[rh][rw];
		for(int tlen = target.height()*target.width() ; len < tlen ; len <<= 1);
//		System.out.println(Integer.toString(len));
		Complex[] tar = new Complex[len], pat = new Complex[len], cres;
		int i;
		// r
		i = 0;
		for(int j = 0 ; j < target.height() ; j ++)
			for(int k = 0 ; k < target.width() ; k ++)
				tar[i++] = new Complex(target.r(k, j), 0);
		for(; i < len ; tar[i ++] = new Complex(0, 0));
//		for(int j = 0 ; j < tar.length ; j ++)
//			System.out.print(Double.toString(tar[j].re())+" ");
//		System.out.println("");
		i = 0;
		for(int h = height()-1, j = width()-1 ; j >= 0 ; j --) pat[i++] = new Complex(r(j,h), 0);
		for(int j = height()-2 ; j >= 0 ; j --) {
			for(int k = 0 ; k < padlen ; k ++)
				pat[i++] = new Complex(0, 0);
			for(int k = width()-1 ; k >= 0 ; k --)
				pat[i++] = new Complex(r(k, j), 0);
		}
		for( ; i < len ; pat[i++] = new Complex(0, 0));
//		for(int j = 0 ; j < pat.length ; j ++)
//			System.out.print(Double.toString(pat[j].re())+" ");
//		System.out.println("");
		
//		System.out.println(Integer.toString(len));
		cres = FFT.convolve(tar, pat);
//		for(int j = 0 ; j < cres.length ; j ++)
//			System.out.print(Double.toString(cres[j].re())+" ");
//		System.out.println("");
		for(int j = 0, v = height()*width()+(height()-1)*padlen-1 ; j < rh ; j ++, v += width()-1)
			for(int k = 0 ; k < rw ; k ++)
				sres[j][k] += -2 * (Math.round(cres[v++].re()));
//		for(int j = 0 ; j < rh ; j ++)
//			for(int k = 0 ; k < rw ; k ++)
//			System.out.print(Double.toString(sres[j][k])+" ");
//		System.out.println("");
		// g
		i = 0;
		for(int j = 0 ; j < target.height() ; j ++)
			for(int k = 0 ; k < target.width() ; k ++)
				tar[i++] = new Complex(target.g(k, j), 0);
		for(; i < len ; tar[i ++] = new Complex(0, 0));
		i = 0;
		for(int h = height()-1, j = width()-1 ; j >= 0 ; j --) pat[i++] = new Complex(g(j,h), 0);
		for(int j = height()-2 ; j >= 0 ; j --) {
			for(int k = 0 ; k < padlen ; k ++)
				pat[i++] = new Complex(0, 0);
			for(int k = width()-1 ; k >= 0 ; k --)
				pat[i++] = new Complex(g(k, j), 0);
		}
		for( ; i < len ; pat[i++] = new Complex(0, 0));
		
		cres = FFT.convolve(tar, pat);
		for(int j = 0, v = height()*width()+(height()-1)*padlen-1 ; j < rh ; j ++, v += width()-1)
			for(int k = 0 ; k < rw ; k ++)
				sres[j][k] += -2 * (Math.round(cres[v++].re()));
		// b
		i = 0;
		for(int j = 0 ; j < target.height() ; j ++)
			for(int k = 0 ; k < target.width() ; k ++)
				tar[i++] = new Complex(target.b(k, j), 0);
		for(; i < len ; tar[i ++] = new Complex(0, 0));
		i = 0;
		for(int h = height()-1, j = width()-1 ; j >= 0 ; j --) pat[i++] = new Complex(b(j,h), 0);
		for(int j = height()-2 ; j >= 0 ; j --) {
			for(int k = 0 ; k < padlen ; k ++)
				pat[i++] = new Complex(0, 0);
			for(int k = width()-1 ; k >= 0 ; k --)
				pat[i++] = new Complex(b(k, j), 0);
		}
		for( ; i < len ; pat[i++] = new Complex(0, 0));
		
		cres = FFT.convolve(tar, pat);
		for(int j = 0, v = height()*width()+(height()-1)*padlen-1 ; j < rh ; j ++, v += width()-1)
			for(int k = 0 ; k < rw ; k ++)
				sres[j][k] += -2 * (Math.round(cres[v++].re()));
		tar = pat = cres = null;
		
		// square sum of target
		int[][] ssr = new int[target.height()][rw],
				ssg = new int[target.height()][rw],
				ssb = new int[target.height()][rw];
		for(int j = 0 ; j < target.height() ; j ++) {
			for(int k = 0 ; k < width() ; k ++) {
				ssr[j][0] += target.r(k, j)*target.r(k, j);
				ssg[j][0] += target.g(k, j)*target.g(k, j);
				ssb[j][0] += target.b(k, j)*target.b(k, j);
			}
			for(int k = 1 ; k < rw ; k ++)
			{
				ssr[j][k] = ssr[j][k-1] - target.r(k-1, j)*target.r(k-1, j) + target.r(k-1+width(), j)*target.r(k-1+width(), j);
				ssg[j][k] = ssg[j][k-1] - target.g(k-1, j)*target.g(k-1, j) + target.g(k-1+width(), j)*target.g(k-1+width(), j);
				ssb[j][k] = ssb[j][k-1] - target.b(k-1, j)*target.b(k-1, j) + target.b(k-1+width(), j)*target.b(k-1+width(), j);
			}
		}
		int acr, acg, acb;
		for(int k = 0 ; k < rw ; k ++) {
			acr = acg = acb = 0;
			for(int j = 0 ; j < height() ; j ++) {
				acr += ssr[j][k];
				acg += ssg[j][k];
				acb += ssb[j][k];
			}
			sres[0][k] += acr + acg + acb;
			for(int j = 1 ; j < rh ; j ++) {
				acr -= ssr[j-1][k];
				acr += ssr[j-1+height()][k];
				acb -= ssb[j-1][k];
				acb += ssb[j-1+height()][k];
				acg -= ssg[j-1][k];
				acb += ssb[j-1+height()][k];
				sres[j][k] += acr + acg + acb;
			}
		}
		// print
		for(int j = 0 ; j < rh ; j ++) {
			for(int k = 0 ; k < rw ; k ++) {
				System.out.print(Integer.toString(sres[j][k]) + " ");
			}
			System.out.println("");
		}
		ArrayList<MImageMatchResult> res = new ArrayList<>();
		return res;
		
		// cres[]
		/*
		ArrayList<MImageMatchResult> res = new ArrayList<MImageMatchResult>(mnumber);
		for(int i = 0, u = height() ; u < target.height(); i ++, u ++)
		{
			System.out.println(Integer.toString(i)+",");
			for(int j = 0, v = width() ; v < target.width(); j ++, v ++)
			{
				int r = matchAtXY(target, j, i);

				if(res.size() < mnumber || r < res.get(res.size()-1).mval){
					int x;
					// find near result
					for(x = res.size() - 1; x >= 0 && (Math.abs(j-res.get(x).x) > nearX || Math.abs(i-res.get(x).y) > nearY) ; x --);
					
					if(x == -1){
						if(res.size() < mnumber){
							res.add(new MImageMatchResult(j,i, r));
							continue;
						}
						else
							x = res.size()-1;
					}
					else if(r > res.get(x).mval) continue;
					
					// insertion sort
					for(; x - 1 >= 0 && r < res.get(x-1).mval ; x --)
						res.set(x, res.get(x-1));
					res.set(x, new MImageMatchResult(j,i, r));
				}
			}
		}
		return res;*/
	}
}
