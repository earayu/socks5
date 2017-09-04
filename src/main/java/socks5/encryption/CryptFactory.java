package socks5.encryption;

import socks5.encryption.impl.AesCrypt;
import socks5.encryption.impl.BlowFishCrypt;
import socks5.encryption.impl.CamelliaCrypt;
import socks5.encryption.impl.SeedCrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CryptFactory {

	private static Log logger = LogFactory.getLog(CryptFactory.class);

	private static Map<String, String> crypts = new HashMap<String, String>();

	static {
		crypts.putAll(AesCrypt.getCiphers());
		crypts.putAll(CamelliaCrypt.getCiphers());
		crypts.putAll(BlowFishCrypt.getCiphers());
		crypts.putAll(SeedCrypt.getCiphers());
	}

	public static ICrypt get(String name, String password) {
		String className = crypts.get(name);
		if (className == null) {
			return null;
		}

		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(String.class,
					String.class);
			return (ICrypt) constructor.newInstance(name, password);
		} catch (Exception e) {
			logger.error("get crypt error", e);
		}

		return null;
	}

//	public static void main(String[] args) throws UnsupportedEncodingException {
//		ICrypt iCrypt = CryptFactory.get("aes-256-cfb", "362412642");
//		ByteBuf byteBuf = iCrypt.encrypt(Unpooled.copiedBuffer("hello", Charset.defaultCharset()));
//		int len = byteBuf.readableBytes();
//		byte[] arr = new byte[len];
//		byteBuf.getBytes(0, arr);
//		System.err.println(new String(arr));
//		System.err.println(iCrypt.decrypt(byteBuf).toString(Charset.defaultCharset()));
//	}
}
