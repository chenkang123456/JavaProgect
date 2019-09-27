package com.yueqian.faq.common;

public enum EnglishLevel {
	COLLEGE_4("��ѧ4��", 1), COLLEGE_6("��ѧ6��", 2), MAJOR_4("רҵ4��", 3), MAJOR_8("רҵ8��", 4), NORMAL("һ��ˮƽ", 5);
	private int value;
	private String name;

	private EnglishLevel(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static EnglishLevel from(byte level) {
		EnglishLevel[] array = EnglishLevel.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].value == level) {
				return array[i];
			}
		}
		return null;
	}

}
