package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

import java.util.Arrays;

public class BinaryValue implements Value {
    private byte[] value;

    public BinaryValue() {
        this(new byte[0]);
    }

    public BinaryValue(byte[] data) {
        value = data;
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }

    @Override
    public Type getType() {
        return Type.BINARY;
    }

    @Override
    public boolean decode(byte[] data) {
        if (data != null) {
            value = data;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public byte[] encode() {
        return value;
    }
}
