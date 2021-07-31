package com.havendan.challengemelicupon.util;
/**
 * @author havendan
 *
 */

public class ApplicationEndPoint {
	private static String ITEM = "/items/";
	 
    public static String getPathItem(String item) {
        return ITEM.concat(item);
    }
}
