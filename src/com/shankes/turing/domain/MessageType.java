package com.shankes.turing.domain;

import android.R.integer;

public enum MessageType {

	FROM(0), // �յ�����Ϣ
	TO(1); // ���͵���Ϣ

	private int mType;

	private MessageType(int _type) {
		this.mType = _type;
	}

	public int getValueType() {
		return mType;
	}

	public static MessageType valueOf(int _type) {
		MessageType messageType = FROM;
		switch (_type) {
		case 0:
			messageType = FROM;
			break;
		case 1:
			messageType = TO;
			break;

		default:
			break;
		}
		return messageType;
	}
}
