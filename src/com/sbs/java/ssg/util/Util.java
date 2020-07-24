package com.sbs.java.ssg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// 현재 시간 테스트
public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format.format(time);
	}
}
