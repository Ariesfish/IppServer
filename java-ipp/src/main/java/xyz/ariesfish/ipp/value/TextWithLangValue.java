package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class TextWithLangValue implements Value {
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
