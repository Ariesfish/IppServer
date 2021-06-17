package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

import java.nio.charset.StandardCharsets;

public class StringValue implements Value {
    private String value;

    public StringValue() {
        this("");
    }

    public StringValue(String data) {
        value = data;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public boolean decode(byte[] data) {
        value = new String(data);
        return true;
    }

    @Override
    public byte[] encode() {
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
