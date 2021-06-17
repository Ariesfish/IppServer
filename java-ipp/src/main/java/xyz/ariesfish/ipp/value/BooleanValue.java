package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class BooleanValue implements Value {

    private boolean value;

    public BooleanValue() {
        this(false);
    }

    public BooleanValue(boolean data) {
        value = data;
    }

    @Override
    public String toString() {
        return String.format("%s", value ? "true" : "false");
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public boolean decode(byte[] data) {
        if (data != null && data.length == 1) {
            int v = data[0] & 0xff;
            this.value = (v == 1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[1];
        data[0] = (byte)(value ? 0x01 : 0x00);
        return data;
    }
}
