package com.shankes.turing.util;

import android.R.integer;

public enum TuringCodeType {

	RECEIVE_RESULT(500001, "�յ���Ϣ"), // �յ���Ϣ

	NORMAL_TEXT(100000, "�ı���"), // �ı���
	NORMAL_LINK(200000, "������"), // ������
	NORMAL_NEWS(302000, "������"), // ������
	NORMAL_COOK(308000, "������"), // ������
	NORMAL_CHILD_SONG(313000, "(��ͯ��)������"), // ����ͯ�棩 ������
	NORMAL_CHILD_POEM(314000, "(��ͯ��)ʫ����"), // ����ͯ�棩ʫ����

	ERROR_KEY(40001, "����key����"), // ����key����
	ERROR_INFO(40002, "��������infoΪ��"), // ��������infoΪ��
	ERROR_TOO_MANY_TIMES(40004, "�������������ʹ����"), // �������������ʹ����
	ERROR_DATA_TYPE_EXCEPTION(40007, "���ݸ�ʽ�쳣");// ���ݸ�ʽ�쳣

	private int mCode;
	private String mText;

	private TuringCodeType(int _code, String _text) {
		this.mCode = _code;
		this.mText = _text;
	}

	public static int getValueCode(TuringCodeType turingCodeType) {
		int code = 100000;
		switch (turingCodeType) {
		case NORMAL_TEXT:
			code = 100000;
			break;
		case NORMAL_LINK:
			code = 200000;
			break;
		case NORMAL_NEWS:
			code = 302000;
			break;
		case NORMAL_COOK:
			code = 308000;
			break;
		case NORMAL_CHILD_SONG:
			code = 313000;
			break;
		case NORMAL_CHILD_POEM:
			code = 314000;
			break;
		case ERROR_KEY:
			code = 40001;
			break;
		case ERROR_INFO:
			code = 40002;
			break;
		case ERROR_TOO_MANY_TIMES:
			code = 40004;
			break;
		case ERROR_DATA_TYPE_EXCEPTION:
			code = 40007;
			break;
		case RECEIVE_RESULT:
			code = 500001;
			break;
		default:
			code = 100000;
			break;
		}
		return code;
	}

	public String getValueText() {
		return mText;
	}

	public static TuringCodeType valueOfInt(int value) {
		TuringCodeType turingCodeType = ERROR_DATA_TYPE_EXCEPTION;
		switch (value) {
		case 100000:
			turingCodeType = NORMAL_TEXT;
			break;
		case 200000:
			turingCodeType = NORMAL_LINK;
			break;
		case 302000:
			turingCodeType = NORMAL_NEWS;
			break;
		case 308000:
			turingCodeType = NORMAL_COOK;
			break;
		case 313000:
			turingCodeType = NORMAL_CHILD_SONG;
			break;
		case 314000:
			turingCodeType = NORMAL_CHILD_POEM;
			break;
		case 40001:
			turingCodeType = ERROR_KEY;
			break;
		case 40002:
			turingCodeType = ERROR_INFO;
			break;
		case 40004:
			turingCodeType = ERROR_TOO_MANY_TIMES;
			break;
		case 40007:
			turingCodeType = ERROR_DATA_TYPE_EXCEPTION;
			break;
		case 500001:
			turingCodeType = RECEIVE_RESULT;
			break;
		default:
			turingCodeType = ERROR_DATA_TYPE_EXCEPTION;
			break;
		}
		return turingCodeType;
	}
}
