package edu.csust.volunteer.util;

import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//AES加密
public class AESUtilTest {

	private static final String AES = "AES";
	// 16位的秘钥
	private static final String INVITE_CODE_KEY = "aaaaaaaaaaaaaaaa" + "";

	private static byte[] encrypt(byte[] src, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		return cipher.doFinal(src);
	}

	private static byte[] decrypt(byte[] src, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		return cipher.doFinal(src);
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("invalid arg b:" + b);
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public final static String decryptInviteCode(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					INVITE_CODE_KEY));
		} catch (Exception e) {
		}
		return null;
	}

	public final static String encryptInviteCode(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(), INVITE_CODE_KEY));
		} catch (Exception e) {
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Date now = new Date();
		System.out.println(now.toLocaleString());

		String inviteCode = AESUtilTest.encryptInviteCode(System
				.currentTimeMillis() + "_1");
		System.out.println("inviteCode  " + inviteCode);

		String des = AESUtilTest.decryptInviteCode(inviteCode);
		System.out.println(des);

	}
}