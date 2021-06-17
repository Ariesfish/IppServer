package xyz.ariesfish.ipp.attribute;

import lombok.Getter;

import java.util.ArrayList;

public class Attributes extends ArrayList<Attribute> {
    @Getter
    private Tag groupTag;

    public Attributes(Tag t) {
        groupTag = t;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Attribute attr : this) {
            builder.append(attr.toString()).append("\n");
        }
        return builder.toString();
    }
}
