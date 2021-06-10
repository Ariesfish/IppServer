package xyz.ariesfish.ipp.attribute;

import lombok.Getter;
import lombok.Setter;
import xyz.ariesfish.ipp.value.Value;
import xyz.ariesfish.ipp.value.ValueMember;

import java.util.ArrayList;

public class Attribute {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private ArrayList<ValueMember> values;

    public Attribute() {

    }

    public Attribute(String name) {
        this.name = name;
        this.values = new ArrayList<>();
    }

    public Attribute(String name, Tag valueTag, Value value) {
        this(name);
        this.values.add(new ValueMember(valueTag, value));
    }

    public Attribute(String name, int valueTagInt, Value value) {
        this(name, new Tag(valueTagInt), value);
    }

    public Attribute(String name, ArrayList<ValueMember> values) {
        this.name = name;
        this.values = values;
    }

    public Attribute addValue(ValueMember vm) {
        values.add(vm);
        return this;
    }

    public Attribute addValue(Tag tag, Value value) {
        return addValue(new ValueMember(tag, value));
    }

    public boolean unpackValue(Tag tag, byte[] data) {
        return false;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
