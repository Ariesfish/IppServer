package xyz.ariesfish.ipp.value;

import lombok.Getter;
import lombok.Setter;
import xyz.ariesfish.ipp.attribute.Tag;

public class ValueMember {
    @Getter
    @Setter
    private Tag tag;

    @Getter
    @Setter
    private Value value;

    public ValueMember(Tag t, Value v) {
        tag = t;
        value = v;
    }

    @Override
    public String toString() {
        return "ValueMember{" +
                "tag=" + tag +
                ", value=" + value +
                '}';
    }
}
