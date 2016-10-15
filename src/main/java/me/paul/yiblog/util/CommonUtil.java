package me.paul.yiblog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CommonUtil {

	public static Map<String, Integer> getPageMap(int pageCount, int page) {
		Map<String, Integer> mapPage = new LinkedHashMap<String, Integer>();
		mapPage.put("首页", 1);
		if (page < 5) {
			for (int i = 1; i <= (pageCount > 10 ? 10 : pageCount); i++) {
				mapPage.put(String.valueOf(i), Integer.valueOf(i));
			}
		} else {
			for (int i = page - 1; i <= (pageCount > page + 7 ? page + 7
					: pageCount); i++) {
				mapPage.put(String.valueOf(i), Integer.valueOf(i));
			}
		}
		mapPage.put("尾页", pageCount);
		return mapPage;
	}

	public static String generateMD5(String src) {
		String result = null;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (md5 == null) {
			return null;
		}

		byte[] target = md5.digest(src.getBytes());
		result = byteArrToHexStr(target);
		return result;
	}

	private static String byteArrToHexStr(byte[] bs) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	private static Random random = new Random();

	public static String generateVerifyCode() {
		char[] chars = "0123456789".toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int rand = random.nextInt(10);
			sb.append(chars[rand]);
		}
		return sb.toString();
	}

}
