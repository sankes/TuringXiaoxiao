package com.shankes.turing.domain;

import java.util.List;

/**
 * ������(�������ʶ��,��ʾ��,�����б�)
 * 
 * @author shankes
 */
public class TuringCook extends TuringBaseInfo {

	private List<TuringCookList> list;

	public List<TuringCookList> getList() {
		return list;
	}

	public void setList(List<TuringCookList> list) {
		this.list = list;
	}
}
