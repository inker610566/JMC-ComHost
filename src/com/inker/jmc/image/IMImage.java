package com.inker.jmc.image;

import java.util.ArrayList;

import com.inker.jmc.image.match_result.MImageMatchResult;

public interface IMImage {
	int height();
	int width();
	byte r(int x, int y);
	byte g(int x, int y);
	byte b(int x, int y);
	/*
	 * find this pattern in target
	 * @param	target		The target to be matched
	 * @param	mnumber		Return the "mnumber" match result with minimal mval
	 * @param	nearX		If two match result are too close(within nearX, nearY), one will consume another.
	 * @param	nearY		If two match result are too close(within nearX, nearY), one will consume another.
	 * @return				A list of match result
	 */
	ArrayList<MImageMatchResult> match(IMImage target, int mnumber, int nearX, int nearY);
}
