package com.shankes.turing.domain;

import java.util.List;

/**
 * ������(�������ʶ��,��ʾ��,��Ϣ�б�<array>) )
 * 
 * @author shankes
 */
public class TuringNews extends TuringBaseInfo {

	private List<TuringNewsList> list;

	public List<TuringNewsList> getList() {
		return list;
	}

	public void setList(List<TuringNewsList> list) {
		this.list = list;
	}
}
