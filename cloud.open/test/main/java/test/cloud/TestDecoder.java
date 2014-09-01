package test.cloud;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestDecoder {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "%E9%A9%AF%E9%BE%99%E9%AB%98%E6%89%8B2";
		System.out.println(URLDecoder.decode(str, "utf-8"));
		
		System.out.println(URLEncoder.encode(str, "utf-8"));
		
	}
}
