package socks5.encryption;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayOutputStream;

/**
 * crypt 加密
 * 
 * @author zhaohui
 * 
 */
public interface ICrypt {

	ByteBuf encrypt(ByteBuf byteBuf);

	ByteBuf decrypt(ByteBuf byteBuf);

	void encrypt(byte[] data, ByteArrayOutputStream stream);

	void encrypt(byte[] data, int length, ByteArrayOutputStream stream);

	void decrypt(byte[] data, ByteArrayOutputStream stream);

	void decrypt(byte[] data, int length, ByteArrayOutputStream stream);

}
