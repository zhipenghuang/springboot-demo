package com.hzp.demo.utils;

import org.springframework.beans.BeanUtils;

public class ObjectUtil {
	public ObjectUtil() {
	}

	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}

	public static void copyPropertiesNotNull(Object source, Object target) {
		com.hzp.demo.utils.BeanUtils.copyProperties(source, target);
	}
}