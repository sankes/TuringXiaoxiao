package com.shankes.turing.domain;

/**
 * ��Ϣ�б�(���ű���,������Դ,����ͼƬ,������������)
 * 
 * @author shankes
 */
public class TuringNewsList {

	private String article;// ���ű���
	private String source;// ������Դ
	private String icon;// ����ͼƬ
	private String detailurl;// ������������

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
}
