package com.ultrainstinct.android.visapay.Models;

public class Barcode {
    String code,concern,key;

    public Barcode(String code, String concern, String key) {
        this.code = code;
        this.concern = concern;
        this.key = key;
    }

    public Barcode() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
