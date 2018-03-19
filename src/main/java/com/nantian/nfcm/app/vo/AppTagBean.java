package com.nantian.nfcm.app.vo;

public class AppTagBean {
    private String tagNum;
    private String tagKey;
    private String tagCiphertext;
    private String tagText;

    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagCiphertext() {
        return tagCiphertext;
    }

    public void setTagCiphertext(String tagCiphertext) {
        this.tagCiphertext = tagCiphertext;
    }

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
    
}
