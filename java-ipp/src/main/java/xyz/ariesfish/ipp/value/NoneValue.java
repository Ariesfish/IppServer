package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class NoneValue implements Value {
    @Override
    public String toString() {
        return "";
    }

    @Override
    public Type getType() {
        return Type.NONE;
    }

    @Override
    public boolean decode(byte[] data) {
        return true;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
