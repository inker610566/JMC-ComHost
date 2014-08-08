package com.inker.jmc.image;

import java.util.ArrayList;

import com.inker.jmc.image.match_result.MImageMatchResult;
/*
 * This class provide a default implementation of match method
 */
public abstract class MImageAbstract implements IMImage{
	
	protected int matchAtXY(IMImage target, int x, int y){
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
		ArrayList<MImageMatchResult> res = new ArrayList<MImageMatchResult>(mnumber);
		for(int i = 0, u = i+height() ; u < target.height(); i ++, u ++)
			for(int j = 0, v = j+width() ; v < target.width(); j ++, v ++)
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
		return res;
	}
}
