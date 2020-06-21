package com.arosseto.g2glite.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Long> decodeLonList(String s) {
		if (!s.isEmpty()) {
			return Arrays.asList(s.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
		}
		else {
			return null;
		}
	}
	
	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
