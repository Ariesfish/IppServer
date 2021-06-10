package xyz.ariesfish.ipp.attribute;

import lombok.Getter;

import java.util.ArrayList;

public class Attributes extends ArrayList<Attribute> {
    @Getter
    private Tag groupTag;

    public Attributes(Tag t) {
        groupTag = t;
    }
}
