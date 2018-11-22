/**
 * Copyright (c) 2011-2017, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.system.common.util;

import com.system.bean.User;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HashKit {

	private static final java.security.SecureRandom random = new java.security.SecureRandom();
	private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
	private static final char[] CHAR_ARRAY = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public static String md5(String srcStr){
		return hash("MD5", srcStr);
	}

	public static String sha1(String srcStr){
		return hash("SHA-1", srcStr);
	}

	public static String sha256(String srcStr){
		return hash("SHA-256", srcStr);
	}

	public static String sha384(String srcStr){
		return hash("SHA-384", srcStr);
	}

	public static String sha512(String srcStr){
		return hash("SHA-512", srcStr);
	}

	public static String hash(String algorithm, String srcStr) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
			return toHex(bytes);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toHex(byte[] bytes) {
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i=0; i<bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	/**
	 * md5 128bit 16bytes
	 * sha1 160bit 20bytes
	 * sha256 256bit 32bytes
	 * sha384 384bit 48bytes
	 * sha512 512bit 64bytes
	 */
	public static String generateSalt(int saltLength) {
		StringBuilder salt = new StringBuilder(saltLength);
		for (int i=0; i<saltLength; i++) {
			salt.append(CHAR_ARRAY[random.nextInt(CHAR_ARRAY.length)]);
		}
		return salt.toString();
	}

	public static String generateSaltForSha256() {
		return generateSalt(32);
	}

	public static String generateSaltForSha512() {
		return generateSalt(64);
	}

	public static boolean slowEquals(byte[] a, byte[] b) {
		if (a == null || b == null) {
			return false;
		}

		int diff = a.length ^ b.length;
		for(int i=0; i<a.length && i<b.length; i++) {
			diff |= a[i] ^ b[i];
		}
		return diff == 0;
    }

    public static void main(String[] args){
		List<User> num = new ArrayList<>();
		User u = new User();
		u.setAgent(1);
		u.setNickName("V");
		num.add(u);
		User u1 = new User();
		u1.setAgent(2);
		u1.setNickName("W");
		num.add(u1);
		User u2 = new User();
		u2.setAgent(3);
		u2.setNickName("X");
		num.add(u2);
		User u3 = new User();
		u3.setAgent(4);
		u3.setNickName("Y");
		num.add(u3);
		User u4 = new User();
		u4.setAgent(5);
		u4.setNickName("Z");
		num.add(u4);

		num.forEach( n ->{
			System.out.println(n.getAgent());
		});

		System.out.println("after sort!");
		Collections.sort(num, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				if(o1.getAgent()>o2.getAgent()){
					return -1;
				}
				return 1;
			}
		});

		num.forEach( n ->{
			System.out.println(n.getAgent());
		});
	}
}




