package com.hzp.demo.utils;

import java.util.UUID;

public class RandomUtil {

	public static String randomStr(int sum) {
		return UUID.randomUUID().toString().replace("-", "").substring(0, sum);
	}
}
