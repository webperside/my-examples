package com.webperside.bulksendexample.enums;

import lombok.Getter;

@Getter
public enum MessageStatus {
    FAILED(0),
    SUCCESS(1),
    PENDING(2),
    IN_PROGRESS(3);

    private final byte value;

    MessageStatus(int value) {
        this.value = (byte) value;
    }
}
