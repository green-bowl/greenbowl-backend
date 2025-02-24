package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StorageMethod {
    // refrigerated, frozen, room_temperature
    COLD("냉장"),
    FROZEN("냉동"),
    ROOM_TEMP("상온");

    private final String description;

    public static StorageMethod of(String storageMethod) {
        for (StorageMethod value : StorageMethod.values()) {
            if (value.getDescription().equals(storageMethod)) {
                return value;
            }
        }
        return null;
    }
}
