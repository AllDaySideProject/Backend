package com.example.Centralthon.domain.menu.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Concept {
    DIET("diet"),
    KETO("keto"),
    LOW_SODIUM("low_sodium"),
    GLYCEMIC("glycemic"),
    BULKING("bulking");

    private final String value;

    @JsonValue
    public String getValue(){
        return value;
    }

    @JsonCreator
    public static Concept fromValue(String value){
        for (Concept concept : Concept.values()){
            if (concept.getValue().equals(value)){
                return concept;
            }
        }
        throw new IllegalArgumentException();
    }
}
