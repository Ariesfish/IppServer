package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Attribute;
import xyz.ariesfish.ipp.attribute.Tag;
import xyz.ariesfish.ipp.attribute.Type;

import java.util.ArrayList;

public class CollectionValue extends ArrayList<Attribute> implements Value {
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Type getType() {
        return Type.COLLECTION;
    }

    @Override
    public boolean decode(byte[] data) {
        return false;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    public CollectionValue addMember(Attribute member) {
        if (member != null) {
            this.add(member);
        }
        return this;
    }

    public CollectionValue addMember(String name, Tag tag, Value value) {
        if (name != null && tag != null && value != null) {
            Attribute member = new Attribute(name, tag, value);
            return addMember(member);
        } else {
            return this;
        }
    }

    public CollectionValue addMember(String name, int tagInt, Value value) {
        return addMember(name, new Tag(tagInt), value);
    }
}
