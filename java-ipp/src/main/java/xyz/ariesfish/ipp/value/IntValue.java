package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class IntValue implements Value {
    private final static int SIZE = 4;

    private int value;

    public IntValue() {
        this(-1);
    }

    public IntValue(int data) {
        value = data;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public boolean decode(byte[] data) {
        if (data == null || data.length != SIZE) {
            return false;
        } else {
            value = ((data[0] & 0xff) << 24)
                    | ((data[1] & 0xff) << 16)
                    | ((data[2] & 0xff) << 8)
                    | (data[3] & 0xff);
            return true;
        }
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[SIZE];
        data[0] = (byte)((value & 0xff000000) >> 24);
        data[1] = (byte)((value & 0xff0000) >> 16);
        data[2] = (byte)((value & 0xff00) >> 8);
        data[3] = (byte)(value & 0xff);
        return data;
    }
}
