package xyz.ariesfish.ipp.attribute;

import lombok.Getter;
import lombok.Setter;
import xyz.ariesfish.ipp.value.*;

import java.util.ArrayList;

public class Attribute {

    @Getter
    @Setter
    private String name;

    private ArrayList<ValueMember> values;

    public Attribute() {
        this("");
    }

    public Attribute(String name) {
        this(name, new ArrayList<ValueMember>());
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

    public ValueMember getValueMember(int index) {
        return values.get(index);
    }

    public void setValue(int index, Value value) {
        values.get(index).setValue(value);
    }

    public void setTag(int index, Tag tag) {
        values.get(index).setTag(tag);
    }

    public int valueSize() {
        return values.size();
    }

    public boolean unpackValue(Tag tag, byte[] data) {
        boolean result = false;
        Value value;

        switch (tag.getType()) {
            case NONE:
            case COLLECTION:
                value = new NoneValue();
                break;
            case INTEGER:
                value = new IntValue();
                break;
            case BOOLEAN:
                value = new BooleanValue();
                break;
            case STRING:
                value = new StringValue();
                break;
            case DATETIME:
                value = new DateTimeValue();
                break;
            case RESOLUTION:
                value = new ResolutionValue();
                break;
            case RANGE:
                value = new RangeValue();
                break;
            case TEXT_WITH_LANG:
                value = new TextWithLangValue();
                break;
            case BINARY:
                value = new BinaryValue();
                break;
            default:
                // TODO: log error
                return false;
        }

        if (value.decode(data)) {
            addValue(tag, value);
            result = true;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
