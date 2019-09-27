package com.yueqian.faq.common;

public enum ProblemStatus {
	UNRESOLVED("δ���", 1), RESOLVED("�ѽ��", 2);
	private int value;
	private String name;

	private ProblemStatus(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static ProblemStatus from(byte level) {
		ProblemStatus[] array = ProblemStatus.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].value == level) {
				return array[i];
			}
		}
		return null;
	}
}
