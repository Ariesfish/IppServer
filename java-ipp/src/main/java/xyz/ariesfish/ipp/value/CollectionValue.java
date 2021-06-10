package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Attribute;
import xyz.ariesfish.ipp.attribute.Type;

import java.util.ArrayList;

public class CollectionValue extends ArrayList<Attribute> implements Value {
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean decode(byte[] data) {
        return false;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
