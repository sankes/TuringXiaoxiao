package com.shankes.turing.domain;

import java.util.Date;

/**
 * @author shankes
 */
public class TuringBaseInfo {

	private int code;// ��ʶ��
	private String text;// ���
	private MessageType messageType;// ��Ϣ����
	private Date time;// ��Ϣ����ʱ��

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
