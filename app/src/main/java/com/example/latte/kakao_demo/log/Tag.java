package com.example.latte.kakao_demo.log;

public enum Tag {
    DEFAULT("kakao.sdk");

    private final String tag;

    Tag(String tag) {
        this.tag = tag;
    }

    public String tag() {
        return tag;
    }
}
