package org.vicrul.customerservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Sex {

    MALE("male"), FEMALE("female");

    private String alias;
}
