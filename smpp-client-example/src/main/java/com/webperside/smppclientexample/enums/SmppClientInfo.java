package com.webperside.smppclientexample.enums;

import lombok.Getter;

@Getter
public enum SmppClientInfo {
    CLIENT_5555("client-1111", "1111"),
    CLIENT_6070("client-2222", "2222");

    private final String name;
    private final String shortCode;

    SmppClientInfo(String name, String shortCode) {
        this.name = name;
        this.shortCode = shortCode;
    }

}
