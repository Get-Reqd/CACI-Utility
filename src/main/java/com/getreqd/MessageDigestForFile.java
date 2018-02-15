package com.getreqd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MessageDigestForFile {

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {

		String file = "test.iso";
		MessageDigest md = MessageDigest.getInstance("MD5");
		String digest = getDigest(new FileInputStream(file), md, 2048);

		System.out.println("MD5 Digest:" + digest);

	}

	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
			throws NoSuchAlgorithmException, IOException {

		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}

}