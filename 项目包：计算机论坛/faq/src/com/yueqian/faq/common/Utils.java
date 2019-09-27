package com.yueqian.faq.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static int count = 0;

	/**
	 * �����ַ�����ʽ������ֵ������Date����
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseDate(String value) {
		Date result = null;
		if (value != null && value.length() > 0) {
			try {
				result = sdf.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ����ָ�����ȵ�����ַ���
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += (int) (Math.random() * 10);
		}
		return str;
	}

	/**
	 * ���ַ���ת��ΪInteger����
	 * 
	 * @param value
	 * @return
	 */
	public static Integer convert2Integer(String value) {
		Integer result = null;
		if (value != null && value.length() > 0) {
			result = new Integer(value);
		}
		return result;
	}

	/**
	 * ����Ψһ���ļ�ID
	 * 
	 * @return
	 */
	public static String createFileId() {
		String id = sdf1.format(new Date()) + String.format("%02d", ++count);
		if (count > 99) {
			count = 0;
		}
		return id;
	}
}
