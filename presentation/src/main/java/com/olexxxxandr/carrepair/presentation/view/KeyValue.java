package com.olexxxxandr.carrepair.presentation.view;

public record KeyValue(String key, String value) {

    @Override
    public String toString() {
        return value;
    }
}
